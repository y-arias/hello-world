package cl.claro.fvc.dto;

import lombok.Data;

@Data
public class DatosBiometriaDto {
    private String auditNumber;
    private String isPreviredAllowed;
    private String type;

    public DatosBiometriaDto() {
        //Constructor por defecto
    }

    @Override
    public String toString() {
        return "DatosBiometriaDto{" +
                "auditNumber='" + auditNumber + '\'' +
                ", isPreviredAllowed='" + isPreviredAllowed + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
