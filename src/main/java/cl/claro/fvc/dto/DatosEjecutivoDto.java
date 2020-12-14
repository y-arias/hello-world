package cl.claro.fvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DatosEjecutivoDto {

    private String rutEjecutivo;
    private String rolEjecutivo;
    private String codiCanal;

    public DatosEjecutivoDto() {
        //Constructor por defecto
    }

    public String getRutEjecutivo() {
        return rutEjecutivo;
    }

    public void setRutEjecutivo(String rutEjecutivo) {
        this.rutEjecutivo = rutEjecutivo;
    }

    public String getRolEjecutivo() {
        return rolEjecutivo;
    }

    public void setRolEjecutivo(String rolEjecutivo) {
        this.rolEjecutivo = rolEjecutivo;
    }

    public String getCodiCanal() {
        return codiCanal;
    }

    public void setCodiCanal(String codiCanal) {
        this.codiCanal = codiCanal;
    }



    @Override
    public String toString() {
        return "DatosEjecutivoDto{" +
                "rutEjecutivo='" + rutEjecutivo + '\'' +
                ", rolEjecutivo='" + rolEjecutivo + '\'' +
                ", codiCanal='" + codiCanal + '\'' +
                '}';
    }
}
