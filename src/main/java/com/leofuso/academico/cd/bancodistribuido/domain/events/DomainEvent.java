package com.leofuso.academico.cd.bancodistribuido.domain.events;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Uma anotação de propósito informativo que indica um elemento
 * responsável por encapsular todos os outros elementos associados à uma ação de negócio
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DomainEvent {}
