package com.leofuso.academico.cd.bancodistribuido.application.communication.controllers.exception;

import com.leofuso.academico.cd.bancodistribuido.application.communication.controllers.error.MessageValidationError;
import com.leofuso.academico.cd.bancodistribuido.application.communication.controllers.error.ResourceError;
import com.leofuso.academico.cd.bancodistribuido.application.exceptions.ComponentNotFoundException;
import com.leofuso.academico.cd.bancodistribuido.application.exceptions.DuplicateComponentException;
import com.leofuso.academico.cd.bancodistribuido.application.exceptions.OwnerOfRequestNotMatchRequestBody;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ComponentNotFoundException.class)
    public ResponseEntity<ResourceError> objectNotFound(ComponentNotFoundException ex, HttpServletRequest request) {
        return ResourceError.of(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(DuplicateComponentException.class)
    public ResponseEntity<ResourceError> duplicatedObject(DuplicateComponentException ex, HttpServletRequest request) {
        return ResourceError.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResourceError> dataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest request) {
        return ResourceError.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(OwnerOfRequestNotMatchRequestBody.class)
    public ResponseEntity<ResourceError> ownerOfRequestNotMatchRequestBodyError(OwnerOfRequestNotMatchRequestBody ex, HttpServletRequest request) {
        return ResourceError.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageValidationError> methodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        return MessageValidationError.of(HttpStatus.BAD_REQUEST, ex.getBindingResult());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ResourceError> illegalStateError(IllegalStateException ex, HttpServletRequest request) {
        return ResourceError.of(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResourceError> illegalArgumentError(IllegalArgumentException ex, HttpServletRequest request) {
        return ResourceError.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
