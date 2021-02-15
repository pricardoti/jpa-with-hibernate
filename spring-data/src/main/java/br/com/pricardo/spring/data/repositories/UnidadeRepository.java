package br.com.pricardo.spring.data.repositories;

import br.com.pricardo.spring.data.models.Unidade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeRepository extends CrudRepository<Unidade, Long> {
}
