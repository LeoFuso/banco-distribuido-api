package com.leofuso.academico.cd.bancodistribuido.application.communication.controllers.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.leofuso.academico.cd.bancodistribuido.application.communication.converters.LocaleDateTimeStdSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public class ResourceError implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Integer code;
    private final String status;

    @JsonSerialize(using = LocaleDateTimeStdSerializer.class)
    private final LocalDateTime timestamp;

    @JsonInclude(NON_NULL)
    private final String cause;

    ResourceError(Integer code, String status, LocalDateTime timestamp, String cause) {
        this.code = code;
        this.status = status;
        this.timestamp = timestamp;
        this.cause = cause;
    }

    ResourceError(ResourceErrorBuilder responseErrorBuilder) {
        this.code = responseErrorBuilder.getCode();
        this.status = responseErrorBuilder.getStatus();
        this.timestamp = responseErrorBuilder.getTimestamp();
        this.cause = responseErrorBuilder.getCause();
    }

    public static ResponseEntity<ResourceError> of(HttpStatus status, String cause) {
        return new ResourceErrorBuilder(status, cause)
                .build();
    }

    public Integer getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getCause() {
        return cause;
    }
}
