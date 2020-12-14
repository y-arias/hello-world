package cl.claro.fvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@SuppressWarnings({"squid:S1820"})
public class DatosResponseInfoDto {
  private String firstName;
  private String secondName;
  private String lastName;
  private String secondLastName;
  private String issueDateOfId;
  private String scoreExpiryDate;
  private String dateCreditBureauConsulted;
  private String creditRefNumber;
  private Boolean evidentaAlreadyValid;
  private String creditScoreCode;
  private String creditScoreDescription;
  private String gama;
  private List<String> exceptions;
  private String evaluationStatus;
  private String ceRejectMessage;
  private String ageRange;
  private String idPlaceOfIssue;
  private String idExpirationDate;
  private List<String> listOfRequiredDocuments;
  private String paymentBehavior;
  private String pastDueAmount;
  private String dueAmountDate;
  private String planType;
  private String purchaseLimit;
  private String totalNumberOfLines;
  private Boolean rcPaymentInAdvancedRequired;
  private Boolean equipmentImmediatePaymentRequired;
  private Boolean payMeansRequired;
  private String acquisitionType;
  private String url;
  private Boolean activeClient;
  private String newestSeniority;
  private List<DatosExpiredDueListDto> expiredDueList;
  private Boolean winback;

  public DatosResponseInfoDto() {
    //Constructor por defecto
  }
}
