package com.hotelalura.dao;

import com.hotelalura.model.Reserva;
import com.hotelalura.util.DateUtilHotel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ReservaDao {

    private EntityManager em;

    public ReservaDao(EntityManager em) {
        this.em = em;
    }

    public void persistirReserva(Reserva reserva) {
        if (!em.getTransaction().isActive()) em.getTransaction().begin();
        if (em.contains(reserva)) {
            em.getTransaction().commit();
        } else {
            em.persist(reserva);
            em.getTransaction().commit();
        }
    }

    public void prepararReserva(Reserva reserva) {
        em.clear();
        if (!em.isOpen()) em.getTransaction().begin();
        em.persist(reserva);
    }

    public void atualizarReserva(Reserva reserva) {
        em.getTransaction().begin();
        Reserva reservaTemp = em.find(Reserva.class, reserva.getId());
        em.merge(reservaTemp);
        em.getTransaction().commit();
    }

    public void removerReserva(Reserva reserva, Long id) {
        if (!em.getTransaction().isActive()) em.getTransaction().begin();
        if (em.contains(reserva)) {
            em.detach(reserva);
        } else {
            em.remove(em.find(Reserva.class, id));
            em.getTransaction().commit();
        }
    }

    public List<Reserva> getReservas() {
        String jpql = "SELECT r FROM Reserva r";
        return em.createQuery(jpql, Reserva.class).getResultList();
    }

    public Reserva getReserva(Long id) {
        return em.find(Reserva.class, id);
    }

    public List<Reserva> buscarReservas(Long id) {
        if (id != null) {
            String jpql = "SELECT r FROM Reserva r WHERE r.id = :id";
            TypedQuery<Reserva> query = em.createQuery(jpql, Reserva.class);
            query.setParameter("id", id);
            return query.getResultList();
        } else {
            return getReservas();
        }
    }

    public boolean temReservaEntradaDisponibilidade(Date data) {

        LocalDate dataLocal = LocalDate.ofInstant(data.toInstant(), DateUtilHotel.getZoneId());
        String jpql = "SELECT r FROM Reserva r WHERE r.dataEntrada = :data";
        TypedQuery<Reserva> query = em.createQuery(jpql, Reserva.class)
                .setParameter("data", dataLocal);

        System.out.println(query.getResultList());
        System.out.println(query.getResultList().size());

        return query.getResultList().size() == 0;
    }

    public boolean temReservaSaidaDisponibilidade(Date data) {

        LocalDate dataLocal = LocalDate.ofInstant(data.toInstant(), DateUtilHotel.getZoneId());
        String jpql = "SELECT r FROM Reserva r WHERE r.dataSaida = :data";
        TypedQuery<Reserva> query = em.createQuery(jpql, Reserva.class)
                .setParameter("data", dataLocal);

        System.out.println(query.getResultList());
        System.out.println(query.getResultList().size());

        return query.getResultList().size() == 0;
    }
}
