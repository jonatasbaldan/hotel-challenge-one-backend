package com.hotelalura.controller;

import com.hotelalura.dao.HospedeDao;
import com.hotelalura.dao.ReservaDao;
import com.hotelalura.model.*;
import com.hotelalura.util.JpaUtil;
import jakarta.persistence.EntityManager;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BuscarController {
    private EntityManager em;
    private ReservaDao reservaDao;
    private HospedeDao hospedeDao;

    public BuscarController() {
        em = JpaUtil.getEntityManager();
        reservaDao = new ReservaDao(em);
        hospedeDao = new HospedeDao(em);
    }

    public void persistirReserva(Reserva reserva, ReservaTableModel tabelaModel) {
        reservaDao.persistirReserva(reserva);
        tabelaModel.addRow(reserva);
    }

    public void buscarHospedes(String sobreNome, HospedeTableModel hospedeTableModel) {
        if (!sobreNome.isBlank()) hospedeTableModel.atualizarHospedes(hospedeDao.buscarHospedes(sobreNome));
        else hospedeTableModel.atualizarHospedes(hospedeDao.getHospedes());
    }

    public void buscarReserva(String id, ReservaTableModel reservaTableModel) {
        try {
            Long idL = Long.parseLong(id);
            System.out.println(idL);
            reservaTableModel.atualizarTabela(reservaDao.buscarReservas(idL));
        } catch (NumberFormatException ex) {
            reservaTableModel.atualizarTabela(reservaDao.getReservas());
        }
    }

    public void removerRegistro(HospedeTableModel tabelaHospede, ReservaTableModel tabelaReserva, int row) {
        Hospede hospede = tabelaHospede.getHospede(row);
        hospedeDao.removerHospede(hospede);
        tabelaHospede.removeRow(row);
        tabelaReserva.atualizarTabela(reservaDao.getReservas());
    }

    public void removerRegistro(ReservaTableModel tabela, int row, Long idReserva) {
        Reserva reserva = tabela.getReserva(row);
        tabela.removeRow(row);
        reservaDao.removerReserva(reserva, idReserva);
    }

    public void editarCelulaRegistro(JTable tabela, String nomeColuna, Long id, String nomeTabela,
                                     Object novoValor, int rowIndex, int columnIndex) {
        switch (nomeTabela) {
            case "Hospedes":
                editarCelulaRegistroHospede(nomeColuna, id, novoValor);
                break;
            case "Reservas":
                editarCelulaRegistroReserva(nomeColuna, id, novoValor);
                break;
        }
        tabela.setValueAt(novoValor, rowIndex, columnIndex);
    }

    private void editarCelulaRegistroHospede(String nomeColuna, Long id,
                                             Object novoValor) {
        HospedeDao hospedeDao = new HospedeDao(em);

        Hospede hospede = hospedeDao.getHospede(id);

        switch (nomeColuna) {
            case "Id":
                hospede.setId((Long) novoValor);
                break;
            case "Nome":
                hospede.setNome((String) novoValor);
                break;
            case "Sobrenome":
                hospede.setSobreNome((String) novoValor);
                break;
            case "Data Nascimento":
                hospede.setDataNascimento((LocalDate) novoValor);
                break;
            case "Nacionalidade":
                hospede.setNacionalidade((Nacionalidade) novoValor);
                break;
            case "Telefone":
                hospede.setTelefone((String) novoValor);
                break;
        }

        hospedeDao.atualizarHospede(hospede);
    }

    private void editarCelulaRegistroReserva(String nomeColuna, Long id,
                                             Object novoValor) {
        ReservaDao reservaDao = new ReservaDao(em);

        Reserva reserva = reservaDao.getReserva(id);

        switch (nomeColuna) {
            case "Id":
                reserva.setId((Long) novoValor);
                break;
            case "Data Entrada":
                reserva.setDataEntrada((LocalDate) novoValor);
                break;
            case "Data Saída":
                reserva.setDataSaida((LocalDate) novoValor);
                break;
            case "Valor":
                reserva.setValor((BigDecimal) novoValor);
                break;
            case "Forma de Pagamento":
                reserva.setFormaPagamento((FormaPagamento) novoValor);
        }

        reservaDao.atualizarReserva(reserva);
    }
}
