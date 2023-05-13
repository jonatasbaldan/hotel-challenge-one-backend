package com.hotelalura.controller;

import com.hotelalura.dao.ReservaDao;
import com.hotelalura.model.Reserva;
import com.hotelalura.model.ReservaTableModel;
import com.hotelalura.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.Date;

public class RegistrarReservaController {
    private EntityManager em;
    private ReservaDao reservaDao;
    public static BigDecimal valorPorDia = new BigDecimal(20);

    public RegistrarReservaController() {
        em = JpaUtil.getEntityManager();
        reservaDao = new ReservaDao(em);
    }

    public void cadastrarReserva(Reserva reserva, ReservaTableModel reservaTableModel) {
        reservaDao.persistirReserva(reserva);
        reservaTableModel.addRow(reserva);
    }

    public void prepararReserva(Reserva reserva) {
        reservaDao.prepararReserva(reserva);
    }

    public void removerReserva(Reserva reserva) {
        reservaDao.removerReserva(reserva, reserva.getId());
    }

    public boolean isReservaDisponivel(Date date, String reservaMomento) {
        switch (reservaMomento) {
            case "Entrada":
                return reservaDao.temReservaEntradaDisponibilidade(date);
            case "Saida":
                return reservaDao.temReservaSaidaDisponibilidade(date);
            default:
                return false;
        }
    }
}
