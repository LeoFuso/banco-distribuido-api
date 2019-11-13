package com.leofuso.academico.cd.bancod.api.domain;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Conta
        implements Serializable {

    public static final String ID = "id";

    public static final String SALDO = "saldo";

    public static final String EMAIL = "email";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double saldo;

    @NaturalId
    private String email;

    @SuppressWarnings("WeakerAccess")
    Conta() { /* Hibernate */ }

    public static Conta produce(final Double saldoInicial, final String email) {

        final Conta novaConta = new Conta();
        novaConta.saldo = Objects.requireNonNull(saldoInicial);
        novaConta.email = Objects.requireNonNull(email);

        return novaConta;
    }

    public void deposito(final Double valor) {
        saldo += Conta.requireNonNullAndPositiveValue(valor);
    }

    public void saque(final Double valor) {
        Conta.requireNonNullAndPositiveValue(valor);

        if (valor > saldo) {
            throw new IllegalArgumentException(String.format("Conta não possui saldo suficiente: Saldo [ %.2f ]",
                                                             saldo));
        }

        saldo -= valor;
    }

    public void transferencia(final Conta destino, final Double valor) {
        Objects.requireNonNull(destino);
        Objects.requireNonNull(valor);

        if (equals(destino)) {
            throw new IllegalArgumentException("Transferências internas não são permitidas");
        }

        saque(valor);
        destino.deposito(valor);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Conta conta = (Conta) o;
        return Objects.equals(id, conta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private static Double requireNonNullAndPositiveValue(final Double value) {
        final Double nonNullValue = Objects.requireNonNull(value);
        final Double inferiorLimit = 0.0d;
        if (inferiorLimit.compareTo(nonNullValue) >= 0) {
            throw new IllegalArgumentException("Valor não pode ser negativo ou 0");
        }
        return nonNullValue;
    }

    public Integer getId() {
        return id;
    }

    public Double getSaldo() {
        return saldo;
    }

    public String getEmail() {
        return email;
    }
}
