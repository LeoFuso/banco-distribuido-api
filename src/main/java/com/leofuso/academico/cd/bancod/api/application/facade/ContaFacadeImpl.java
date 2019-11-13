package com.leofuso.academico.cd.bancod.api.application.facade;

import com.leofuso.academico.cd.bancod.api.application.communication.commands.DepositoCommand;
import com.leofuso.academico.cd.bancod.api.application.communication.commands.OperacaoBancariaCommand;
import com.leofuso.academico.cd.bancod.api.application.communication.commands.SaqueCommand;
import com.leofuso.academico.cd.bancod.api.application.communication.commands.TransferenciaCommand;
import com.leofuso.academico.cd.bancod.api.application.communication.resources.ContaResource;
import com.leofuso.academico.cd.bancod.api.application.exceptions.OwnerOfRequestNotMatchRequestBody;
import com.leofuso.academico.cd.bancod.api.application.factory.ContaFactory;
import com.leofuso.academico.cd.bancod.api.domain.Conta;
import com.leofuso.academico.cd.bancod.api.domain.events.NovaTransferencia;
import com.leofuso.academico.cd.bancod.api.domain.events.NovoDeposito;
import com.leofuso.academico.cd.bancod.api.domain.events.NovoSaque;
import com.leofuso.academico.cd.bancod.api.domain.interfaces.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@ApplicationFacade
public class ContaFacadeImpl
        implements ContaFacade {

    private final ContaService service;
    private final ContaFactory factory;

    @Autowired
    public ContaFacadeImpl(final ContaService service, final ContaFactory factory) {
        this.service = requireNonNull(service);
        this.factory = requireNonNull(factory);
    }

    @Override
    public void deposito(final Integer id, final DepositoCommand command) {
        verificarIntegridade(id, command);

        final NovoDeposito novoDeposito = factory.enrich(command, NovoDeposito.class);
        service.deposito(novoDeposito);
    }

    @Override
    public void saque(final Integer id, final SaqueCommand command) {
        verificarIntegridade(id, command);

        final NovoSaque novoSaque = factory.enrich(command, NovoSaque.class);
        service.saque(novoSaque);

    }

    @Override
    public void transferencia(final Integer id, final TransferenciaCommand command) {
        verificarIntegridade(id, command);

        final NovaTransferencia novaTransferencia = factory.enrich(command, NovaTransferencia.class);
        service.transferencia(novaTransferencia);

    }

    @Override
    public List<ContaResource> findAll() {
        return service.findAll().stream()
                      .map(ContaResource::from)
                      .collect(Collectors.toList());
    }

    @Override
    public ContaResource findOneById(final Integer id) {
        Assert.notNull(id, "Conta ID não pode ser nulo");

        final Conta conta = service.findOneById(id);
        return ContaResource.from(conta);
    }

    private void verificarIntegridade(final Integer id, final OperacaoBancariaCommand command) {
        Assert.notNull(id, "Conta ID não pode ser nulo");
        Assert.notNull(command, "Comando não pode ser nulo");

        final Integer idFound = command.getContaId();
        final boolean idInCommandIsWrong = !id.equals(idFound);

        if (idInCommandIsWrong) {
            throw new OwnerOfRequestNotMatchRequestBody("Conta [ " + id + " ] não corresponde ao " +
                                                                "alvo do requerimento [ " + idFound + " ]");
        }
    }
}
