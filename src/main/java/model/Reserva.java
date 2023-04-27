package model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private BigDecimal valor;
    @ManyToOne
    private Hospede hospede;

    public Reserva() {
    }

    public Reserva(Long id, LocalDate dataEntrada, LocalDate dataSaida, BigDecimal valor, Hospede hospede) {
        this.id = id;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.valor = valor;
        this.hospede = hospede;
    }
}
