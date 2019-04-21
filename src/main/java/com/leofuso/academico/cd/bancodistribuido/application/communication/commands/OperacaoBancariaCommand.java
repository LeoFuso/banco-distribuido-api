package com.leofuso.academico.cd.bancodistribuido.application.communication.commands;

import java.io.Serializable;

public interface OperacaoBancariaCommand extends Serializable {

    Integer getContaId();

    Double getValor();

}
