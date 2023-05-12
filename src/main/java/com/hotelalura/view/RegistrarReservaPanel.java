package com.hotelalura.view;

import com.hotelalura.controller.RegistrarReservaController;
import com.hotelalura.model.FormaPagamento;
import com.hotelalura.model.Reserva;
import com.hotelalura.util.DateUtilHotel;
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

    private JDateChooser dataCheckIn;
    private JDateChooser dataCheckOut;
    public JButton avancarButton;
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

        this.setFont(new Font("Arial", Font.PLAIN, 13));

        JLabel tituloReservaLabel = new JLabel("Registrar Reserva");
        tituloReservaLabel.setBounds(140, 40, 300, 32);
        tituloReservaLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        this.add(tituloReservaLabel);

        int alinhamentoX = 160;

        JLabel dataCheckInTexto = new JLabel("Data check in");
        dataCheckInTexto.setBounds(alinhamentoX, 120, 150, 32);
        this.add(dataCheckInTexto);

        dataCheckIn = new JDateChooser();
        dataCheckIn.setBounds(alinhamentoX, 150, 170, 32);
        dataCheckIn.setDateFormatString("dd-MM-yyy");
        dataCheckIn.setDate(Date.from(Instant.now()));
        dataCheckIn.addPropertyChangeListener(e -> calcularValor());
        this.add(dataCheckIn);

        JLabel dataCheckOutTexto = new JLabel("Data check out");
        dataCheckOutTexto.setBounds(alinhamentoX, 200, 150, 32);
        this.add(dataCheckOutTexto);

        dataCheckOut = new JDateChooser();
        dataCheckOut.setBounds(alinhamentoX, 230, 170, 32);
        dataCheckOut.setDateFormatString("dd-MM-yyy");
        dataCheckOut.setDate(Date.from(Instant.now()));
        dataCheckOut.addPropertyChangeListener(e -> calcularValor());
        this.add(dataCheckOut);

        JLabel pagamentoTexto = new JLabel("Formas de pagamento");
        pagamentoTexto.setBounds(alinhamentoX, 280, 150, 32);
        this.add(pagamentoTexto);

        formasPagamentoBox = new JComboBox<>(FormaPagamento.values());
        formasPagamentoBox.setBounds(alinhamentoX, 310, 150, 32);
        this.add(formasPagamentoBox);

        JLabel precoLabel = new JLabel("Preço Total: ");
        precoLabel.setBounds(alinhamentoX, 360, 170, 32);
        this.add(precoLabel);

        totalField = new JTextField();
        totalField.setBounds(alinhamentoX, 390, 150, 32);
        totalField.setText("R$ 0,00");
        totalField.setEditable(false);
        totalField.setFocusable(false);
        totalField.setBackground(null);
        totalField.setBorder(null);
        this.add(totalField);

        avancarButton = new JButton("Avançar");
        avancarButton.setBounds(alinhamentoX, 440, 130, 32);
        this.add(avancarButton);
    }

    private void calcularValor() {
        Instant dataCheckInInstant = dataCheckIn.getDate().toInstant();
        Instant dataCheckOutInstant = dataCheckOut.getDate().toInstant();
        long diasEntreInEOut = ChronoUnit.DAYS.between(dataCheckInInstant, dataCheckOutInstant);
        diasEntreInEOut = diasEntreInEOut < 0 ? 0 : diasEntreInEOut + 1L;

        avancarButton.setEnabled(diasEntreInEOut != 0);

        BigDecimal valorPorDia = new BigDecimal(20);

        valorTotal = valorPorDia;
        valorTotal = valorTotal.multiply(BigDecimal.valueOf(diasEntreInEOut));
        totalField.setText("R$ " + valorTotal + ",00");
    }

    public Reserva getReserva() {
        Reserva reserva = new Reserva();

        Instant dataEntradaInstant = dataCheckIn.getDate().toInstant();
        Instant dataSaidaInstant = dataCheckOut.getDate().toInstant();

        reserva.setDataEntrada(LocalDate.ofInstant(dataEntradaInstant, DateUtilHotel.getZoneId()));
        reserva.setDataSaida(LocalDate.ofInstant(dataSaidaInstant, DateUtilHotel.getZoneId()));
        reserva.setFormaPagamento((FormaPagamento) formasPagamentoBox.getSelectedItem());
        reserva.setValor(valorTotal);
        System.out.println("id: " + reserva.getId());

        return reserva;
    }

    public void resetarCampos() {
        dataCheckIn.setDate(Date.from(Instant.now()));
        dataCheckOut.setDate(Date.from(Instant.now()));
        formasPagamentoBox.setSelectedItem(FormaPagamento.CREDITO);
    }

    public void temDisponibilidade() throws DateTimeException {
        Date dataIn = dataCheckIn.getDate();
        Date dataOut = dataCheckOut.getDate();
        RegistrarReservaController registrarReservaController = new RegistrarReservaController();

        if (!registrarReservaController.temReservaDisponibilidade(dataIn, "Entrada")) {
            System.out.println("Entrada");
            throw new DateTimeException("A data de Check In já está reservada. Escolha outra data.");
        } else if (!registrarReservaController.temReservaDisponibilidade(dataOut, "Saida")) {
            System.out.println("Saida");
            throw new DateTimeException("A data de Check Out já está reservada. Escolha outra data.");
        }
    }
}
