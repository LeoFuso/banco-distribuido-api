package com.leofuso.academico.cd.bancodistribuido.domain.events;

import com.leofuso.academico.cd.bancodistribuido.domain.Conta;
import lombok.Getter;

import java.util.Objects;

@Getter
public class NovoSaque extends OperacaoBancaria {

    private final Double valor;

    private NovoSaque(Double valor, Conta conta) {
        super(conta);
        this.valor = valor;
    }

    public static NovoSaque produce(Double valor, Conta conta) {
        Objects.requireNonNull(valor, "NovoSaque falhou: valor [ null ] illegal");
        OperacaoBancaria.requireNonNullValid(conta);
        return new NovoSaque(valor, conta);
    }

}
