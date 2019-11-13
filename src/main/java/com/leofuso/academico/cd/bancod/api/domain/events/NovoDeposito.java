package com.leofuso.academico.cd.bancod.api.domain.events;

import com.leofuso.academico.cd.bancod.api.domain.Conta;

import static java.util.Objects.requireNonNull;

public class NovoDeposito
        extends OperacaoBancaria {

    private final Double valor;

    private NovoDeposito(final Double valor, final Conta conta) {
        super(conta);
        this.valor = requireNonNull(valor);
    }

    public static NovoDeposito produce(final Double valor, final Conta conta) {
        requireNonNull(valor, "NovoDeposito falhou: valor [ null ] ilegal");
        OperacaoBancaria.requireNonNullValid(conta);
        return new NovoDeposito(valor, conta);
    }

    public Double getValor() {
        return valor;
    }
}
