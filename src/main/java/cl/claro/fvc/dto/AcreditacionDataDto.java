package cl.claro.fvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AcreditacionDataDto {

    private String accreditationLabel;
    private String accreditationValue;
}
