package cl.claro.fvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseScoreTo {

  private String uuid;

  public ResponseScoreTo() {
    //Constructor por defecto
  }

  @Override
  public String toString() {
    return "ResponseScoreTo{" +
          "uuid='" + uuid + '\'' +
          '}';
  }
}
