package cl.claro.fvc.repository.document;

import cl.claro.fvc.dto.DetailsAccreditationDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
@AllArgsConstructor
@Builder
public class DataDetailsAccredDocument {

  @Id
  private String codiTipoAcreditacion;
  private List<DetailsAccreditationDto> detailsAccreditationDtos;

  public DataDetailsAccredDocument() {
    //Constructor por defecto
  }

  @Override
  public String toString() {
    return "DataDetailsAccredDocument{" +
            "codiTipoAcreditacion='" + codiTipoAcreditacion + '\'' +
            ", detailsAccreditationDtos=" + detailsAccreditationDtos +
            '}';
  }
}
