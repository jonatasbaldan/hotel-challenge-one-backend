package com.hotelalura.controller;

import com.hotelalura.dao.ReservaDao;
import com.hotelalura.model.Hospede;
import com.hotelalura.model.Reserva;
import com.hotelalura.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class RegistrarReservaController {
    private Reserva reserva;
    private EntityManager em;
    private ReservaDao reservaDao;

    public RegistrarReservaController(EntityManager em) {
        this.em = em;
        reservaDao = new ReservaDao(em);
    }

    public RegistrarReservaController(EntityManager em, Reserva reserva) {
        this.em = em;
        reservaDao = new ReservaDao(em);
        this.reserva = reserva;
    }

    public void persistirReserva() {
        reservaDao.cadastrarReserva(reserva);
    }

}
