package cl.claro.fvc.dto;

import lombok.Data;

@Data
public class DatosClienteDto {

  private String rutCliente;
  private String serieCedula;
  private String tipoCliente;
  private String fechaNacimiento;

  public DatosClienteDto(String rutCliente, String serieCedula, String tipoCliente, String fechaNacimiento) {
    this.rutCliente = rutCliente;
    this.serieCedula = serieCedula;
    this.tipoCliente = tipoCliente;
    this.fechaNacimiento = fechaNacimiento;
  }

  public DatosClienteDto() {
  }

  @Override
  public String toString() {
    return "DatosClienteDto{" +
          "rutCliente='" + rutCliente + '\'' +
          ", serieCedula='" + serieCedula + '\'' +
          ", tipoCliente='" + tipoCliente + '\'' +
          ", fechaNacimiento='" + fechaNacimiento + '\'' +
          '}';
  }
}
