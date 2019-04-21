package com.leofuso.academico.cd.bancodistribuido.application.communication.controllers.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

abstract class AbstractResourceErrorBuilder<B extends AbstractResourceErrorBuilder, E extends ResourceError> {

    private final Integer code;
    private final String status;
    private final String cause;
    private final LocalDateTime timestamp;

    AbstractResourceErrorBuilder(HttpStatus status, String cause) {
        this.code = status.value();
        this.status = status.getReasonPhrase();
        this.timestamp = LocalDateTime.now();
        this.cause = cause;
    }

    abstract ResponseEntity<E> build();

    Integer getCode() {
        return code;
    }

    String getStatus() {
        return status;
    }

    String getCause() {
        return cause;
    }

    LocalDateTime getTimestamp() {
        return timestamp;
    }
}
