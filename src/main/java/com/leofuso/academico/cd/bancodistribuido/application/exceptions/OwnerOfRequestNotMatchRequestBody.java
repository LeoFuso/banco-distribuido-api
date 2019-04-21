package com.leofuso.academico.cd.bancodistribuido.application.exceptions;

public class OwnerOfRequestNotMatchRequestBody extends RuntimeException {

    public OwnerOfRequestNotMatchRequestBody() {
        super();
    }

    public OwnerOfRequestNotMatchRequestBody(String message) {
        super(message);
    }
}
