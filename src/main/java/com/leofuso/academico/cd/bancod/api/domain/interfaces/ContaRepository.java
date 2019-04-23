package com.leofuso.academico.cd.bancod.api.domain.interfaces;

import com.leofuso.academico.cd.bancod.api.domain.Conta;

import java.util.List;
import java.util.Optional;

public interface ContaRepository {

    Optional<Conta> findById(Integer id);

    Conta saveAndFlush(Conta conta);

    List<Conta> findAll();

}
