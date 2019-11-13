package com.leofuso.academico.cd.bancod.api.application.factory;

import com.leofuso.academico.cd.bancod.api.application.communication.commands.OperacaoBancariaCommand;
import com.leofuso.academico.cd.bancod.api.application.communication.commands.TransferenciaCommand;
import com.leofuso.academico.cd.bancod.api.application.exceptions.ComponentNotFoundException;
import com.leofuso.academico.cd.bancod.api.domain.Conta;
import com.leofuso.academico.cd.bancod.api.domain.events.NovaTransferencia;
import com.leofuso.academico.cd.bancod.api.domain.events.NovoDeposito;
import com.leofuso.academico.cd.bancod.api.domain.events.NovoSaque;
import com.leofuso.academico.cd.bancod.api.domain.events.OperacaoBancaria;
import com.leofuso.academico.cd.bancod.api.domain.interfaces.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@ApplicationFactory
public class ContaFactory {

    private final ContaRepository repository;

    @Autowired
    public ContaFactory(final ContaRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public <T extends OperacaoBancaria, C extends OperacaoBancariaCommand> T enrich(final C command,
                                                                                    final Class<T> type) {

        if (command instanceof TransferenciaCommand) {
            return enrichTransferencia((TransferenciaCommand) command, type);
        }

        return enrichCommon(command, type);
    }

    private <T extends OperacaoBancaria, C extends OperacaoBancariaCommand> T enrichCommon(final C command,
                                                                                           final Class<T> type) {

        final Integer contaId = command.getContaId();
        final Double valor = command.getValor();

        final Conta conta = repository.findById(contaId)
                                      .orElseThrow(() -> new ComponentNotFoundException(contaId, Conta.class));

        final OperacaoBancaria operacaoBancaria;

        if (type.isAssignableFrom(NovoDeposito.class)) {
            operacaoBancaria = NovoDeposito.produce(valor, conta);
        } else if (type.isAssignableFrom(NovoSaque.class)) {
            operacaoBancaria = NovoSaque.produce(valor, conta);
        } else {
            throw new ClassCastException("Operação bancária inválida [ " + type.getSimpleName() + " ] ");
        }

        return type.cast(operacaoBancaria);
    }

    private <T extends OperacaoBancaria> T enrichTransferencia(final TransferenciaCommand command,
                                                               final Class<T> type) {

        final Integer contaOrigemId = command.getContaId();
        final Integer contaDestinoId = command.getContaDestinoId();
        final Double valor = command.getValor();

        final Conta contaOrigem = repository.findById(contaOrigemId)
                                            .orElseThrow(() -> new ComponentNotFoundException(contaOrigemId,
                                                                                              Conta.class));

        final Conta contaDestino = repository.findById(contaDestinoId)
                                             .orElseThrow(() -> new ComponentNotFoundException(contaDestinoId,
                                                                                               Conta.class));

        final NovaTransferencia novaTransferencia = NovaTransferencia.produce(valor, contaOrigem, contaDestino);
        return type.cast(novaTransferencia);
    }
}
