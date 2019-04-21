package com.leofuso.academico.cd.bancodistribuido.application.communication.commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Uma anotação de propósito informativo que indica um elemento
 * exclusivamente associado à uma ação de negócio
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandApplicationEvent {}
