package dao;

import jakarta.persistence.EntityManager;
import model.Reserva;

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
