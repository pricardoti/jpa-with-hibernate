package br.com.pricardo.jpa.testes.movimentacao;

import br.com.pricardo.jpa.dao.MovimentacaoDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MovimentacoesFiltradasCriteriaTest {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();

        MovimentacaoDao movimentacaoDao = new MovimentacaoDao(em);
        movimentacaoDao.getMovimentacoesFiltradasPorData(null, null, 2017)
                .forEach(System.out::println);
    }
}
