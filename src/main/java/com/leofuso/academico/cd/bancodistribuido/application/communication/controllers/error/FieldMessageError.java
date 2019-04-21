package com.leofuso.academico.cd.bancodistribuido.application.communication.controllers.error;

import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.util.Optional;

class FieldMessageError implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String field;
    private final String value;
    private final String message;

    private FieldMessageError(String field, String value, String message) {
        this.field = field;
        this.value = value;
        this.message = message;
    }

    static FieldMessageError of(FieldError fieldError) {
        String field = fieldError.getField();
        String value = Optional.ofNullable(fieldError.getRejectedValue())
                .orElse("null")
                .toString();
        String message = fieldError.getDefaultMessage();
        return new FieldMessageError(field, value, message);
    }

    public String getField() {
        return field;
    }

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

}
