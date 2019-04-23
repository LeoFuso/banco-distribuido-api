package com.leofuso.academico.cd.bancod.api.domain.events;

import com.leofuso.academico.cd.bancod.api.domain.Conta;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.util.Objects;

@Getter
@DomainEvent
public abstract class OperacaoBancaria {

    private final Conta conta;

    OperacaoBancaria(Conta conta) {
        this.conta = conta;
    }

    static void requireNonNullValid(@Nullable Conta conta) {
        Objects.requireNonNull(conta, "Conta não pode ser [ null ]");
        Objects.requireNonNull(conta.getId(), "Conta ID não pode ser [ null ]");
    }

}
