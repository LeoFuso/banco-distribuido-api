package com.leofuso.academico.cd.bancodistribuido.application.communication.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.leofuso.academico.cd.bancodistribuido.domain.Conta;

import java.io.Serializable;
import java.util.Objects;

@ApplicationRESTResource
public class ContaResource implements Serializable {

    private final Integer id;
    private final Double saldo;
    private final String email;

    private ContaResource(Integer id, Double saldo, String email) {
        this.id = Objects.requireNonNull(id);
        this.saldo = Objects.requireNonNull(saldo);
        this.email = Objects.requireNonNull(email);
    }

    public static ContaResource from(Conta conta) {
        return new ContaResource(conta.getId(), conta.getSaldo(), conta.getEmail());
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("saldo")
    public Double getSaldo() {
        return saldo;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }
}
