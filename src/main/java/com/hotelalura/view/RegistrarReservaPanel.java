package com.hotelalura.view;

import com.hotelalura.component.*;
import com.hotelalura.controller.RegistrarReservaController;
import com.hotelalura.model.*;
import com.hotelalura.util.DateHotelUtil;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class RegistrarReservaPanel extends JPanel {

    private JDateChooser checkInDate;
    private JDateChooser checkOutDate;
    public final JButton avancarButton;
    private JTextField totalField;
    private JComboBox<FormaPagamento> formasPagamentoBox;
    private BigDecimal valorTotal;

    public RegistrarReservaPanel() {
        this.setBounds(240, 20, 520, 520);
        this.setLayout(null);
        this.setBackground(new Color(0xffffff));
        this.setName("Painel Cadastro Hospede");
        System.out.println(this.getName());
        this.setVisible(true);

        int alinhamentoXEsquerdo = 90;
        int alinhamentoXDireito = 260;

        JLabel tituloReservaLabel = new TextoTituloLabel("Registrar Reserva", alinhamentoXEsquerdo, 40);
        this.add(tituloReservaLabel);

        JLabel checkInLabel = new TextoLabel("Data check in", alinhamentoXEsquerdo, 170);
        this.add(checkInLabel);

        checkInDate = new EscolherData(alinhamentoXEsquerdo, 200);
        checkInDate.setDate(DateHotelUtil.dataAgora);
        checkInDate.addPropertyChangeListener(e -> onCalcularValor());
        this.add(checkInDate);

        JLabel checkOutLabel = new TextoLabel("Data check out", alinhamentoXDireito, 170);
        this.add(checkOutLabel);

        checkOutDate = new EscolherData(alinhamentoXDireito, 200);
        checkOutDate.setDate(DateHotelUtil.dataAgora);
        checkOutDate.addPropertyChangeListener(e -> onCalcularValor());
        this.add(checkOutDate);

        JLabel pagamentoLabel = new TextoLabel("Formas de pagamento", alinhamentoXEsquerdo, 250);
        this.add(pagamentoLabel);

        formasPagamentoBox = new JComboBox<>(FormaPagamento.values());
        formasPagamentoBox.setBounds(alinhamentoXEsquerdo, 280, 150, 32);
        this.add(formasPagamentoBox);

        JLabel precoLabel = new TextoLabel("Preço Total", alinhamentoXEsquerdo, 330);
        this.add(precoLabel);

        totalField = new EntrataDeTextoTotalField(alinhamentoXEsquerdo, 360);
        this.add(totalField);

        avancarButton = new BotaoButton("Avançar", alinhamentoXEsquerdo, 450);
        this.add(avancarButton);
    }

    private void onCalcularValor() {
        Instant dataCheckInInstant = checkInDate.getDate().toInstant();
        Instant dataCheckOutInstant = checkOutDate.getDate().toInstant();
        long diasEntreInEOut = ChronoUnit.DAYS.between(dataCheckInInstant, dataCheckOutInstant);
        diasEntreInEOut = diasEntreInEOut < 0 ? 0 : diasEntreInEOut + 1L;

        avancarButton.setEnabled(diasEntreInEOut != 0);

        valorTotal = RegistrarReservaController.valorPorDia;
        valorTotal = valorTotal.multiply(BigDecimal.valueOf(diasEntreInEOut));
        totalField.setText("R$ " + valorTotal + ",00");
    }

    public Reserva getReserva() {
        Reserva reserva = new Reserva();

        Instant dataEntradaInstant = checkInDate.getDate().toInstant();
        Instant dataSaidaInstant = checkOutDate.getDate().toInstant();

        reserva.setDataEntrada(LocalDate.ofInstant(dataEntradaInstant, DateHotelUtil.getZoneId()));
        reserva.setDataSaida(LocalDate.ofInstant(dataSaidaInstant, DateHotelUtil.getZoneId()));
        reserva.setFormaPagamento((FormaPagamento) formasPagamentoBox.getSelectedItem());
        reserva.setValor(valorTotal);
        System.out.println("id: " + reserva.getId());

        return reserva;
    }

    public void resetarCampos() {
        checkInDate.setDate(Date.from(Instant.now()));
        checkOutDate.setDate(Date.from(Instant.now()));
        formasPagamentoBox.setSelectedItem(FormaPagamento.CREDITO);
    }

    public void checarDisponibilidade() throws DateTimeException {
        Date dataIn = checkInDate.getDate();
        Date dataOut = checkOutDate.getDate();
        RegistrarReservaController registrarReservaController = new RegistrarReservaController();

        if (!registrarReservaController.isReservaDisponivel(dataIn, "Entrada")) {
            System.out.println("Entrada");
            throw new DateTimeException("A data de Check In já está reservada. Escolha outra data.");
        } else if (!registrarReservaController.isReservaDisponivel(dataOut, "Saida")) {
            System.out.println("Saida");
            throw new DateTimeException("A data de Check Out já está reservada. Escolha outra data.");
        }
    }
}
