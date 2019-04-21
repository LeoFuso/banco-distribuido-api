package com.leofuso.academico.cd.bancodistribuido.application.exceptions;

public class ComponentNotFoundException extends RuntimeException {

    public ComponentNotFoundException() {
        super();
    }

    public ComponentNotFoundException(String s) {
        super(s);
    }

    public ComponentNotFoundException(String s, Exception e) {
        super(s, e);
    }

    public ComponentNotFoundException(Object identificadorUnico, Class componentNotFound) {
        super("Objeto [ " + componentNotFound.getSimpleName() + " ] não pôde" +
                " ser encontrado sobre o identificador único [ " + identificadorUnico + " ]");
    }

    public ComponentNotFoundException(Class componentNotFound) {
        super("Objeto [ " + componentNotFound.getSimpleName() + " ] não pôde ser encontrado");
    }
}
