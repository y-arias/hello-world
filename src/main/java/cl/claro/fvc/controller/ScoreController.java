package cl.claro.fvc.controller;

import cl.claro.fvc.dto.*;
import cl.claro.fvc.repository.dao.DataTypeAccredSimpleRepository;
import cl.claro.fvc.repository.document.DataTypeAccredDocument;
import cl.claro.fvc.repository.document.DatosCreditScoreDocument;
import cl.claro.fvc.util.Constantes;
import cl.claro.fvc.repository.dao.DatosScoreRepository;
import cl.claro.fvc.repository.dao.KafkaMsgRepository;
import cl.claro.fvc.repository.document.RspDataScoreDocument;
import cl.claro.fvc.repository.event.KafkaMsgProducer;
import cl.claro.fvc.util.exception.ValidationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@RequestMapping("score")
@RestController("score")
@CrossOrigin("*")
@RefreshScope
public class ScoreController {

  private KafkaMsgProducer kafkaMsgProducer;

  private KafkaMsgRepository kafkaMsgRepository;

  private DatosScoreRepository datosScoreRepository;

  private DataTypeAccredSimpleRepository dataTypeAccredSimpleRepository;

  private Environment env;

  @Autowired
  public ScoreController(KafkaMsgProducer kafkaMsgProducer, KafkaMsgRepository kafkaMsgRepository,
                         DatosScoreRepository datosScoreRepository, Environment env,
                         DataTypeAccredSimpleRepository dataTypeAccredSimpleRepository) {
    this.kafkaMsgProducer = kafkaMsgProducer;
    this.kafkaMsgRepository = kafkaMsgRepository;
    this.datosScoreRepository = datosScoreRepository;
    this.env = env;
    this.dataTypeAccredSimpleRepository = dataTypeAccredSimpleRepository;
  }

