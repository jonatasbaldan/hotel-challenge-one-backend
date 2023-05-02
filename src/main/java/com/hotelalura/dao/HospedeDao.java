package com.hotelalura.dao;

import com.hotelalura.model.Hospede;
import jakarta.persistence.EntityManager;

import java.util.List;

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
    public List<Hospede> getHospedes() {
        String jpql = "SELECT h FROM Hospede h";
        return em.createQuery(jpql, Hospede.class).getResultList();
    }
}
