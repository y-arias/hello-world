package cl.claro.fvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DatosExpiredDueListDto {
    private String expiredDueAmount;
    private String expiredDueDate;

    //Constructor por defecto
}
