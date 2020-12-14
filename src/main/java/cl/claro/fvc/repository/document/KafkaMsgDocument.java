package cl.claro.fvc.repository.document;

import cl.claro.fvc.dto.DatosClienteDto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class KafkaMsgDocument {
  @Id
  private String uuid;
  private String codiRespuesta;
  private String descRespuesta;
  private String timeBegin;
  private String timeFinish;
  private DatosClienteDto datosClienteDto;

  public KafkaMsgDocument() {
    //Constructor por defecto
  }

  @Override
  public String toString() {
    return "KafkaMsgDocument{" +
          "uuid='" + uuid + '\'' +
          ", codiRespuesta='" + codiRespuesta + '\'' +
          ", descRespuesta='" + descRespuesta + '\'' +
          ", timeBegin='" + timeBegin + '\'' +
          ", timeFinish='" + timeFinish + '\'' +
          ", datosClienteDto=" + datosClienteDto +
          '}';
  }
}
