package com.leofuso.academico.cd.bancodistribuido.domain.events;

import com.leofuso.academico.cd.bancodistribuido.domain.Conta;
import lombok.Getter;

import java.util.Objects;

@Getter
public class NovoDeposito extends OperacaoBancaria {

    private final Double valor;

    private NovoDeposito(Double valor, Conta conta) {
        super(conta);
        this.valor = valor;
    }

    public static NovoDeposito produce(Double valor, Conta conta) {
        Objects.requireNonNull(valor, "NovoDeposito falhou: valor [ null ] illegal");
        OperacaoBancaria.requireNonNullValid(conta);
        return new NovoDeposito(valor, conta);
    }

}
