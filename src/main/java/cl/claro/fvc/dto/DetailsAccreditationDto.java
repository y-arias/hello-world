package cl.claro.fvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class DetailsAccreditationDto {
  private String codiTipoAcreditacion;
  private String fechIniTipoAcredita;
  private String codiAcredParam;
  private String descAcredParam;
  private String codiTagMotor;
  private String codiTipoDato;
  private String vlorMinimo;
  private String vlorMaximo;
  private Long   vlorOrden;
  private String indcObligatorio;
  private String indcEnvioMotor;
  private String indcEditFinalizacion;
  private List<ExtraValuesDto> extraValue;

  public DetailsAccreditationDto() {
    //Constructor por defecto
  }

  @Override
  public String toString() {
    return "DetailsAccreditationDto{" +
          "codiTipoAcreditacion='" + codiTipoAcreditacion + '\'' +
          ", fechIniTipoAcredita='" + fechIniTipoAcredita + '\'' +
          ", codiAcredParam='" + codiAcredParam + '\'' +
          ", descAcredParam='" + descAcredParam + '\'' +
          ", codiTagMotor='" + codiTagMotor + '\'' +
          ", codiTipoDato='" + codiTipoDato + '\'' +
          ", vlorMinimo='" + vlorMinimo + '\'' +
          ", vlorMaximo='" + vlorMaximo + '\'' +
          ", vlorOrden=" + vlorOrden +
          ", indcObligatorio='" + indcObligatorio + '\'' +
          ", indcEnvioMotor='" + indcEnvioMotor + '\'' +
          ", indcEditFinalizacion='" + indcEditFinalizacion + '\'' +
          ", extraValuesDto=" + extraValue +
          '}';
  }
}
