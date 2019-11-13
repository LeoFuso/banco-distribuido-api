package com.leofuso.academico.cd.bancod.api.application.communication.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@CommandApplicationEvent
public class SaqueCommand
        implements OperacaoBancariaCommand {

    private final Integer contaId;
    private final Double valor;

    private SaqueCommand(final Integer contaId,
                         final Double valor) {
        this.contaId = contaId;
        this.valor = valor;
    }

    @JsonCreator
    @SuppressWarnings("unused")
    public static SaqueCommand create(@JsonProperty("conta_id") final Integer contaId,
                                       @JsonProperty("valor") final Double valor) {
        return new SaqueCommand(contaId, valor);
    }

    @Override
    public Integer getContaId() {
        return contaId;
    }

    @Override
    public Double getValor() {
        return valor;
    }
}
