package com.hotelalura.view;

import com.hotelalura.dao.HospedeDao;
import com.hotelalura.dao.ReservaDao;
import com.hotelalura.model.*;
import com.hotelalura.util.JpaUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BuscarPanel extends JPanel {
    private JButton registrarReservaButton;
    private JTextField buscarField;
    private JButton editarButton;
    private JButton excluirButton;

    public BuscarPanel() {
        this.setBounds(240, 20, 520, 520);
        this.setBackground(new Color(0xffffff));
        System.out.println("Painel Buscar");
        this.setLayout(null);
        this.setVisible(true);

        buscarField = new JTextField();
        buscarField.setBounds(130, 30, 220, 32);
        this.add(buscarField);

        editarButton = new JButton("Editar");
        editarButton.setBounds(240, 80, 120, 32);
        this.add(editarButton);

        excluirButton = new JButton("Excluir");
        excluirButton.setBounds(380, 80, 120, 32);
        this.add(excluirButton);

        Hospede hospede = new Hospede();
        hospede.setDataNascimento(LocalDate.now());
        hospede.setNome("João");
        hospede.setSobreNome("Maria");
        hospede.setId(123L);
        hospede.setNacionalidade(Nacionalidade.COREANO);
        hospede.setTelefone("1788337363");

        Reserva reserva = new Reserva();
        reserva.setId(3322L);
        reserva.setValor(new BigDecimal("3933.3"));
        reserva.setDataEntrada(LocalDate.now());
        reserva.setDataSaida(LocalDate.now());
        reserva.setFormaPagamento(FormaPagamento.CREDITO);
        reserva.setHospede(hospede);

        HospedeDao hospedeDao = new HospedeDao(JpaUtil.getEntityManager());
        List<Hospede> hospedes = hospedeDao.getHospedes();
        hospedes.add(hospede);

        ReservaDao reservaDao = new ReservaDao(JpaUtil.getEntityManager());
        List<Reserva> reservas = reservaDao.getReservas();
        reservas.add(reserva);

        JTable tabelaHospede = new JTable(new HospedeTableModel(hospedes));
        JScrollPane tabelaHospedeScroll = new JScrollPane(tabelaHospede);

        JTable tabelaReserva = new JTable(new ReservaTableModel(reservas));
        JScrollPane tabelaReservaScroll = new JScrollPane(tabelaReserva);

        JTabbedPane paineis = new JTabbedPane();
        paineis.setBounds(20, 90, 480, 360);
        paineis.addTab("Hospedes", tabelaHospedeScroll);
        paineis.addTab("Reservas", tabelaReservaScroll);
        this.add(paineis);

        registrarReservaButton = new JButton("Registrar Reserva");
        registrarReservaButton.setBounds(20, 470, 120, 32);
        this.add(registrarReservaButton);
    }
}
