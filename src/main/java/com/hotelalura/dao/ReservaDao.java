package com.hotelalura.dao;

import com.hotelalura.model.Reserva;
import jakarta.persistence.EntityManager;

public class ReservaDao {

    private EntityManager em;

    public ReservaDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrarReserva(Reserva reserva) {
        em.persist(reserva);
    }

    public void removerReserva(Reserva reserva) {
        em.remove(reserva);
    }
}
