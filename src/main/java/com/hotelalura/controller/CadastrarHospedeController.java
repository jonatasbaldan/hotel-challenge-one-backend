package com.hotelalura.controller;

import com.hotelalura.dao.HospedeDao;
import com.hotelalura.dao.ReservaDao;
import com.hotelalura.model.Hospede;
import com.hotelalura.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class CadastrarHospedeController {
    private Hospede hospede;
    private EntityManager em;
    private HospedeDao hospedeDao;

    public CadastrarHospedeController(EntityManager em) {
        this.em = em;
        hospedeDao = new HospedeDao(em);
    }

    public CadastrarHospedeController(EntityManager em, Hospede hospede) {
        this.em = em;
        hospedeDao = new HospedeDao(em);
        this.hospede = hospede;
    }

    public void persistirHospede() {
        hospedeDao.cadastrarHospede(hospede);
    }
}
