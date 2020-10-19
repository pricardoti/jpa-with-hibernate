package br.com.pricardo;

import br.com.pricardo.jpa.Cliente;
import br.com.pricardo.jpa.Conta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ClientAccountTest {

    public static void main(String[] args) {
        Conta conta = new Conta();
        conta.setId(1L);

        Cliente cliente = new Cliente();
        cliente.setNome("Uncle Bob");
        cliente.setEndereco("Onde o vento faz a curva, 01");
        cliente.setProfissao("Programador");
        cliente.setConta(conta);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();

        em.close();
    }
}
