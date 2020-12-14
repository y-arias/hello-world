package cl.claro.fvc.controller;

import cl.claro.fvc.repository.dao.DataTypeAccredRepository;
import cl.claro.fvc.repository.document.DataDetailsAccredDocument;
import cl.claro.fvc.repository.document.DataTypeAccredDocument;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Log4j2
@RequestMapping("accreditation")
@RestController("accreditation")
@CrossOrigin("*")
@RefreshScope
public class AccreditationController {


  private DataTypeAccredRepository dataTypeAccredRepository;

  public AccreditationController(
        DataTypeAccredRepository dataTypeAccredRepository) {

    this.dataTypeAccredRepository = dataTypeAccredRepository;
  }

  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<List<DataTypeAccredDocument>> getConfiguredAccreditationByBiometricIndAndCustomerType(
        @RequestParam("biometricInd") String biometricInd,
        @RequestParam("customerType") String customerType) {
    log.info("Inicio getConfiguredAccreditationByBiometricIndAndCustomerType(), " +
          "biometricInd = {}, customerType = {}", biometricInd, customerType);

    return dataTypeAccredRepository.findConfiguredAccreditationByBiometricIndAndCustomerType(biometricInd,
          customerType).collectList();
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<DataTypeAccredDocument> getConfiguredAccreditationTypeById(
        @PathVariable("id") String id) {
    log.info("Inicio getConfiguredAccreditationTypeById(), id = {}", id);

    return dataTypeAccredRepository.findById(id);
  }

  @PostMapping(value = "/detailref", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<List<DataTypeAccredDocument>> referenciarDetalle() {
    return dataTypeAccredRepository.findAll().map(dataTypeAccredDocument -> {
      DataDetailsAccredDocument detail = new DataDetailsAccredDocument();
      detail.setCodiTipoAcreditacion(dataTypeAccredDocument.getCodiTipoAcreditacion());
      dataTypeAccredDocument.setDetails(detail);
      dataTypeAccredRepository.save(dataTypeAccredDocument).subscribe();
      return dataTypeAccredDocument;
    }).collectList();
  }

  @PostMapping(value = "/modifiyCustomerType")
  public Mono<List<DataTypeAccredDocument>> modifiyCustomerType() {
    return dataTypeAccredRepository.findAll().map(dataTypeAccredDocument -> {
      if(dataTypeAccredDocument.getCodiTipoCliente().equals("PN")) {
        dataTypeAccredDocument.setCodiTipoCliente("N");
      } else if(dataTypeAccredDocument.getCodiTipoCliente().equals("PG")) {
        dataTypeAccredDocument.setCodiTipoCliente("P");
      }
      dataTypeAccredRepository.save(dataTypeAccredDocument).subscribe();
      return dataTypeAccredDocument;
    }).collectList();
  }

}
