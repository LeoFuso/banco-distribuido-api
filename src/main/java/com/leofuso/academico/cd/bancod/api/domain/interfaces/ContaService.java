package com.leofuso.academico.cd.bancod.api.domain.interfaces;

import com.leofuso.academico.cd.bancod.api.domain.Conta;
import com.leofuso.academico.cd.bancod.api.domain.events.NovaTransferencia;
import com.leofuso.academico.cd.bancod.api.domain.events.NovoDeposito;
import com.leofuso.academico.cd.bancod.api.domain.events.NovoSaque;

import java.util.List;

public interface ContaService {

    void deposito(NovoDeposito event);

    void saque(NovoSaque event);

    void transferencia(NovaTransferencia event);

    Conta findOneById(Integer conta);

    List<Conta> findAll();

}
