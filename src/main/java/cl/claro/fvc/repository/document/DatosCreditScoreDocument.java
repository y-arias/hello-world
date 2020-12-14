package cl.claro.fvc.repository.document;

import cl.claro.fvc.dto.DatosAcreditacionTipoDto;
import cl.claro.fvc.dto.DatosNumberLinesAllowedDto;
import cl.claro.fvc.dto.DatosResponseInfoDto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class DatosCreditScoreDocument {

  @Id
  private String uuid;
  @Indexed
  private String orderId;
  private DatosResponseInfoDto datosResponseInfoDto;
  private List<DatosNumberLinesAllowedDto> datosNumberLinesAllowedDto;
  private String numberOfFinancedActiveLines;
  private String reglasAplicadas;
  private String indicadorSiguiente;
  private String mensajeError;
  private String codiAcreditacion;
  private String descAcreditacion;
  private List<DatosAcreditacionTipoDto> extraValuesEntered;

  public DatosCreditScoreDocument() {
    //Constructor por defecto
  }

  @Override
  public String toString() {
    return "DatosCreditScoreDocument{" +
          "uuid='" + uuid + '\'' +
          ", orderId='" + orderId + '\'' +
          ", datosResponseInfoDto=" + datosResponseInfoDto +
          ", datosNumberLinesAllowedDto=" + datosNumberLinesAllowedDto +
          ", numberOfFinancedActiveLines='" + numberOfFinancedActiveLines + '\'' +
          ", reglasAplicadas='" + reglasAplicadas + '\'' +
          ", indicadorSiguiente='" + indicadorSiguiente + '\'' +
          ", mensajeError='" + mensajeError + '\'' +
          ", codiAcreditacion='" + codiAcreditacion + '\'' +
          ", descAcreditacion='" + descAcreditacion + '\'' +
          ", extraValuesEntered=" + extraValuesEntered +
          '}';
  }
}
