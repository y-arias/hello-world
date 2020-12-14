package cl.claro.fvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class AcreditacionInfoDto {

  private List<AcreditacionDataDto> acreditacionDataDtos;
  private String accreditationName;

  public AcreditacionInfoDto() {
    //Constructor por defecto
  }

  @Override
  public String toString() {
    return "AcreditacionInfoDto{" +
          "acreditacionDataDtos=" + acreditacionDataDtos +
          ", accreditationName='" + accreditationName + '\'' +
          '}';
  }
}
