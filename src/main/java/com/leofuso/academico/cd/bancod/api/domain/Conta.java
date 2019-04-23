package com.leofuso.academico.cd.bancod.api.domain;

import lombok.Getter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Entity
public class Conta implements Serializable {

    public static final String ID = "id";
    public static final String SALDO = "saldo";
    public static final String EMAIL = "email";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double saldo;

    @NaturalId
    private String email;

    Conta() { /* empty */ }

    public static Conta produce(Double saldoInicial, String email) {

        final Conta novaConta = new Conta();
        novaConta.saldo = Objects.requireNonNull(saldoInicial);
        novaConta.email = Objects.requireNonNull(email);

        return novaConta;
    }

    public void deposito(Double valor) {
        this.saldo += Objects.requireNonNull(valor);
    }

    public void saque(Double valor) {
        Objects.requireNonNull(valor);

        if (valor > this.saldo) {
            throw new IllegalArgumentException(String.format("Conta não possui saldo suficiente: Saldo [ %.2f ]", this.saldo));
        }

        this.saldo -= valor;
    }

    public void transferencia(Conta destino, Double valor) {
        Objects.requireNonNull(destino);
        Objects.requireNonNull(valor);

        if (this.equals(destino)) {
            throw new IllegalArgumentException("Transferências internas não são permitidas");
        }

        this.saque(valor);
        destino.deposito(valor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return Objects.equals(id, conta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
