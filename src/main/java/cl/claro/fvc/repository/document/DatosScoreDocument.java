package cl.claro.fvc.repository.document;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class DatosScoreDocument {
  @Id
  private String rut;

  public DatosScoreDocument() {
    //Constructor por defecto
  }

  @Override
  public String toString() {
    return "DatosScoreDocument{" +
          "rut='" + rut + '\'' +
          '}';
  }
}
