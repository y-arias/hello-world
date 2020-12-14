package cl.claro.fvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatosAcreditacionTipoDto {

  private String codiAcredParam;
  private String accreditationLabel;
  private String accreditationValue;

}
