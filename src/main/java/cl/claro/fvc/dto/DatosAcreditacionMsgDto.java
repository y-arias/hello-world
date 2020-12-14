package cl.claro.fvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by ChristianCarmonaQuin on 23-01-2020.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DatosAcreditacionMsgDto {
  private String accreditationName;
  private List<DatosAcreditacionTipoDto> datosAcreditacionTipoDto;

  @Override
  public String toString() {
    return "DatosAcreditacionMsgDto{" +
          "accreditationName='" + accreditationName + '\'' +
          ", datosAcreditacionTipoDto=" + datosAcreditacionTipoDto +
          '}';
  }
}
