package com.leofuso.academico.cd.bancod.api.domain.events;

import com.leofuso.academico.cd.bancod.api.domain.Conta;

import java.util.Objects;

public class NovaTransferencia
        extends OperacaoBancaria {

    private final Conta contaDestino;
    private final Double valor;

    private NovaTransferencia(Double valor, Conta conta, Conta contaDestino) {
        super(conta);
        this.valor = valor;
        this.contaDestino = contaDestino;
    }

    public static NovaTransferencia produce(Double valor, Conta conta, Conta contaDestino) {
        Objects.requireNonNull(valor, "NovoSaque falhou: valor [ null ] ilegal");
        OperacaoBancaria.requireNonNullValid(conta);
        OperacaoBancaria.requireNonNullValid(contaDestino);
        return new NovaTransferencia(valor, conta, contaDestino);
    }

    public Conta getContaDestino() {
        return contaDestino;
    }

    public Double getValor() {
        return valor;
    }

}
