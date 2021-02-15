package br.com.pricardo.jpa.dao;

import br.com.pricardo.jpa.model.MediaComData;
import br.com.pricardo.jpa.model.Movimentacao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovimentacaoDao {

    private EntityManager em;

    public MovimentacaoDao(EntityManager em) {
        this.em = em;
    }

    public List<Movimentacao> getMovimentacoesFiltradasPorData(Integer dia, Integer mes, Integer ano) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Movimentacao> criteriaQuery = criteriaBuilder
                .createQuery(Movimentacao.class);

        Root<Movimentacao> root = criteriaQuery.from(Movimentacao.class);
        List<Predicate> predicates = new ArrayList<>();

        if (dia != null) {
            Predicate predicate = criteriaBuilder.equal(criteriaBuilder.function("day", Integer.class, root.get("data")), dia);
            predicates.add(predicate);
        }

        if (mes != null) {
            Predicate predicate = criteriaBuilder.equal(criteriaBuilder.function("month", Integer.class, root.get("data")), mes);
            predicates.add(predicate);
        }

        if (ano != null) {
            Predicate predicate = criteriaBuilder.equal(criteriaBuilder.function("year", Integer.class, root.get("data")), ano);
            predicates.add(predicate);
        }

        criteriaQuery.where(predicates.toArray(new Predicate[3]));
        TypedQuery<Movimentacao> typedQuery = em.createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }

    public List<MediaComData> getMediaDiariaDasMovimentacoes() {
        TypedQuery<MediaComData> query = em.createNamedQuery("mediaDiariaMovimentacoes", MediaComData.class);
        return query.getResultList();
    }

    public BigDecimal getSomaDasMovimentacoes() {
        String jpql = "select sum(m.valor) from Movimentacao m ";

        TypedQuery<BigDecimal> query = em.createQuery(jpql, BigDecimal.class);
        BigDecimal somaDasMovimentacoes = query.getSingleResult();

        return somaDasMovimentacoes;
    }

}