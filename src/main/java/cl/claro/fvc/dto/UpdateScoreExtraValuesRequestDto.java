package cl.claro.fvc.dto;

import lombok.*;

import java.util.List;

/**
 * Created by ChristianCarmonaQuin on 28-01-2020.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UpdateScoreExtraValuesRequestDto {
  private String idEvaluation;
  private List<DatosAcreditacionTipoDto> extraValues;
}
