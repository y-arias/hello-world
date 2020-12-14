package cl.claro.fvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@AllArgsConstructor
@Builder
public class AccreditationDtoList {

    private String id;
    private String codiTipoAcreditacion;
    private String descTipoAcreditacion;
    private String fechInicioVigencia;
    private String codiUsuario;
    private String codiBiometria;
    private String indcDatosExtras;
    private String codiCatalogDms;
    private String indcActBiometria;

    public AccreditationDtoList() {
        //Constructor por defecto
    }
}
