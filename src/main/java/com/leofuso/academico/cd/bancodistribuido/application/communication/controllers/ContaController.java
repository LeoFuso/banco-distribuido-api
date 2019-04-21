package com.leofuso.academico.cd.bancodistribuido.application.communication.controllers;

import com.leofuso.academico.cd.bancodistribuido.application.communication.commands.DepositoCommand;
import com.leofuso.academico.cd.bancodistribuido.application.communication.commands.SaqueCommand;
import com.leofuso.academico.cd.bancodistribuido.application.communication.commands.TransferenciaCommand;
import com.leofuso.academico.cd.bancodistribuido.application.communication.resources.ContaResource;
import com.leofuso.academico.cd.bancodistribuido.application.facade.ContaFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@CrossOrigin
@RestController
@RequestMapping(path = "/contas")
public class ContaController {

    private final ContaFacade facade;

    @Autowired
    public ContaController(ContaFacade facade) {
        this.facade = facade;
    }

    @RequestMapping(path = {"/"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resources<Resource<ContaResource>>> findAll() {

        List<ContaResource> contas = facade.findAll();

        final List<Resource<ContaResource>> contasResource = contas.stream()
                .map(contaResource -> {

                    Link umaConta = linkTo(methodOn(ContaController.class).findOneById(contaResource.getId())).withSelfRel();
                    Link todasAsContas = linkTo(methodOn(ContaController.class).findAll()).withRel("contas");
                    return new Resource<>(contaResource, umaConta, todasAsContas);

                }).collect(Collectors.toList());

        Link todasAsContasSelf = linkTo(methodOn(ContaController.class).findAll()).withSelfRel();

        Resources<Resource<ContaResource>> resource = new Resources<>(contasResource, todasAsContasSelf);

        return ResponseEntity
                .ok(resource);
    }

    @RequestMapping(path = {"/{id}/deposito"},
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deposito(@NotNull @PathVariable Integer id,
                                         @Valid @RequestBody DepositoCommand command) {

        facade.deposito(id, command);

        return ContaController.responseWithLocationHelper(id);
    }

    @RequestMapping(path = {"/{id}/saque"},
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saque(@NotNull @PathVariable Integer id,
                                      @Valid @RequestBody SaqueCommand command) {

        facade.saque(id, command);

        return ContaController.responseWithLocationHelper(id);
    }

    @RequestMapping(path = {"/{id}/transferencia"},
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> transferencia(@NotNull @PathVariable Integer id,
                                              @Valid @RequestBody TransferenciaCommand command) {

        facade.transferencia(id, command);

        return ContaController.responseWithLocationHelper(id);
    }


    @RequestMapping(path = {"/{id}"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource<ContaResource>> findOneById(@NotNull @PathVariable Integer id) {

        ContaResource conta = facade.findOneById(id);

        Link umaConta = linkTo(methodOn(ContaController.class).findOneById(id)).withSelfRel();
        Link todasAsContas = linkTo(methodOn(ContaController.class).findAll()).withRel("contas");

        Resource<ContaResource> resource = new Resource<>(conta, umaConta, todasAsContas);

        return ResponseEntity
                .ok(resource);
    }

    private static ResponseEntity<Void> responseWithLocationHelper(Integer id) {

        ControllerLinkBuilder controllerLinkBuilder = linkTo(methodOn(ContaController.class).findOneById(id));
        URI uri = controllerLinkBuilder.toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.LOCATION);

        return ResponseEntity
                .accepted()
                .headers(headers)
                .location(uri)
                .build();
    }
}
