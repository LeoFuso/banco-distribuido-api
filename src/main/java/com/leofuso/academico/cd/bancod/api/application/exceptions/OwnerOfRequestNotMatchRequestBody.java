package com.leofuso.academico.cd.bancod.api.application.exceptions;

public class OwnerOfRequestNotMatchRequestBody extends RuntimeException {

    public OwnerOfRequestNotMatchRequestBody() {
        super();
    }

    public OwnerOfRequestNotMatchRequestBody(String message) {
        super(message);
    }
}
