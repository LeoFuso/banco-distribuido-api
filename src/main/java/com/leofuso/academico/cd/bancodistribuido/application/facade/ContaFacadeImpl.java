package com.leofuso.academico.cd.bancodistribuido.application.facade;

import com.leofuso.academico.cd.bancodistribuido.application.communication.commands.DepositoCommand;
import com.leofuso.academico.cd.bancodistribuido.application.communication.commands.OperacaoBancariaCommand;
import com.leofuso.academico.cd.bancodistribuido.application.communication.commands.SaqueCommand;
import com.leofuso.academico.cd.bancodistribuido.application.communication.commands.TransferenciaCommand;
import com.leofuso.academico.cd.bancodistribuido.application.communication.resources.ContaResource;
import com.leofuso.academico.cd.bancodistribuido.application.exceptions.OwnerOfRequestNotMatchRequestBody;
import com.leofuso.academico.cd.bancodistribuido.application.factory.ContaFactory;
import com.leofuso.academico.cd.bancodistribuido.domain.Conta;
import com.leofuso.academico.cd.bancodistribuido.domain.events.NovaTransferencia;
import com.leofuso.academico.cd.bancodistribuido.domain.events.NovoDeposito;
import com.leofuso.academico.cd.bancodistribuido.domain.events.NovoSaque;
import com.leofuso.academico.cd.bancodistribuido.domain.interfaces.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationFacade
public class ContaFacadeImpl implements ContaFacade {

    private final ContaService service;
    private final ContaFactory factory;

    @Autowired
    public ContaFacadeImpl(ContaService service, ContaFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @Override
    public void deposito(Integer id, DepositoCommand command) {
        verificarIntegridade(id, command);

        NovoDeposito novoDeposito = factory.enrich(command, NovoDeposito.class);
        service.deposito(novoDeposito);

    }

    @Override
    public void saque(Integer id, SaqueCommand command) {
        verificarIntegridade(id, command);

        NovoSaque novoSaque = factory.enrich(command, NovoSaque.class);
        service.saque(novoSaque);

    }

    @Override
    public void transferencia(Integer id, TransferenciaCommand command) {
        verificarIntegridade(id, command);

        NovaTransferencia novaTransferencia = factory.enrich(command, NovaTransferencia.class);
        service.transferencia(novaTransferencia);

    }

    @Override
    public List<ContaResource> findAll() {
        return service.findAll().stream()
                .map(ContaResource::from)
                .collect(Collectors.toList());
    }

    @Override
    public ContaResource findOneById(Integer id) {
        Assert.notNull(id, "Conta ID não pode ser nulo");

        Conta conta = service.findOneById(id);

        return ContaResource.from(conta);
    }

    private void verificarIntegridade(Integer id, OperacaoBancariaCommand command) {
        Assert.notNull(id, "Conta ID não pode ser nulo");
        Assert.notNull(command, "Comando não pode ser nulo");

        Integer idFound = command.getContaId();
        boolean idInCommandIsWrong = !id.equals(idFound);

        if (idInCommandIsWrong) {
            throw new OwnerOfRequestNotMatchRequestBody("Conta [ " + id + " ] não corresponde ao " +
                    "alvo do requerimento [ " + idFound + " ]");
        }
    }
}
