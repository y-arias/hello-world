package cl.claro.fvc.repository.document;

import cl.claro.fvc.dto.DatosAcreditacionTipoDto;
import cl.claro.fvc.dto.DatosNumberLinesAllowedDto;
import cl.claro.fvc.dto.DatosResponseInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class DatosCreditScore {

  private String orderId;
  private DatosResponseInfoDto datosResponseInfoDto;
  private List<DatosNumberLinesAllowedDto> datosNumberLinesAllowedDto;
  private String numberOfFinancedActiveLines;
  private String reglasAplicadas;
  private String codiAcreditacion;
  private String descAcreditacion;
  private List<DatosAcreditacionTipoDto> extraValuesEntered;


  public DatosCreditScore() {
    //Constructor por defecto
  }

  @Override
  public String toString() {
    return "DatosCreditScore{" +
          "orderId='" + orderId + '\'' +
          ", datosResponseInfoDto=" + datosResponseInfoDto +
          ", datosNumberLinesAllowedDto=" + datosNumberLinesAllowedDto +
          ", numberOfFinancedActiveLines='" + numberOfFinancedActiveLines + '\'' +
          ", reglasAplicadas='" + reglasAplicadas + '\'' +
          ", codiAcreditacion='" + codiAcreditacion + '\'' +
          ", descAcreditacion='" + descAcreditacion + '\'' +
          ", extraValuesEntered=" + extraValuesEntered +
          '}';
  }
}
