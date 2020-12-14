/*
 * Copyright (c) 2019.
 * Claro Chile
 * Creado por Sistemas ltda
 */
package cl.claro.fvc.util;

public final class Constantes {

  public static final String KAFKA_TOPIC_REQUEST = "fvc.domain.score.createevaluacion.request";
  public static final String KAFKA_TOPIC_RESPONSE = "fvc.integration.score.createevaluacion.response";
  public static final String KAFKA_TOPIC_GROUP_ID = "domain";
  public static final String KAFKA_MESSAGE_CODE_NOK = "002";

  public static final String LOG_INICIO_METODO = "score.log.formato.metodo.inicio";
  public static final String LOG_INICIO_METODO_ENTRADA = "score.log.formato.metodo.inicioentrada";
  public static final String LOG_FIN_METODO = "score.log.formato.metodo.final";
  public static final String LOG_FIN_METODO_SIMPLE = "score.log.formato.metodo.finalsimple";
  public static final String LOG_KAFKA_PRODUCCER_SUCCESS = "score.log.formato.kafka.producer.success";
  public static final String LOG_KAFKA_PRODUCCER_FAILURE = "score.log.formato.kafka.producer.failure";

  public static final String LOG_DATOS_GUARDADOS_MONGO = "Mensaje y datos ejecutivo guardados en MongoDb";
  public static final String LOG_MSJ_GUARDADO_MONGO = "Mensaje guardado en MongoDb";

  public static final String MSJ_RUT_INVALIDO = "score.mensaje.error.rutinvalido";
  public static final String OATYPE_VENTA = "Venta";
  public static final String OATYPE_PORTA = "Portabilidad";

  public static final String MONTOMAXDEUDA = "score.prop.montomaximodeuda";
  public static final String IND_DEUDAOK = "score.prop.indicadordeudaok";
  public static final String IND_DEUDANOK = "score.prop.indicadordeudanok";
  public static final String MSJ_ERRORDEUDA = "score.prop.msjerrordeuda";
  public static final String TIPOACREDITACION = "score.prop.tipoacreditacion";

  public static final String BIOMETRIC_VALIDATION_ALLOWED = "score.prop.biometricvalidationallowed";
  public static final String BIOMETRIC_VALIDATION_TYPE = "score.prop.biometricvalidationtype";

  public static final String CONS_TEXTO_S = "S";

  private Constantes() {

  }
}
