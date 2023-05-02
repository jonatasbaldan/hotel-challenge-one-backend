package com.hotelalura.controller;

import com.hotelalura.model.Hospede;
import com.hotelalura.model.Reserva;
import com.hotelalura.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.Objects;

public class ServiceController {
    private EntityManager em;
    private Reserva reserva;
    private RegistrarReservaController registroReservaController;
    private CadastrarHospedeController cadastroHospedeController;

    public ServiceController() {
        em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
    }

    public void cadastrarReserva(Reserva reserva) {
        this.reserva = reserva;
        registroReservaController = new RegistrarReservaController(em, this.reserva);
        registroReservaController.persistirReserva();
    }

    public void cadastrarHospede(Hospede hospede) {
        reserva.setHospede(hospede);
        cadastroHospedeController = new CadastrarHospedeController(em, hospede);
        cadastroHospedeController.persistirHospede();
        em.getTransaction().commit();
    }
}
