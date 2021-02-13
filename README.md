# JPA com Hibernate

Objetivo do projeto é praticar e aprimorar os conhecimentos referentes a utilização da especificação **Java Persistance API (JPA)**. JPA é uma especificação do mundo Java referente persistência de dados com mapeamento de objetos relacionais, utilizaremos a principal implementação de referência chamada **Hibernate**.

---

#### Objetivos

- Entenda as dificuldades do uso do JDBC puro
- Abuse (usar de forma adequada) da persistência orientada a objetos
- Conheça e aprenda a trabalhar com todos os estados de uma entidade
- Fique fera em Lazy Loading e relacionamentos bidirecionais
- Organize suas queries como um profissional

##### O que é Dialeto ?
O dialeto também serve como forma de escolhermos recursos do banco que serão usados. Por exemplo, no MySQL, podemos utilizar o **MyISAM (storage strategy)**, que não possui transações e integridade referencial (foreign key constraint).

##### EntityManager / Persistence

A classe ``Persistence`` lê a configuração e cria uma ``EntityManagerFactory``.

O método ``createEntityManagerFactory`` irá gerar um ``EntityManagerFactory`` baseado nas configurações do **persistence.xml**. Baseado nisso, é fundamental que este método receba como argumento o nome de alguma unidade de persistência existente no arquivo. 

As configurações relacionadas ao acesso banco de dados ficam dentro da sessão ``persistence-unit``. A JPA não limita o número de unidades de persistência (o que é útil quando precisamos de mais de um banco por aplicação, como veremos no próximo exercício) e por isso precisamos escolher um para usar no método ``createEntityManagerFactory``.

**Obs.:** Se o projeto estiver for maven o arquivo **persistence.xml** deve estar dentro da pasta ``/src/resource/META-INF``, caso contrário de ser ``/src/META-INF``.

##### Transaction

Para que possamos persistir dados, ou seja, realizar **INSERT**, **UPDATE** ou **DELETE** é necessários a utilização do ``em.getTransaction().begin();`` e ``em.getTransaction().commit();`` (sendo o ``em`` o ``EntityManager`` criado).

Uma transação é um conjunto de operações que executam de forma atômica. Um mecanismo para manter a consistência das alterações de estado no banco, visto que todas as operações precisam ser executadas com sucesso, para que a transação seja confirmada.

##### Java Persistence Query Language ou JPQL 

- JPQL é orientado a objetos, enquanto SQL não;
- JPQL é feita para abstrair o mundo relacional, permitindo com que os desenvolvedores se preocupem apenas com objetos;

É comum colocarmos um tipo de ``query`` mais específica, chamado ``TypedQuery<>``, o qual é geralmente tipado com o que foi trazido na ``query``. 
O ``TypedQuery`` faz checagem de tipo, evitando a ocorrência de ``ClassCastException``.

A notação de passar o valor do parâmetro, baseado na posição onde ele se encontra, assim como no JDBC, também existe em JPA e se chama ***Positional Parameter Notation***.