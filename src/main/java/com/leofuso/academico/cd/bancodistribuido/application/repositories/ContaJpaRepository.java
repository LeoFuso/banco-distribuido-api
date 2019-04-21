package com.leofuso.academico.cd.bancodistribuido.application.repositories;

import com.leofuso.academico.cd.bancodistribuido.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaJpaRepository extends JpaRepository<Conta, Integer> {}
