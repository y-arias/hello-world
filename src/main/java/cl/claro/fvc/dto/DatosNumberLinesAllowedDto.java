package cl.claro.fvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DatosNumberLinesAllowedDto {
    private String totalNumberOfLinesAllowed;
    private String gama;
    private String productType;

    public DatosNumberLinesAllowedDto() {
        //Constructor por defecto
    }
}
