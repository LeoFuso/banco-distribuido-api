package com.leofuso.academico.cd.bancod.api.application.communication.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@CommandApplicationEvent
public class SaqueCommand implements OperacaoBancariaCommand {

    @NotNull
    private final Integer contaId;

    @NotNull
    private final Double valor;

    private SaqueCommand(@NotNull Integer contaId,
                         @NotNull Double valor) {
        this.contaId = contaId;
        this.valor = valor;
    }

    @JsonCreator
    public static SaqueCommand create(@JsonProperty("conta_id") Integer contaId,
                                      @JsonProperty("valor") Double valor) {
        return new SaqueCommand(contaId, valor);
    }
}
