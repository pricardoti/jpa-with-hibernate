package br.com.pricardo.spring.data;

import br.com.pricardo.spring.data.models.Cargo;
import br.com.pricardo.spring.data.models.Unidade;
import br.com.pricardo.spring.data.repositories.CargoRepository;
import br.com.pricardo.spring.data.repositories.UnidadeRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitializerDataApplication implements ApplicationRunner {

    private final CargoRepository cargoRepository;
    private final UnidadeRepository unidadeRepository;

    public InitializerDataApplication(
            CargoRepository cargoRepository,
            UnidadeRepository unidadeRepository
    ) {
        this.cargoRepository = cargoRepository;
        this.unidadeRepository = unidadeRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        cargoRepository.save(new Cargo(1, "Gerente de Projetos"));
        cargoRepository.save(new Cargo(2, "Desennvolvedor de Softwarer"));
        cargoRepository.save(new Cargo(3, "Auxiliar Administrativo"));
        
        unidadeRepository.save(new Unidade(1, "Sede", "Torre Stark"));
        unidadeRepository.save(new Unidade(2, "Filial 1", "R. onde o vento faz a curva"));
        unidadeRepository.save(new Unidade(3, "Filial 2", "Pais das maravilhas"));
    }

}