  @PostMapping(value = "/command/", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  @ResponseStatus(HttpStatus.ACCEPTED)
  @ResponseBody
  public Mono<ResponseScoreTo> createScore(@RequestBody RequestScoreTo requestScoreTo)
        throws JsonProcessingException {
    log.info("Inicio Metodo createScore {}", requestScoreTo);
    RequestMsgScore requestMsgScore = new RequestMsgScore();
    UUID idTransaccion = UUID.randomUUID();
    String oaType;
    String accreditationTypeDescription;
    Optional<DataTypeAccredDocument> accreditationTypeOptional = dataTypeAccredSimpleRepository
          .findById(requestScoreTo.getAccreditationInfoDto().getAccreditationTypeCode());

    if (accreditationTypeOptional.isPresent()) {
      accreditationTypeDescription = accreditationTypeOptional.get().getDescTipoAcreditacion();
      if (env.getProperty(Constantes.TIPOACREDITACION).equals(accreditationTypeDescription)) {
        oaType = Optional.ofNullable(requestScoreTo.getOaType())
              .orElse(Constantes.OATYPE_PORTA);
      } else {
        oaType = Optional.ofNullable(requestScoreTo.getOaType())
              .orElse(Constantes.OATYPE_VENTA);
      }
      requestMsgScore.setUuid(idTransaccion.toString());
      requestMsgScore.setOaType(oaType);
      requestMsgScore.setUserDto(requestScoreTo.getUserDto());
      requestMsgScore.setAccreditationInfoDto(DatosAcreditacionMsgDto.builder()
            .accreditationName(accreditationTypeDescription)
            .datosAcreditacionTipoDto(requestScoreTo.getAccreditationInfoDto().getDatosAcreditacionTipoDto()).build());
      requestMsgScore.setCustomerDto(requestScoreTo.getCustomerDto());
    }
    //Logica asociada a BIOMETRIA
    if (null != requestScoreTo.getIndicadorBiometria() &&
          Constantes.CONS_TEXTO_S.equals(requestScoreTo.getIndicadorBiometria())) {

      DatosBiometriaDto datosBiometriaDto = new DatosBiometriaDto();

      datosBiometriaDto.setAuditNumber(requestScoreTo.getAuditNumber());
      datosBiometriaDto.setIsPreviredAllowed(env.getProperty(Constantes.BIOMETRIC_VALIDATION_ALLOWED));
      datosBiometriaDto.setType(env.getProperty(Constantes.BIOMETRIC_VALIDATION_TYPE));
      requestScoreTo.setBiometricType(datosBiometriaDto);
    }
    requestMsgScore.setBiometricType(requestScoreTo.getBiometricType());
    requestMsgScore.setIndicadorBiometria(requestScoreTo.getIndicadorBiometria());
    requestMsgScore.setCodiAcreditacion(requestScoreTo.getCodiAcreditacion());
    requestMsgScore.setDescAcreditacion(requestScoreTo.getDescAcreditacion());
    ObjectMapper mapper = new ObjectMapper();
    String jsonRequest = mapper.writeValueAsString(requestMsgScore);

    kafkaMsgProducer.sendMessageRequest(Constantes.KAFKA_TOPIC_REQUEST, jsonRequest);

    ResponseScoreTo responseScoreTo = new ResponseScoreTo();
    responseScoreTo.setUuid(requestMsgScore.getUuid());

    return Mono.just(responseScoreTo);
  }

  @GetMapping(value = "/hub/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<RspDataScoreDocument> hubGetMensaje(@PathVariable("id") String id) {
    log.info("hub obtener datos controller id [{}]", id);
    return Flux
          .interval(Duration.ofSeconds(1))
          .onBackpressureDrop()
          .flatMap(val -> kafkaMsgRepository.findById(id).flux());
  }

  @GetMapping(value = "/query/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Mono<DatosCreditScoreDocument> obtenerEvaluacion(@PathVariable("id") String id) {
    log.info("obtener datos controller id = [{}]", id);
    return datosScoreRepository.findById(id);
  }

  @GetMapping(value = "/query/evaluation/{idEvaluation}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Mono<DatosCreditScoreDocument> getScoreByNmrEvaluacion(@PathVariable("idEvaluation") String idEvaluation) {
    log.info("get controller data from evaluation number = [{}]", idEvaluation);
    return datosScoreRepository.findByOrderId(idEvaluation);
  }

  @SuppressWarnings("squid:S3776")
  @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<DatosCreditScoreDocument> updateScoreExtraValuesByNmrEvaluacion(
        @RequestBody UpdateScoreExtraValuesRequestDto request) {
    log.info("Inicio updateScoreExtraValuesByNmrEvaluacion() request = {}", request);
    return datosScoreRepository.findByOrderId(request.getIdEvaluation()).map(scoreData -> {
      List<DatosAcreditacionTipoDto> extraValuesEntered = scoreData.getExtraValuesEntered();
      DataTypeAccredDocument configuration;
      List<DatosAcreditacionTipoDto> newExtraValues = new ArrayList<>();
      if (dataTypeAccredSimpleRepository.findById(scoreData.getCodiAcreditacion()).isPresent()) {
        configuration = dataTypeAccredSimpleRepository.findById(scoreData.getCodiAcreditacion()).get();
        if("N".equals(configuration.getIndcDatosExtras())) {
          log.info("No existen datos extra para este tipo de evaluacion");
          throw new ValidationException("No existen datos extra solicitados para este tipo de evaluacion");
        } else {
          //verificar que campos son actualizables y actualizarlos.
          for (DatosAcreditacionTipoDto extraValueUpdated: request.getExtraValues()) {
            if(isUpdatable(extraValueUpdated, configuration)) {
              newExtraValues.add(extraValueUpdated);
            }
          }
          for (DatosAcreditacionTipoDto extraValueOld: extraValuesEntered) {
            if(!contains(extraValueOld, newExtraValues)) {
              newExtraValues.add(extraValueOld);
            }
          }
          log.info("extraValuesEntered: {}", extraValuesEntered);
          scoreData.setExtraValuesEntered(newExtraValues);
          datosScoreRepository.save(scoreData).subscribe();
          return scoreData;
        }
      } else {
        log.info("No se encontro la configuracion para este tipo de evaluacion en MongoDB");
        throw new ValidationException("No existe configuracion para este tipo de Evaluacion");
      }
    });
  }

  boolean isUpdatable(DatosAcreditacionTipoDto extraValue, DataTypeAccredDocument configuration) {
    for (DetailsAccreditationDto detailAccredConfig: configuration.getDetails().getDetailsAccreditationDtos()) {
      if(detailAccredConfig.getCodiAcredParam().equals(extraValue.getCodiAcredParam())) {
        return "S".equals(detailAccredConfig.getIndcEditFinalizacion());
      }
    }
    return false;
  }

  boolean contains(DatosAcreditacionTipoDto value, List<DatosAcreditacionTipoDto> lista) {
    for (DatosAcreditacionTipoDto valor: lista) {
      if(value.getCodiAcredParam().equals(valor.getCodiAcredParam())) {
        return true;
      }
    }
    return false;
  }
}
