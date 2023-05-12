package com.hotelalura.controller;

import com.hotelalura.dao.HospedeDao;
import com.hotelalura.model.Hospede;
import com.hotelalura.model.HospedeTableModel;
import com.hotelalura.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class CadastrarHospedeController {
    private EntityManager em;
    private HospedeDao hospedeDao;

    public CadastrarHospedeController() {
        em = JpaUtil.getEntityManager();
        hospedeDao = new HospedeDao(em);
    }

    public void cadastrarHospede(Hospede hospede, HospedeTableModel tabelaModel) {
        hospedeDao.persistirHospede(hospede);
        tabelaModel.addRow(hospede);
    }
}
