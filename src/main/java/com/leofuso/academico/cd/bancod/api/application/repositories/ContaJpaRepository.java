package com.leofuso.academico.cd.bancod.api.application.repositories;

import com.leofuso.academico.cd.bancod.api.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaJpaRepository extends JpaRepository<Conta, Integer> {}
