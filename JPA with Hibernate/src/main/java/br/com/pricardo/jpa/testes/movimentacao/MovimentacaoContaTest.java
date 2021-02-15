package br.com.pricardo.jpa.testes.movimentacao;

import br.com.pricardo.jpa.model.Conta;
import br.com.pricardo.jpa.model.Movimentacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MovimentacaoContaTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();

        Movimentacao movimentacao = em.find(Movimentacao.class, 1L);

        Conta conta = movimentacao.getConta();
        int quantidadeDeMovimentacoes = conta.getMovimentacoes().size();

        System.out.println("Quantidade de movimentacoes: " + quantidadeDeMovimentacoes);
        System.out.println("Titular da conta: " + conta.getTitular());
    }

}