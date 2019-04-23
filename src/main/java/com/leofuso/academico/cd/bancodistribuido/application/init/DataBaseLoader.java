package com.leofuso.academico.cd.bancodistribuido.application.init;

import com.leofuso.academico.cd.bancodistribuido.application.repositories.ContaJpaRepository;
import com.leofuso.academico.cd.bancodistribuido.domain.Conta;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Component
public class DataBaseLoader {

    @Bean
    @Transactional
    CommandLineRunner init(ContaJpaRepository repository) {
        return args -> {
            final List<Conta> contas = Arrays.asList(
                    Conta.produce(1000.0, "leonardo.nuzzo@mackenista.br"),
                    Conta.produce(1000.0, "rodrigo.azevedo@fatec.com.br"),
                    Conta.produce(1000.0, "lucas.torreao.machado@usp.br"),
                    Conta.produce(1000.0, "ana.rossi@gmail.com")
            );
            repository.saveAll(contas);
        };
    }
}
