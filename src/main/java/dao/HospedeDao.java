package dao;

import jakarta.persistence.EntityManager;
import model.Hospede;

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
