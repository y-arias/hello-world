package cl.claro.fvc.repository.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class RspDataScoreDocument {
  @Id
  private String uuid;
  private String codiRespuesta;
  private String descRespuesta;
  private String timeBegin;
  private String timeFinish;
  private DatosCreditScore datosCreditScore;

  public RspDataScoreDocument() {
    //Constructor por defecto
  }

  @Override
  public String toString() {
    return "RspDataScoreDocument{" +
          "uuid='" + uuid + '\'' +
          ", codiRespuesta='" + codiRespuesta + '\'' +
          ", descRespuesta='" + descRespuesta + '\'' +
          ", timeBegin='" + timeBegin + '\'' +
          ", timeFinish='" + timeFinish + '\'' +
          ", datosCreditScore=" + datosCreditScore +
          '}';
  }
}
