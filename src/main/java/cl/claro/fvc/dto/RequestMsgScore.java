package cl.claro.fvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RequestMsgScore {

    private String uuid;
    private String oaType;
    private DatosAcreditacionMsgDto accreditationInfoDto;
    private DatosClienteDto customerDto;
    private DatosEjecutivoDto userDto;
    private String indicadorBiometria;
    private DatosBiometriaDto biometricType;
    private String codiAcreditacion;
    private String descAcreditacion;


    public RequestMsgScore() {
        //Constructor por defecto.
    }

    @Override
    public String toString() {
        return "RequestMsgScore{" +
                "uuid='" + uuid + '\'' +
                ", oaType='" + oaType + '\'' +
                ", accreditationInfoDto=" + accreditationInfoDto +
                ", customerDto=" + customerDto +
                ", userDto=" + userDto +
                ", indicadorBiometria='" + indicadorBiometria + '\'' +
                ", biometricType=" + biometricType +
                '}';
    }
}
