package cl.claro.fvc.repository.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
public class DataTypeAccredDocument {

  @Id
  private String id;
  private String codiTipoAcreditacion;
  private String descTipoAcreditacion;
  private String fechInicioVigencia;
  private String codiUsuario;
  private String indcDatosExtras;
  private String codiCatalogDms;
  private String indcActBiometria;
  private String codiTipoCliente;
  @DBRef
  private DataDetailsAccredDocument details;

  public DataTypeAccredDocument() {
    //Constructor por defecto
  }

  @Override
  public String toString() {
    return "DataTypeAccredDocument{" +
          "id='" + id + '\'' +
          ", codiTipoAcreditacion='" + codiTipoAcreditacion + '\'' +
          ", descTipoAcreditacion='" + descTipoAcreditacion + '\'' +
          ", fechInicioVigencia='" + fechInicioVigencia + '\'' +
          ", codiUsuario='" + codiUsuario + '\'' +
          ", indcDatosExtras='" + indcDatosExtras + '\'' +
          ", codiCatalogDms='" + codiCatalogDms + '\'' +
          ", indcActBiometria='" + indcActBiometria + '\'' +
          ", codiTipoCliente='" + codiTipoCliente + '\'' +
          ", details=" + details +
          '}';
  }
}
