package br.com.pricardo.jpa.testes.movimentacao;

import br.com.pricardo.jpa.model.Movimentacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

public class SomaDasMovimentacoesTest {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();

        // Reomvendo o DAO
        // MovimentacaoDao dao = new MovimentacaoDao(em);
        // System.out.println("A soma das movimentacoes é: " + dao.getSomaDasMovimentacoes());

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> criteriaQuery = criteriaBuilder
                .createQuery(BigDecimal.class);

        Root<Movimentacao> root = criteriaQuery.from(Movimentacao.class);                           // Criando o root da consulta, ou seja, tudo que for executado será baseado na entidade definida.
        Expression<BigDecimal> expression = criteriaBuilder.sum(root.<BigDecimal>get("valor"));     // Especificando a função que será executada
        criteriaQuery.select(expression);                                                           // criando a query com a função desejada/

        TypedQuery<BigDecimal> typedQuery = em.createQuery(criteriaQuery);                          // executando a query criada.
        System.out.println("A soma das movimentacoes é: " + typedQuery.getSingleResult());          // exibindo o resultado.
        
    }
}