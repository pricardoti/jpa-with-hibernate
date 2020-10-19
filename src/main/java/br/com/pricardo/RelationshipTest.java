package br.com.pricardo;

import br.com.pricardo.jpa.Conta;
import br.com.pricardo.jpa.Movimentacao;
import br.com.pricardo.jpa.TipoMovimentacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RelationshipTest {
    public static void main(String[] args) {
        Conta conta = new Conta();
        conta.setAgencia(123123l);
        conta.setNumero(456456l);
        conta.setSaldo(300.0);
        conta.setTitular("Zezinho");

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setData(LocalDateTime.now());
        movimentacao.setDescricao("Churrascaria");
        movimentacao.setValor(new BigDecimal(200.0));
        movimentacao.setTipoMovimentacao(TipoMovimentacao.ENTRADA);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(movimentacao);
        em.getTransaction().commit();

        em.close();
    }
}
