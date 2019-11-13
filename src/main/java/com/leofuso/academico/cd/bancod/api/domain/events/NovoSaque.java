package com.leofuso.academico.cd.bancod.api.domain.events;

import com.leofuso.academico.cd.bancod.api.domain.Conta;

import static java.util.Objects.requireNonNull;

public class NovoSaque
        extends OperacaoBancaria {

    private final Double valor;

    private NovoSaque(final Double valor, final Conta conta) {
        super(conta);
        this.valor = requireNonNull(valor);
    }

    public static NovoSaque produce(final Double valor, final Conta conta) {
        requireNonNull(valor, "NovoSaque falhou: valor [ null ] ilegal");
        OperacaoBancaria.requireNonNullValid(conta);
        return new NovoSaque(valor, conta);
    }

    public Double getValor() {
        return valor;
    }
}
