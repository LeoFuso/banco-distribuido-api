package com.leofuso.academico.cd.bancodistribuido.application.communication.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@CommandApplicationEvent
public class TransferenciaCommand implements OperacaoBancariaCommand {

    @NotNull
    private final Integer contaOrigemId;

    @NotNull
    private final Integer contaDestinoId;

    @NotNull
    private final Double valor;

    private TransferenciaCommand(@NotNull Integer contaOrigemId,
                                 @NotNull Integer contaDestinoId,
                                 @NotNull Double valor) {
        this.contaOrigemId = contaOrigemId;
        this.contaDestinoId = contaDestinoId;
        this.valor = valor;
    }

    public static TransferenciaCommand create(@JsonProperty("conta_origem_id") Integer contaOrigemId,
                                              @JsonProperty("conta_destino_id") Integer contaDestinoId,
                                              @JsonProperty("valor") Double valor) {
        return new TransferenciaCommand(contaOrigemId, contaDestinoId, valor);
    }

    @Override
    public Integer getContaId() {
        return contaOrigemId;
    }
}
