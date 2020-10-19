package br.com.pricardo;

import br.com.pricardo.jpa.Categoria;
import br.com.pricardo.jpa.Conta;
import br.com.pricardo.jpa.Movimentacao;
import br.com.pricardo.jpa.TipoMovimentacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

public class RelationshipMultipleTest {

    public static void main(String[] args) {
        Categoria categoria = new Categoria("Viagem");
        Categoria categoria2 = new Categoria("Negócios");

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setDescricao("Viagem à São Paulo");
        movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);
        movimentacao.setData(LocalDateTime.now());
        movimentacao.setValor(new BigDecimal(300.0));
        movimentacao.setCategorias(Arrays.asList(categoria, categoria2));

        Movimentacao movimentacao2 = new Movimentacao();
        movimentacao2.setDescricao("Viagem ao Rio de Janeiro");
        movimentacao2.setTipoMovimentacao(TipoMovimentacao.SAIDA);
        movimentacao2.setData(LocalDateTime.now());
        movimentacao2.setValor(new BigDecimal(400.0));
        movimentacao2.setCategorias(Arrays.asList(categoria, categoria2));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        System.out.println(">>> Persistindo categorias...");
        em.persist(categoria);
        em.persist(categoria2);

        System.out.println(">>> Persistindo movimentações...");
        em.persist(movimentacao);
        em.persist(movimentacao2);
        em.getTransaction().commit();

        em.close();
    }
}
