package com.hotelalura.dao;

import com.hotelalura.model.Hospede;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Objects;

public class HospedeDao {
    private EntityManager em;

    public HospedeDao(EntityManager em) {
        this.em = em;
    }

    public void persistirHospede(Hospede hospede) {
        em.getTransaction().begin();
        em.persist(hospede);
        em.getTransaction().commit();
    }
    public void atualizarHospede(Hospede hospede) {
        em.getTransaction().begin();
        em.merge(hospede);
        em.getTransaction().commit();
    }

    public void removerHospede(Hospede hospede) {
        em.getTransaction().begin();
        Hospede hospedeTemp = em.merge(hospede);
        em.refresh(hospedeTemp);
        em.remove(hospedeTemp);
        em.getTransaction().commit();
    }

    public List<Hospede> getHospedes() {
        String jpql = "SELECT h FROM Hospede h";
        return em.createQuery(jpql, Hospede.class).getResultList();
    }

    public Hospede getHospede(Long id) {
        return em.find(Hospede.class, id);
    }

    public List<Hospede> buscarHospedes(String sobrenome) {
        if (Objects.nonNull(sobrenome) && !sobrenome.isBlank()) {
            String jpql = "SELECT h FROM Hospede h WHERE h.sobreNome = :sobreNome";
            TypedQuery<Hospede> query = em.createQuery(jpql, Hospede.class);
            query.setParameter("sobreNome", sobrenome);
            return query.getResultList();
        } else {
            return getHospedes();
        }
    }
}
