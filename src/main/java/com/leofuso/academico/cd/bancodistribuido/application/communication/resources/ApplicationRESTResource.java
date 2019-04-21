package com.leofuso.academico.cd.bancodistribuido.application.communication.resources;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Uma anotação de propósito informativo que indica um elemento
 * exclusivamente associado à um recurso disponível através
 * do protocolo RESTful
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ApplicationRESTResource {
}
