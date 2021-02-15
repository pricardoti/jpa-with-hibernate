package br.com.pricardo.spring.data;

import br.com.pricardo.spring.data.repositories.CargoRepository;
import br.com.pricardo.spring.data.repositories.UnidadeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

}
