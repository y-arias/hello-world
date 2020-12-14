package cl.claro.fvc.dto;

import cl.claro.fvc.repository.document.DatosCreditScore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RspScoreMsgeDto {
    private String uuid;
    private String codiRespuesta;
    private String descRespuesta;
    private String timeBegin;
    private String timeFinish;
    private DatosCreditScore datosCreditScore;

    public RspScoreMsgeDto() {
        //Constructor Generico
    }
}
