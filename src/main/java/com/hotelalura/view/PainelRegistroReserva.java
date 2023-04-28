package com.hotelalura.view;

import com.hotelalura.model.FormaPagamento;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class PainelRegistroReserva extends JPanel {
    public PainelRegistroReserva() {
        this.setBounds(240, 20, 520, 520);
        this.setLayout(null);
        this.setBackground(new Color(0xffffff));
        System.out.println("Painel Registro Reserva");
        this.setVisible(true);

        JLabel dataCheckInTexto = new JLabel("Data check in");
        dataCheckInTexto.setBounds(40, 120, 150, 32);
        this.add(dataCheckInTexto);

        JDateChooser dataCheckIn = new JDateChooser();
        dataCheckIn.setBounds(40, 140, 170, 32);
        dataCheckIn.getCalendarButton().setBounds(138, 0, 32, 32);
        dataCheckIn.getCalendarButton().setBackground(Color.RED);
        dataCheckIn.setDateFormatString("yyyy-MM-dd");
        this.add(dataCheckIn);

        JLabel dataCheckOutTexto = new JLabel("Data check out");
        dataCheckOutTexto.setBounds(40, 220, 150, 32);
        this.add(dataCheckOutTexto);

        JDateChooser dataCheckOut = new JDateChooser();
        dataCheckOut.setBounds(40, 240, 170, 32);
        dataCheckOut.getCalendarButton().setBounds(138, 0, 32, 32);
        dataCheckOut.getCalendarButton().setBackground(Color.RED);
        dataCheckOut.setDateFormatString("yyyy-MM-dd");
        this.add(dataCheckOut);

        JLabel pagamentoTexto = new JLabel("Forma de pagamento");
        pagamentoTexto.setBounds(40, 300, 150, 32);
        this.add(pagamentoTexto);

        JComboBox<String> boxFormasDePagamento = new JComboBox<>(formasPagamento());
        boxFormasDePagamento.setBounds(40, 340, 150, 32);
        this.add(boxFormasDePagamento);
    }

    public String[] formasPagamento() {
        List<String> listaFormasDePagamento = new ArrayList<>();
        for (FormaPagamento formaPagamento : FormaPagamento.values()) {
            String pagamento = formaPagamento.name();
            listaFormasDePagamento.add(pagamento);
        }

        String[] arrayFormasDePagamento = new String[listaFormasDePagamento.size()];
        arrayFormasDePagamento = listaFormasDePagamento.toArray(arrayFormasDePagamento);

        return arrayFormasDePagamento;
    }
}