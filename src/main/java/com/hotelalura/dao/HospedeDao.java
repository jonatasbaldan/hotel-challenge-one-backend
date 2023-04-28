package com.hotelalura.dao;

import com.hotelalura.model.Hospede;
import jakarta.persistence.EntityManager;

public class HospedeDao {
    private EntityManager em;

    public HospedeDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrarHospede(Hospede hospede) {
        em.persist(hospede);
    }

    public void deletarHospede(Hospede hospede) {
        em.remove(hospede);
    }
}
