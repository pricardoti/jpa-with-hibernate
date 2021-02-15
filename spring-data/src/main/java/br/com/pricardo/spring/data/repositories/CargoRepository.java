package br.com.pricardo.spring.data.repositories;

import br.com.pricardo.spring.data.models.Cargo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Long> {
}
