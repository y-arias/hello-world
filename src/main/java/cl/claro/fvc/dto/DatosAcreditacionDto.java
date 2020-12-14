package cl.claro.fvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DatosAcreditacionDto {

    private String accreditationTypeCode;
    private List<DatosAcreditacionTipoDto> datosAcreditacionTipoDto;

    @Override
    public String toString() {
        return "DatosAcreditacionDto{" +
                "accreditationTypeCode='" + accreditationTypeCode + '\'' +
                ", datosAcreditacionTipoDto=" + datosAcreditacionTipoDto +
                '}';
    }
}
