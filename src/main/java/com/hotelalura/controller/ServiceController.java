package com.hotelalura.controller;

import com.hotelalura.model.Hospede;
import com.hotelalura.model.Reserva;
import com.hotelalura.model.ReservaTableModel;
import com.hotelalura.util.JpaUtil;
import com.hotelalura.view.BuscarPanel;
import com.hotelalura.view.RegistrarReservaPanel;
import jakarta.persistence.EntityManager;

import javax.swing.*;
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
}