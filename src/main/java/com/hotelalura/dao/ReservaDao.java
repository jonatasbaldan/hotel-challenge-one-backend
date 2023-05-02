package com.hotelalura.dao;

import com.hotelalura.model.Hospede;
import com.hotelalura.model.Reserva;
import jakarta.persistence.EntityManager;

import java.util.List;

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

    public List<Reserva> getReservas() {
        String jpql = "SELECT r FROM Reserva r";
        return em.createQuery(jpql, Reserva.class).getResultList();
    }
}
