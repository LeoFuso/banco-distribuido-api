package com.leofuso.academico.cd.bancod.api.application.communication.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import static java.util.Objects.requireNonNull;

@CommandApplicationEvent
public class TransferenciaCommand
        implements OperacaoBancariaCommand {

    private final Integer contaOrigemId;
    private final Integer contaDestinoId;
    private final Double valor;

    private TransferenciaCommand(final Integer contaOrigemId,
                                 final Integer contaDestinoId,
                                 final Double valor) {
        this.contaOrigemId = requireNonNull(contaOrigemId);
        this.contaDestinoId = requireNonNull(contaDestinoId);
        this.valor = requireNonNull(valor);
    }

    @JsonCreator
    @SuppressWarnings("unused")
    public static TransferenciaCommand create(@JsonProperty("conta_origem_id") final Integer contaOrigemId,
                                              @JsonProperty("conta_destino_id") final Integer contaDestinoId,
                                              @JsonProperty("valor") final Double valor) {
        return new TransferenciaCommand(contaOrigemId, contaDestinoId, valor);
    }

    @Override
    @JsonIgnore
    public Integer getContaId() {
        return contaOrigemId;
    }

    @Override
    public Double getValor() {
        return valor;
    }

    public Integer getContaDestinoId() {
        return contaDestinoId;
    }
}
