# JPA com Hibernate

Objetivo do projeto é praticar e aprimorar os conhecimentos referentes a utilização da especificação **Java Persistance API (JPA)**. JPA é uma especificação do mundo Java referente persistência de dados com mapeamento de objetos relacionais, utilizaremos a principal implementação de referência chamada **Hibernate**.

---

### A JPA sincroniza o estado de uma entidade gerenciada com o banco de dados:

- Isto é, o SQL será gerado baseado na diferença entre a entidade em memória e o banco de dados;
- Para essa sincronização acontecer, a entidade precisa estar gerenciada (Managed);
- Os estados de uma entidade são: **Managed**, **Detached**, **Transient** e **Removed**;
- Os métodos do ``EntityManager``, como ``persist``, ``merge`` ou ``remove`` alteram o estado da entidade;
- Relacionamentos entre entidades precisam ser configurados pelas anotações no atributo que define o relacionamento na classe.
- A JPA carrega automaticamente o relacionamento ao carregar a entidade


##### Objetivos

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

##### Estado Managed

Quando realizamos uma consulta e o resultado é devolvido, é mantida uma referência, então a JPA ainda a conhece o estado mesmo após a devolução. Sendo assim, costuma-se dizer que esta entidade se encontra no estado **Managed**, ou seja, gerenciado pela JPA. Portanto, como o estado original(is) dos dados são conhecidos, quando fizermos qualquer alteração e commitarmos a transação, haverá a **sincronização automática**.

A JPA verificará cada atributo para saber se houve alteração. Se houver qualquer alteração em algum deles, um UPDATE será excutado com a mudança do campo(s) alterado, isso também é aplicado quando usamos o ``persist()`` ou ``find()``.

O estado **Detached** não permite realizar a sincronização automática quando há alteração no objeto (mesmo sendo item que já existe na base de dados). Então se temos um objeto que é **Detached**, e precisaremos alterar seu estado. Para isso, para podermos aplicar a sincronização automática utilizando o ``.merge()``.

##### Transaction

Para que possamos persistir dados (alteração de estado), ou seja, realizar **INSERT**, **UPDATE** ou **DELETE** é necessários a utilização do ``em.getTransaction().begin();`` e ``em.getTransaction().commit();`` (sendo o ``em`` o ``EntityManager`` criado).

Uma transação é um conjunto de operações que executam de forma atômica. Um mecanismo para manter a consistência das alterações de estado no banco, visto que todas as operações precisam ser executadas com sucesso, para que a transação seja confirmada.

##### Relacionamentos

Temos as anotações ``@OneToOne`` e ``@ManyToOne``, dependendo da cardinalidade.
O ``@OneToOne`` por padrão, não coloca essa restrição construente nas tabelas. Neste mesmo caso, é necessário aplicarmos o comportamento através da anotação ``@JoinColumn()`` passando o atributo **unique** como ``true``, o que tornará única a chave estrangeira, impedindo outros relacionamento indevidos.

### Java Persistence Query Language ou JPQL 

JPQL é uma linguagem de consulta poderosa que permite definir consultas de banco de dados com base em seu modelo de entidade. Sua estrutura e sintaxe são muito semelhantes às do SQL.
A principal diferença entre SQL e JPQL é que o SQL funciona com tabelas, registros e campos de bancos de dados relacionais, enquanto o JPQL funciona com classes e objetos Java.
___

- JPQL é orientado a objetos, enquanto SQL não;
- JPQL é feita para abstrair o mundo relacional, permitindo com que os desenvolvedores se preocupem apenas com objetos;
- O objeto do tipo ``javax.persistence.Query`` encapsula (representa) uma query;
- ``javax.persistence.TypedQuery`` é a versão tipada do ``javax.persistence.Query``;

É comum colocarmos um tipo de ``query`` mais específica, chamado ``TypedQuery<>``, o qual é geralmente tipado com o que foi trazido na ``query``. 
O ``TypedQuery`` faz checagem de tipo, evitando a ocorrência de ``ClassCastException``.

A notação de passar o valor do parâmetro, baseado na posição onde ele se encontra, assim como no JDBC, também existe na JPA e se chama ***Positional Parameter Notation***. No entanto, a presença de muitos parâmetros pode facilmente se tornar uma confusão.

Para evitar isso, usamos a notação ***Named Parameter Notation*** que é mais expressiva. Onde ganhamos a facilidade de identificar os parâmetros, diminuindo a probabilidade de erros.

### JPA: JPQL e Criteria
___

- Relacionamento ``@*ToMany`` são Lazy por padrão;
- *Lazy* significa que só serão carregados sob demanda;
- Podemos reconfigurar esse comportamento pele atributo ``fetch`` da anotação usando a enum ``FetchType.EAGER``;
- as queries podemos inicializar um relacionamento *lazy* usando ``join fetch``;

___

TODO: Descrever relacionamentos bidirecionais <br>
TODO: Descrever mappedBy <br>
TODO: Descrever fetch

___

Para os exercicios/testes desta fase é necessário a execução via docker, seguem os comandos abaixo::

- Para rodar os services abaixo: ``docker-compose up -d``
- Para terminar os services abaixo: ``docker-compose down``

___

## Spring Data

O **Spring Data** é um projeto do Spring com o objetivo de simplificar ainda mais o acesso ao banco de dados. Como ele definimos apenas interfaces e o Spring Data gera as implementações para a maioria das operações. Em outras palavras, com Spring Data nem é mais preciso criar um DAO ou implementar pesquisas!JPQL é uma DSL, uma linguagem especifica para consultas. A Criteria por sua vez é puramente Java e por isso mais verboso. Ou seja, devemos usar a JPQL sempre?
                                                                                                                                                                                                                                                                                                                     
                                                                                                                                                                                                                                                                                                                     A resposta é: depende. Só devemos usar a API de Criteria quando a consulta é dinâmica. Ai sim ele fica mais flexível e a JPQL perde toda legibilidade e simplicidade. E sempre devemos usar a JPQL quando a pesquisa é estática, pois ela é muito mais legível e enxuta.
                                                                                                                                                                                                                                                                                                                     
                                                                                                                                                                                                                                                                                                                     Resumindo:
                                                                                                                                                                                                                                                                                                                     
                                                                                                                                                                                                                                                                                                                     as consultas JPQL são mais fáceis de escrever e ler quando a consulta é estática.
                                                                                                                                                                                                                                                                                                                     as consultas com a API de Criteria são superiores na hora de construir consultas dinâmicas.