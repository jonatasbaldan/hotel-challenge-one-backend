package com.hotelalura.view;

import com.hotelalura.controller.RegistrarReservaController;
import com.hotelalura.model.FormaPagamento;
import com.hotelalura.model.Reserva;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class RegistrarReservaPanel extends JPanel {

    private JDateChooser dataCheckIn;
    private JDateChooser dataCheckOut;
    public JButton avancarButton;
    private JTextField totalField;
    private JComboBox<FormaPagamento> formasPagamentoBox;
    BigDecimal valorTotal;


    public RegistrarReservaPanel() {
        this.setBounds(240, 20, 520, 520);
        this.setLayout(null);
        this.setBackground(new Color(0xffffff));
        this.setName("Painel Cadastro Hospede");
        System.out.println(this.getName());
        this.setVisible(true);

        JLabel dataCheckInTexto = new JLabel("Data check in");
        dataCheckInTexto.setBounds(40, 120, 150, 32);
        this.add(dataCheckInTexto);

        dataCheckIn = new JDateChooser();
        dataCheckIn.setBounds(40, 140, 170, 32);
        dataCheckIn.setDateFormatString("dd-MM-yyy");
        dataCheckIn.setDate(Date.from(Instant.now()));
        dataCheckIn.addPropertyChangeListener(e -> calcularValor());
        this.add(dataCheckIn);

        JLabel dataCheckOutTexto = new JLabel("Data check out");
        dataCheckOutTexto.setBounds(40, 220, 150, 32);
        this.add(dataCheckOutTexto);

        dataCheckOut = new JDateChooser();
        dataCheckOut.setBounds(40, 240, 170, 32);
        dataCheckOut.setDateFormatString("dd-MM-yyy");
        dataCheckOut.setDate(Date.from(Instant.now()));
        dataCheckOut.addPropertyChangeListener(e -> calcularValor());
        this.add(dataCheckOut);

        totalField = new JTextField();
        totalField.setBounds(40, 260, 150, 32);
        totalField.setText("R$ 0,00");
        totalField.setEditable(false);
        totalField.setFocusable(false);
        totalField.setBackground(null);
        totalField.setBorder(null);
        this.add(totalField);

        JLabel pagamentoTexto = new JLabel("Formas de pagamento");
        pagamentoTexto.setBounds(40, 300, 150, 32);
        this.add(pagamentoTexto);

        formasPagamentoBox = new JComboBox<>(FormaPagamento.values());
        formasPagamentoBox.setBounds(40, 340, 150, 32);
        this.add(formasPagamentoBox);

        avancarButton = new JButton();
        avancarButton.setText("Avançar");
        avancarButton.setBounds(40, 390, 130, 32);
        this.add(avancarButton);
    }

    private void calcularValor() {
        Instant dataCheckInInstant = dataCheckIn.getDate().toInstant();
        Instant dataCheckOutInstant = dataCheckOut.getDate().toInstant();
        long diasEntreInEOut = ChronoUnit.DAYS.between(dataCheckInInstant, dataCheckOutInstant);
        diasEntreInEOut = diasEntreInEOut < 0 ? 0 : diasEntreInEOut + 1L;

        avancarButton.setEnabled(diasEntreInEOut != 0);

        valorTotal = new BigDecimal("20");
        valorTotal = valorTotal.multiply(BigDecimal.valueOf(diasEntreInEOut));
        totalField.setText(valorTotal + ",00");
    }

    public Reserva getReserva() {
        Reserva reserva = new Reserva();

        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
        Instant dataEntradaInstant = dataCheckIn.getDate().toInstant();
        Instant dataSaindaInstant = dataCheckOut.getDate().toInstant();

        reserva.setDataEntrada(LocalDate.ofInstant(dataEntradaInstant, zoneId));
        reserva.setDataSaida(LocalDate.ofInstant(dataSaindaInstant, zoneId));
        reserva.setFormaPagamento((FormaPagamento) formasPagamentoBox.getSelectedItem());
        reserva.setValor(valorTotal);
        System.out.println(reserva);

        return reserva;
    }
}
