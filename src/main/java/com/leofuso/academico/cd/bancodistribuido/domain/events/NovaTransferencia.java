package com.leofuso.academico.cd.bancodistribuido.domain.events;

import com.leofuso.academico.cd.bancodistribuido.domain.Conta;
import lombok.Getter;

import java.util.Objects;

@Getter
public class NovaTransferencia extends OperacaoBancaria {

    private final Conta contaDestino;
    private final Double valor;

    private NovaTransferencia(Double valor, Conta conta, Conta contaDestino) {
        super(conta);
        this.valor = valor;
        this.contaDestino = contaDestino;
    }

    public static NovaTransferencia produce(Double valor, Conta conta, Conta contaDestino) {
        Objects.requireNonNull(valor, "NovoSaque falhou: valor [ null ] illegal");
        OperacaoBancaria.requireNonNullValid(conta);
        OperacaoBancaria.requireNonNullValid(contaDestino);
        return new NovaTransferencia(valor, conta, contaDestino);
    }

}
