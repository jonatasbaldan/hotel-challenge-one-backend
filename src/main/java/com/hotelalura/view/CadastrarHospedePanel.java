package com.hotelalura.view;

import com.hotelalura.controller.CadastrarHospedeController;
import com.hotelalura.model.Hospede;
import com.hotelalura.model.Nacionalidade;
import com.hotelalura.model.Reserva;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class CadastrarHospedePanel extends JPanel {
    private JTextField nomeField;
    private JTextField sobrenomeField;
    private JDateChooser dataNascimentoDate;
    private JComboBox<Nacionalidade> nacionalidadeLista;
    private JTextField telefoneField;
    private JTextField reservaIdField;
    public JButton salvarButton;

    public CadastrarHospedePanel(Reserva reserva) {
        this.setLayout(null);
        this.setBounds(240, 20, 520, 520);
        this.setBackground(new Color(0xffffff));
        System.out.println("Painel Cadastro");
        this.setVisible(true);

        JLabel nomeLabel = new JLabel("Nome");
        nomeLabel.setBounds(20, 40, 150, 32);
        this.add(nomeLabel);

        nomeField = new JTextField();
        nomeField.setBounds(20, 80, 150, 32);
        this.add(nomeField);

        JLabel sobrenomeLabel = new JLabel("Nome");
        sobrenomeLabel.setBounds(20, 120, 150, 32);
        this.add(sobrenomeLabel);

        sobrenomeField = new JTextField();
        sobrenomeField.setBounds(20, 160, 150, 32);
        this.add(sobrenomeField);

        JLabel dataNascimentoLabel = new JLabel("Data de Nascimento");
        dataNascimentoLabel.setBounds(100, 200, 150, 32);
        this.add(dataNascimentoLabel);

        dataNascimentoDate = new JDateChooser();
        dataNascimentoDate.setBounds(20, 240, 150, 32);
        dataNascimentoDate.setDateFormatString("yyyy-MM-dd");
        this.add(dataNascimentoDate);

        JLabel nacionalidadeLabel = new JLabel("Nacionalidade");
        nacionalidadeLabel.setBounds(20, 280, 150, 32);
        this.add(nacionalidadeLabel);

        nacionalidadeLista = new JComboBox<>(Nacionalidade.values());
        nacionalidadeLista.setSelectedItem(Nacionalidade.BRASILEIRO);
        nacionalidadeLista.setBounds(20, 320, 150, 32);
        this.add(nacionalidadeLista);

        JLabel telefoneLabel = new JLabel("Telefone");
        telefoneLabel.setBounds(20, 360, 150, 32);
        this.add(telefoneLabel);

        telefoneField = new JTextField();
        telefoneField.setBounds(20, 400, 150, 32);
        this.add(telefoneField);

        JLabel reservaIdLabel = new JLabel("Numero da Reserva");
        reservaIdLabel.setBounds(20, 440, 150, 32);
        this.add(reservaIdLabel);

        reservaIdField = new JTextField(String.valueOf(reserva.getId()));
        reservaIdField.setBounds(20, 480, 150, 32);
        reservaIdField.setEditable(false);
        this.add(reservaIdField);

        salvarButton = new JButton("Salvar");
        salvarButton.setBounds(20, 500, 150, 32);
        this.add(salvarButton);
    }

    public Hospede getHospede() {
        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
        Instant dataNascimento = dataNascimentoDate.getDate().toInstant();

        Hospede hospede = new Hospede();
        hospede.setNome(nomeField.getText());
        hospede.setSobreNome(sobrenomeField.getText());
        hospede.setDataNascimento(LocalDate.ofInstant(dataNascimento, zoneId));
        hospede.setTelefone(telefoneField.getText());
        hospede.setNacionalidade((Nacionalidade) nacionalidadeLista.getSelectedItem());

        return hospede;
    }
}
