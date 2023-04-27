package model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Hóspedes")
public class Hospede {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;
    private String sobreNome;
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private Nacionalidade nacionalidade;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    public Hospede() {}

    public Hospede(Long id, String nome, String sobreNome, LocalDate dataNascimento, Nacionalidade nacionalidade,
                   String telefone, FormaPagamento formaPagamento) {
        this.id = id;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.formaPagamento = formaPagamento;
    }
}
