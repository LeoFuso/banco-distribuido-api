package com.leofuso.academico.cd.bancod.api.application.repositories;

import com.leofuso.academico.cd.bancod.api.domain.Conta;
import com.leofuso.academico.cd.bancod.api.domain.interfaces.ContaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Repository
public class ContaRepositoryImpl implements ContaRepository {

    private final ContaJpaRepository jpaRepository;

    public ContaRepositoryImpl(final ContaJpaRepository jpaRepository) {
        this.jpaRepository = requireNonNull(jpaRepository);
    }

    @Override
    public Optional<Conta> findById(final Integer id) {
        Assert.notNull(id, "Argument is obligatory");
        return jpaRepository.findById(id);
    }

    @Override
    public Conta saveAndFlush(final Conta conta) {
        Assert.notNull(conta, "Argument is obligatory");
        return jpaRepository.saveAndFlush(conta);
    }

    @Override
    public List<Conta> findAll() {
        return jpaRepository.findAll();
    }

}
