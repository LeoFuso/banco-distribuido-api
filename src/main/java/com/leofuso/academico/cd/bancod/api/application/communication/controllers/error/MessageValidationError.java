package com.leofuso.academico.cd.bancod.api.application.communication.controllers.error;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public class MessageValidationError extends ResourceError {

    private static final long serialVersionUID = 1L;

    private final List<FieldMessageError> errors;

    MessageValidationError(MessageValidationErrorBuilder builder) {
        super(builder.getCode(), builder.getStatus(), builder.getTimestamp(), builder.getCause());
        this.errors = builder.getErrors();
    }

    public static ResponseEntity<MessageValidationError> of(HttpStatus status, BindingResult result) {
        return new MessageValidationErrorBuilder(status, null, result)
                .build();
    }

    public List<FieldMessageError> getErrors() {
        return errors;
    }
}
