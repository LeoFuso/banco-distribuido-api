package com.leofuso.academico.cd.bancod.api.application.communication.controllers.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

class MessageValidationErrorBuilder
        extends AbstractResourceErrorBuilder<MessageValidationErrorBuilder, MessageValidationError> {

    private final List<FieldMessageError> errors;

    MessageValidationErrorBuilder(final HttpStatus status, final String cause, final BindingResult bindingResult) {
        super(status, cause);

        errors = bindingResult.getFieldErrors().stream()
                              .map(FieldMessageError::of)
                              .collect(Collectors.toList());
    }

    @Override
    ResponseEntity<MessageValidationError> build() {
        return ResponseEntity
                .status(super.getCode())
                .body(new MessageValidationError(this));
    }

    List<FieldMessageError> getErrors() {
        return errors;
    }
}
