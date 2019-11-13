package com.leofuso.academico.cd.bancod.api.application.communication.controllers;

import com.leofuso.academico.cd.bancod.api.application.communication.commands.DepositoCommand;
import com.leofuso.academico.cd.bancod.api.application.communication.commands.SaqueCommand;
import com.leofuso.academico.cd.bancod.api.application.communication.commands.TransferenciaCommand;
import com.leofuso.academico.cd.bancod.api.application.communication.resources.ContaResource;
import com.leofuso.academico.cd.bancod.api.application.facade.ContaFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ContaController(final ContaFacade facade) {
        this.facade = facade;
    }

    @RequestMapping(path = {"/"},
            method = {RequestMethod.GET, RequestMethod.OPTIONS},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resources<Resource<ContaResource>>> findAll() {

        final List<ContaResource> contas = facade.findAll();

        final List<Resource<ContaResource>> contasResource
                = contas.stream()
                        .map(contaResource -> {

                            Link umaConta =
                                    linkTo(methodOn(ContaController.class).findOneById(
                                            contaResource.getId())).withSelfRel();
                            Link todasAsContas =
                                    linkTo(methodOn(ContaController.class).findAll())
                                            .withRel("contas");
                            return new Resource<>(contaResource,
                                                  umaConta,
                                                  todasAsContas);

                        }).collect(Collectors.toList());

        final Link todasAsContasSelf = linkTo(methodOn(ContaController.class).findAll()).withSelfRel();

        final Resources<Resource<ContaResource>> resource = new Resources<>(contasResource, todasAsContasSelf);

        return ResponseEntity
                .ok(resource);
    }

    @RequestMapping(path = {"/{id}/deposito"},
            method = {RequestMethod.PUT, RequestMethod.OPTIONS},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deposito(@NotNull @PathVariable final Integer id,
                                         @Valid @RequestBody final DepositoCommand command) {

        facade.deposito(id, command);

        return ContaController.responseWithLocationHelper(id);
    }

    @RequestMapping(path = {"/{id}/saque"},
            method = {RequestMethod.PUT, RequestMethod.OPTIONS},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saque(@NotNull @PathVariable final Integer id,
                                      @Valid @RequestBody final SaqueCommand command) {
        facade.saque(id, command);
        return ContaController.responseWithLocationHelper(id);
    }

    @RequestMapping(path = {"/{id}/transferencia"},
            method = {RequestMethod.PUT, RequestMethod.OPTIONS},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> transferencia(@NotNull @PathVariable final Integer id,
                                              @Valid @RequestBody final TransferenciaCommand command) {

        facade.transferencia(id, command);
        return ContaController.responseWithLocationHelper(id);
    }


    @RequestMapping(path = {"/{id}"},
            method = {RequestMethod.GET, RequestMethod.OPTIONS},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource<ContaResource>> findOneById(@NotNull @PathVariable final Integer id) {

        final ContaResource conta = facade.findOneById(id);
        final Link umaConta = linkTo(methodOn(ContaController.class).findOneById(id)).withSelfRel();
        final Link todasAsContas = linkTo(methodOn(ContaController.class).findAll()).withRel("contas");

        final Resource<ContaResource> resource = new Resource<>(conta, umaConta, todasAsContas);

        return ResponseEntity
                .ok(resource);
    }

    private static ResponseEntity<Void> responseWithLocationHelper(final Integer id) {

        final ControllerLinkBuilder controllerLinkBuilder = linkTo(methodOn(ContaController.class).findOneById(id));
        final URI uri = controllerLinkBuilder.toUri();
        final HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.LOCATION);

        return ResponseEntity
                .accepted()
                .headers(headers)
                .location(uri)
                .build();
    }
}
