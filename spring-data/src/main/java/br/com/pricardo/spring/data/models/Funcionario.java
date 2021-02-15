package br.com.pricardo.spring.data.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Cargo cargo;

    @ManyToMany
    @JoinTable(name = "funcionarios_unidades",
            joinColumns = {
                    @JoinColumn(name = "id_funcionario")
            },
            foreignKey = @ForeignKey(name = "fk001_func_unid"),
            inverseJoinColumns = {
                    @JoinColumn(name = "id_unidade")
            },
            inverseForeignKey = @ForeignKey(name = "fk001_unid_func")
    )
    private Set<Unidade> unidades;

    private String nome;
    private String cpf;
    private Double salario;
    private LocalDate contratacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public LocalDate getContratacao() {
        return contratacao;
    }

    public void setContratacao(LocalDate contratacao) {
        this.contratacao = contratacao;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Set<Unidade> getUnidades() {
        return unidades;
    }

    public void setUnidades(Set<Unidade> unidades) {
        this.unidades = unidades;
    }
}
