package br.com.pricardo.jpa.testes.relacionamento;

import br.com.pricardo.jpa.model.Conta;
import br.com.pricardo.jpa.model.Movimentacao;
import br.com.pricardo.jpa.model.TipoMovimentacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RelacionamentoSimplesTest {
    public static void main(String[] args) {
        Conta conta = new Conta();
        conta.setAgencia(1);
        conta.setNumero(123);
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
