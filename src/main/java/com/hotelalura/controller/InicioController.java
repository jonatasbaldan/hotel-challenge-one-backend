package com.hotelalura.controller;

import com.hotelalura.dao.HospedeDao;
import com.hotelalura.dao.ReservaDao;
import com.hotelalura.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class InicioController {
    private EntityManager em;
    public InicioController() {
        em = JpaUtil.getEntityManager();
    }

    public Long getNumeroTotalReservas() {
        ReservaDao reservaDao = new ReservaDao(em);
        return reservaDao.getNumeroTotalReservas();
    }

    public Long getNumeroTotalHospedes() {
        HospedeDao hospedeDao = new HospedeDao(em);
        return hospedeDao.getNumeroTotalHospedes();
    }
}
