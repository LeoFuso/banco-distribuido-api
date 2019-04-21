package com.leofuso.academico.cd.bancodistribuido.application.factory;

import com.leofuso.academico.cd.bancodistribuido.application.communication.commands.OperacaoBancariaCommand;
import com.leofuso.academico.cd.bancodistribuido.application.communication.commands.TransferenciaCommand;
import com.leofuso.academico.cd.bancodistribuido.application.exceptions.ComponentNotFoundException;
import com.leofuso.academico.cd.bancodistribuido.domain.Conta;
import com.leofuso.academico.cd.bancodistribuido.domain.events.NovaTransferencia;
import com.leofuso.academico.cd.bancodistribuido.domain.events.NovoDeposito;
import com.leofuso.academico.cd.bancodistribuido.domain.events.NovoSaque;
import com.leofuso.academico.cd.bancodistribuido.domain.events.OperacaoBancaria;
import com.leofuso.academico.cd.bancodistribuido.domain.interfaces.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@ApplicationFactory
public class ContaFactory {

    private final ContaRepository repository;

    @Autowired
    public ContaFactory(ContaRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public <T extends OperacaoBancaria, C extends OperacaoBancariaCommand> T enrich(C command, Class<T> type) {

        if (command instanceof TransferenciaCommand)
            return enrichTransferencia((TransferenciaCommand) command, type);

        return enrichCommon(command, type);
    }

    private <T extends OperacaoBancaria, C extends OperacaoBancariaCommand> T enrichCommon(C command, Class<T> type) {

        @NotNull final Integer contaId = command.getContaId();
        @NotNull final Double valor = command.getValor();

        Conta conta = repository.findById(contaId)
                .orElseThrow(() -> new ComponentNotFoundException(contaId, Conta.class));

        final OperacaoBancaria operacaoBancaria;

        if (type.isAssignableFrom(NovoDeposito.class))
            operacaoBancaria = NovoDeposito.produce(valor, conta);
        else if (type.isAssignableFrom(NovoSaque.class))
            operacaoBancaria = NovoSaque.produce(valor, conta);
        else
            throw new ClassCastException("Operação bancária inválida [ " + type.getSimpleName() + " ] ");

        return type.cast(operacaoBancaria);
    }

    private <T extends OperacaoBancaria> T enrichTransferencia(TransferenciaCommand command, Class<T> type) {

        @NotNull final Integer contaOrigemId = command.getContaOrigemId();
        @NotNull final Integer contaDestinoId = command.getContaDestinoId();
        @NotNull final Double valor = command.getValor();

        Conta contaOrigem = repository.findById(contaOrigemId)
                .orElseThrow(() -> new ComponentNotFoundException(contaOrigemId, Conta.class));

        Conta contaDestino = repository.findById(contaDestinoId)
                .orElseThrow(() -> new ComponentNotFoundException(contaDestinoId, Conta.class));

        final NovaTransferencia novaTransferencia = NovaTransferencia.produce(valor, contaOrigem, contaDestino);
        return type.cast(novaTransferencia);
    }
}
