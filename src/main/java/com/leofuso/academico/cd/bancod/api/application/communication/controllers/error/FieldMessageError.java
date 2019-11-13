package com.leofuso.academico.cd.bancod.api.application.communication.controllers.error;

import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.util.Optional;

class FieldMessageError implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String field;
    private final String value;
    private final String message;

    private FieldMessageError(final String field, final String value, final String message) {
        this.field = field;
        this.value = value;
        this.message = message;
    }

    static FieldMessageError of(final FieldError fieldError) {
        final String field = fieldError.getField();
        final String value = Optional.ofNullable(fieldError.getRejectedValue())
                                     .orElse("null")
                                     .toString();
        final String message = fieldError.getDefaultMessage();
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
