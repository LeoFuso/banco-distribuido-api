package com.leofuso.academico.cd.bancod.api.domain.events;

import com.leofuso.academico.cd.bancod.api.domain.Conta;
import org.springframework.lang.Nullable;

import java.util.Objects;

@DomainEvent
public abstract class OperacaoBancaria {

    private final Conta conta;

    OperacaoBancaria(final Conta conta) {
        this.conta = requireNonNullValid(conta);
    }

    static Conta requireNonNullValid(@Nullable final Conta conta) {
        Objects.requireNonNull(conta, "Conta não pode ser [ null ]");
        Objects.requireNonNull(conta.getId(), "Conta ID não pode ser [ null ]");
        return conta;
    }

    public Conta getConta() {
        return conta;
    }
}
