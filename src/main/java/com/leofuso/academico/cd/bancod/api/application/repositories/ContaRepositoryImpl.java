package com.leofuso.academico.cd.bancod.api.application.repositories;

import com.leofuso.academico.cd.bancod.api.domain.Conta;
import com.leofuso.academico.cd.bancod.api.domain.interfaces.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ContaRepositoryImpl implements ContaRepository {

    private final ContaJpaRepository jpaRepository;

    @Autowired
    public ContaRepositoryImpl(ContaJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Conta> findById(Integer id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Conta saveAndFlush(Conta conta) {
        return jpaRepository.saveAndFlush(conta);
    }

    @Override
    public List<Conta> findAll() {
        return jpaRepository.findAll();
    }

}
