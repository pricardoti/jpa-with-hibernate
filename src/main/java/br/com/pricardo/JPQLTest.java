package br.com.pricardo;

import br.com.pricardo.jpa.Conta;
import br.com.pricardo.jpa.Movimentacao;

import javax.persistence.*;
import java.util.List;

public class JPQLTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();

        Conta conta = new Conta();
        conta.setId(2L);

        System.out.println(">>> Escrevendo a query com JPQL...");

        String jpql =
                " select m " +
                        " from Movimentacao m " +
                        " where m.conta = :pConta " +
                        " order by m.valor desc";

        Query query = em.createQuery(jpql);
        query.setParameter("pConta", conta);

        List<Movimentacao> resultList = query.getResultList();
        resultList.forEach(item -> {
            System.out.println("Descrição: " + item.getDescricao());
            System.out.println("Tipo: " + item.getTipoMovimentacao());
        });

        System.out.println(">>> Escrevendo a query com TypedQuery...");
        TypedQuery<Movimentacao> typedQuery = em.createQuery(jpql, Movimentacao.class);
        typedQuery.setParameter("pConta", conta);

        resultList = typedQuery.getResultList();
        resultList.forEach(item -> {
            System.out.println("Descrição: " + item.getDescricao());
            System.out.println("Tipo: " + item.getTipoMovimentacao());
        });
    }
}
