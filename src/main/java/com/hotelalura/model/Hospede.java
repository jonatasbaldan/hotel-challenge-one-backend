package com.hotelalura.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Hóspedes")
public class Hospede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobreNome;
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private Nacionalidade nacionalidade;
    private String telefone;
    @OneToMany(mappedBy = "hospede", cascade = CascadeType.ALL)
    private List<Reserva> reservas = new ArrayList<>();

    public Hospede() {
    }

    public Hospede(Long id, String nome, String sobreNome, LocalDate dataNascimento, Nacionalidade nacionalidade,
                   String telefone, List<Reserva> reservas) {
        this.id = id;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.reservas = reservas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Nacionalidade getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(Nacionalidade nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    @Override
    public String toString() {
        return "Hospede{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobreNome='" + sobreNome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", nacionalidade=" + nacionalidade +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
