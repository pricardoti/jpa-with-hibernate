package br.com.pricardo.jpa.testes;

import br.com.pricardo.jpa.model.Conta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateUpdateEntityTest {

    public static void main(String[] args) {
        System.out.println(">>> Criando EntityManagerFactory...");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager entityManager = emf.createEntityManager();

        System.out.println(">>> EntityManagerFactory criado !!!");
        System.out.println(">>> Inserindo registro(s)...");

        entityManager.getTransaction().begin();
        entityManager.persist(new Conta(4321l, 1234l, 1000.0, "Seu João"));
        entityManager.getTransaction().commit();

        System.out.println(">>> Registro(s) inserido(s) com sucesso.");
        System.out.println(">>> Consultando registro(s)...");

        Conta conta = entityManager.find(Conta.class, 1l); // Verificar o ID criado.

        System.out.println(">>> Registro: " + conta);
        System.out.println(">>> Atualizando registro(s)...");

        entityManager.getTransaction().begin();
        conta.setSaldo(500.0);
        entityManager.getTransaction().commit();

        System.out.println(">>> Fechando conexão...");
        entityManager.close(); // Detached

        // Não deve alterar o estado do item na base pois já fechou a conexão.
        try {
            entityManager.getTransaction().begin();
            conta.setSaldo(100.0);
            entityManager.getTransaction().commit();
        } catch (IllegalStateException isException) {
            System.out.println(isException);
        }

        System.out.println(">>> Criando um novo managed...");
        entityManager = emf.createEntityManager();

        System.out.println(">>> Atualizando registro(s)...");

        entityManager.getTransaction().begin();
        conta.setSaldo(250.0);
        entityManager.merge(conta);
        entityManager.getTransaction().commit();
        entityManager.close();

        System.out.println(">>> Fechando conexão...");
    }
}
