package com.leofuso.academico.cd.bancod.api.application.communication.commands;

import java.io.Serializable;

public interface OperacaoBancariaCommand extends Serializable {

    Integer getContaId();

    Double getValor();

}
