package com.leofuso.academico.cd.bancod.api.application.communication.controllers.exception;

import com.leofuso.academico.cd.bancod.api.application.communication.controllers.error.MessageValidationError;
import com.leofuso.academico.cd.bancod.api.application.communication.controllers.error.ResourceError;
import com.leofuso.academico.cd.bancod.api.application.exceptions.ComponentNotFoundException;
import com.leofuso.academico.cd.bancod.api.application.exceptions.DuplicateComponentException;
import com.leofuso.academico.cd.bancod.api.application.exceptions.OwnerOfRequestNotMatchRequestBody;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ComponentNotFoundException.class)
    public ResponseEntity<ResourceError> objectNotFound(final ComponentNotFoundException ex, final HttpServletRequest request) {
        return ResourceError.of(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(DuplicateComponentException.class)
    public ResponseEntity<ResourceError> duplicatedObject(final DuplicateComponentException ex, final HttpServletRequest request) {
        return ResourceError.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResourceError> dataIntegrityViolation(final DataIntegrityViolationException ex,
                                                                final HttpServletRequest request) {
        return ResourceError.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(OwnerOfRequestNotMatchRequestBody.class)
    public ResponseEntity<ResourceError> ownerOfRequestNotMatchRequestBodyError(final OwnerOfRequestNotMatchRequestBody ex,
                                                                                final HttpServletRequest request) {
        return ResourceError.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageValidationError> methodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                         final HttpServletRequest request) {
        return MessageValidationError.of(HttpStatus.BAD_REQUEST, ex.getBindingResult());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResourceError> methodArgumentNotValid(final HttpMessageNotReadableException ex,
                                                                final HttpServletRequest request) {
        return MessageValidationError.of(HttpStatus.BAD_REQUEST, ex.getCause().getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ResourceError> illegalStateError(final IllegalStateException ex, final HttpServletRequest request) {
        return ResourceError.of(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResourceError> illegalArgumentError(final IllegalArgumentException ex, final HttpServletRequest request) {
        return ResourceError.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
