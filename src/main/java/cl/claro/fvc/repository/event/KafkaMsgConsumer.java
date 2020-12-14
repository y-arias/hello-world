/*
 * Copyright (c) 2019.
 * Claro Chile
 * Creado por Sistemas ltda
 */
package cl.claro.fvc.repository.event;

import cl.claro.fvc.dto.DatosAcreditacionTipoDto;
import cl.claro.fvc.dto.RspScoreMsgeDto;
import cl.claro.fvc.repository.dao.DatosScoreRepository;
import cl.claro.fvc.repository.dao.KafkaMsgRepository;
import cl.claro.fvc.repository.document.DatosCreditScore;
import cl.claro.fvc.repository.document.DatosCreditScoreDocument;
import cl.claro.fvc.repository.document.RspDataScoreDocument;
import cl.claro.fvc.util.Constantes;
import cl.claro.fvc.util.exception.ValidationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Log4j2
@Service
public class KafkaMsgConsumer {

  private DatosScoreRepository datosClienteRepository;
  private KafkaMsgRepository kafkaMessageRepository;
  private Environment env;

  @Autowired
  public KafkaMsgConsumer(DatosScoreRepository datosClienteRepository, KafkaMsgRepository kafkaMessageRepository,
                          Environment env) {
    this.datosClienteRepository = datosClienteRepository;
    this.kafkaMessageRepository = kafkaMessageRepository;
    this.env = env;
  }

  @KafkaListener(topics = Constantes.KAFKA_TOPIC_RESPONSE, groupId = Constantes.KAFKA_TOPIC_GROUP_ID)
  public void domainListener(String message) throws IOException {
    RspDataScoreDocument rspDataScoreDocument = new RspDataScoreDocument();
    DatosCreditScoreDocument datosCreditScoreDocument = new DatosCreditScoreDocument();
    datosCreditScoreDocument.setIndicadorSiguiente(env.getProperty(Constantes.IND_DEUDAOK));
    datosCreditScoreDocument.setMensajeError("");
    try {
      RspScoreMsgeDto respKafka = new ObjectMapper().readValue(message, RspScoreMsgeDto.class);
      if (!Constantes.KAFKA_MESSAGE_CODE_NOK.equals(
            respKafka.getCodiRespuesta())) {
        rspDataScoreDocument.setUuid(respKafka.getUuid());
        rspDataScoreDocument.setCodiRespuesta(respKafka.getCodiRespuesta());
        rspDataScoreDocument.setDescRespuesta(respKafka.getDescRespuesta());
        rspDataScoreDocument.setTimeBegin(respKafka.getTimeBegin());
        rspDataScoreDocument.setTimeFinish(respKafka.getTimeFinish());
        rspDataScoreDocument.setDatosCreditScore(respKafka.getDatosCreditScore());

      } else {
        throw new ValidationException(respKafka.getDescRespuesta());
      }
      DatosCreditScore datosCreditScore = respKafka.getDatosCreditScore();
      datosCreditScoreDocument.setUuid(respKafka.getUuid());
      datosCreditScoreDocument.setOrderId(datosCreditScore.getOrderId());
      datosCreditScoreDocument.setDatosNumberLinesAllowedDto(datosCreditScore.getDatosNumberLinesAllowedDto());
      datosCreditScoreDocument.setDatosResponseInfoDto(datosCreditScore.getDatosResponseInfoDto());
      datosCreditScoreDocument.setNumberOfFinancedActiveLines(datosCreditScore.getNumberOfFinancedActiveLines());
      datosCreditScoreDocument.setReglasAplicadas(datosCreditScore.getReglasAplicadas());
      datosCreditScoreDocument.setCodiAcreditacion(datosCreditScore.getCodiAcreditacion());
      datosCreditScoreDocument.setDescAcreditacion(datosCreditScore.getDescAcreditacion());
      if(null != respKafka.getDatosCreditScore() && null != respKafka.getDatosCreditScore().getExtraValuesEntered()
            && !(respKafka.getDatosCreditScore().getExtraValuesEntered().isEmpty())) {
        datosCreditScoreDocument.setExtraValuesEntered(respKafka.getDatosCreditScore().getExtraValuesEntered());
      } else {
        datosCreditScoreDocument.setExtraValuesEntered(new ArrayList<DatosAcreditacionTipoDto>());
      }
      validarNoDeuda(respKafka, datosCreditScoreDocument);

      if("OWN".equals(datosCreditScore.getDatosResponseInfoDto().getAcquisitionType())){
        datosCreditScore.getDatosResponseInfoDto().setAcquisitionType("PROPIO");
      }else{
        datosCreditScore.getDatosResponseInfoDto().setAcquisitionType("");
      }

      if (null != respKafka.getDatosCreditScore()) {
        datosClienteRepository.save(datosCreditScoreDocument).block();
      }
      kafkaMessageRepository.save(rspDataScoreDocument).block();
    } catch (Exception e) {
      RspScoreMsgeDto respKafka = new ObjectMapper().readValue(message, RspScoreMsgeDto.class);
      rspDataScoreDocument.setUuid(respKafka.getUuid());
      rspDataScoreDocument.setCodiRespuesta(respKafka.getCodiRespuesta());
      rspDataScoreDocument.setDescRespuesta(respKafka.getDescRespuesta());
      rspDataScoreDocument.setTimeBegin(respKafka.getTimeBegin());
      rspDataScoreDocument.setTimeFinish(respKafka.getTimeFinish());
      rspDataScoreDocument.setDatosCreditScore(respKafka.getDatosCreditScore());
      kafkaMessageRepository.save(rspDataScoreDocument).block();
      throw new ValidationException("Error en el servicio " + e.getMessage() + " no es valido", e);
    }
  }

  public void validarNoDeuda(RspScoreMsgeDto respKafka, DatosCreditScoreDocument datosCreditScoreDocument) {
    String montoMax = env.getProperty(Constantes.MONTOMAXDEUDA);
    String indicadorDeuda = env.getProperty(Constantes.IND_DEUDAOK);
    String mensajeError = "";

    if (respKafka.getDatosCreditScore().getDatosResponseInfoDto().getPastDueAmount() != null
          && Integer.parseInt(respKafka.getDatosCreditScore().getDatosResponseInfoDto().getPastDueAmount()) > 0) {

      //check debt amount and winback
      if (Long.parseLong(montoMax) >=
            Long.parseLong(respKafka.getDatosCreditScore().getDatosResponseInfoDto().getPastDueAmount()) ||
            respKafka.getDatosCreditScore().getDatosResponseInfoDto().getWinback()) {
        indicadorDeuda = env.getProperty(Constantes.IND_DEUDAOK);
      } else {
        indicadorDeuda = env.getProperty(Constantes.IND_DEUDANOK);
        mensajeError = env.getProperty(Constantes.MSJ_ERRORDEUDA);
      }
    }
    datosCreditScoreDocument.setIndicadorSiguiente(indicadorDeuda);
    datosCreditScoreDocument.setMensajeError(mensajeError);
  }
}
