package cl.claro.fvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ExtraValuesDto {
  private String codiAcredParam;
  private String codiTipoAcreditacion;
  private String codiDespacred;
  private String descdespAcred;
  private String codiTagMotorPa;
  private String fechInicioVigencia;
  private String codiUsuario;

  @Override
  public String toString() {
    return "ExtraValuesDto{" +
          "codiAcredParam='" + codiAcredParam + '\'' +
          ", codiTipoAcreditacion='" + codiTipoAcreditacion + '\'' +
          ", codiDespacred='" + codiDespacred + '\'' +
          ", descdespAcred='" + descdespAcred + '\'' +
          ", codiTagMotorPa='" + codiTagMotorPa + '\'' +
          ", fechInicioVigencia='" + fechInicioVigencia + '\'' +
          ", codiUsuario='" + codiUsuario + '\'' +
          '}';
  }
}
