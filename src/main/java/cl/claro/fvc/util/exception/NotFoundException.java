
/*
 * Copyright (c) 2019.
 * Claro Chile
 * Creado por Sistemas ltda
 */

package cl.claro.fvc.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Recurso no encontrado")
public class NotFoundException extends RuntimeException {

    public NotFoundException() {

        super();
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }
}
