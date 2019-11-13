package com.leofuso.academico.cd.bancod.api.application.communication.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static java.util.Objects.requireNonNull;

@CommandApplicationEvent
public class DepositoCommand
        implements OperacaoBancariaCommand {

    private final Integer contaId;
    private final Double valor;

    private DepositoCommand(final Integer contaId,
                            final Double valor) {
        this.contaId = requireNonNull(contaId);
        this.valor = requireNonNull(valor);
    }

    @JsonCreator
    @SuppressWarnings("unused")
    public static DepositoCommand create(@JsonProperty("conta_id") final Integer contaId,
                                         @JsonProperty("valor") final Double valor) {
        return new DepositoCommand(contaId, valor);
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
