package cl.claro.fvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RequestScoreTo {

    private String oaType;
    private DatosAcreditacionDto accreditationInfoDto;
    private DatosClienteDto customerDto;
    private DatosEjecutivoDto userDto;
    private String indicadorBiometria;
    private DatosBiometriaDto biometricType;
    private String auditNumber;
    private String codiAcreditacion;
    private String descAcreditacion;

    public RequestScoreTo() {
        //Constructor por defecto
    }
}
