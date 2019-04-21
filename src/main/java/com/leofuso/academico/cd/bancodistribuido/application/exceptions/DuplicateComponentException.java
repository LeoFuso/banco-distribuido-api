package com.leofuso.academico.cd.bancodistribuido.application.exceptions;

public class DuplicateComponentException extends RuntimeException {

    public DuplicateComponentException() {
        super();
    }

    public DuplicateComponentException(String s) {
        super(s);
    }

    public <T> DuplicateComponentException(String identificadorUnico, T componenteDuplicado) {
        super("Componente [ " + componenteDuplicado.getClass().getSimpleName() + " ] já " +
                " registrado sobre o identificador único [ " + identificadorUnico + " ]");
    }

    public <T> DuplicateComponentException(Class<T> componenteDuplicado) {
        super("Componente [ " + componenteDuplicado.getSimpleName() + " ] já " +
                " registrado");
    }
}
