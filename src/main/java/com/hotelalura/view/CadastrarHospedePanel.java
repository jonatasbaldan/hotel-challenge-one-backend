package com.hotelalura.view;

import com.hotelalura.model.Hospede;
import com.hotelalura.model.Nacionalidade;
import com.hotelalura.model.Reserva;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;

public class CadastrarHospedePanel extends JPanel {
    private JTextField nomeField;
    private JTextField sobrenomeField;
    private JDateChooser dataNascimentoDate;
    private JComboBox<Nacionalidade> nacionalidadeLista;
    private JTextField telefoneField;
    private JTextField reservaIdField;
    public JButton salvarButton;
    public JButton cancelarButton;

    public CadastrarHospedePanel(Reserva reserva) {

        this.setLayout(null);
        this.setBounds(240, 20, 520, 520);
        this.setBackground(new Color(0xffffff));
        System.out.println("Painel Cadastro");
        this.setVisible(true);

        JLabel tituloHospedeLabel = new JLabel("Cadastrar Hospede");
        tituloHospedeLabel.setBounds(140, 40, 300, 32);
        tituloHospedeLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        this.add(tituloHospedeLabel);

        int alinhamentoX = 90;

        JLabel nomeLabel = new JLabel("Nome");
        nomeLabel.setBounds(alinhamentoX, 120, 150, 32);
        this.add(nomeLabel);

        nomeField = new JTextField();
        nomeField.setBounds(alinhamentoX, 150, 150, 32);
        this.add(nomeField);

        JLabel sobrenomeLabel = new JLabel("Sobrenome");
        sobrenomeLabel.setBounds(alinhamentoX + 170, 120, 150, 32);
        this.add(sobrenomeLabel);

        sobrenomeField = new JTextField();
        sobrenomeField.setBounds(alinhamentoX + 170, 150, 150, 32);
        this.add(sobrenomeField);

        JLabel dataNascimentoLabel = new JLabel("Data de Nascimento");
        dataNascimentoLabel.setBounds(alinhamentoX, 200, 150, 32);
        this.add(dataNascimentoLabel);

        dataNascimentoDate = new JDateChooser();
        dataNascimentoDate.setBounds(alinhamentoX, 230, 150, 32);
        dataNascimentoDate.setDateFormatString("yyyy-MM-dd");
        this.add(dataNascimentoDate);

        JLabel nacionalidadeLabel = new JLabel("Nacionalidade");
        nacionalidadeLabel.setBounds(alinhamentoX + 170, 200, 150, 32);
        this.add(nacionalidadeLabel);

        nacionalidadeLista = new JComboBox<>(Nacionalidade.values());
        nacionalidadeLista.setSelectedItem(Nacionalidade.BRASILEIRO);
        nacionalidadeLista.setBounds(alinhamentoX + 170, 230, 150, 32);
        this.add(nacionalidadeLista);

        JLabel telefoneLabel = new JLabel("Telefone");
        telefoneLabel.setBounds(alinhamentoX, 280, 150, 32);
        this.add(telefoneLabel);

        telefoneField = new JTextField();
        telefoneField.setBounds(alinhamentoX, 310, 150, 32);
        this.add(telefoneField);

        JLabel reservaIdLabel = new JLabel("Numero da Reserva");
        reservaIdLabel.setBounds(alinhamentoX + 170, 280, 150, 32);
        this.add(reservaIdLabel);

        reservaIdField = new JTextField(String.valueOf(reserva.getId()));
        reservaIdField.setBounds(alinhamentoX + 170, 310, 150, 32);
        reservaIdField.setEditable(false);
        this.add(reservaIdField);

        salvarButton = new JButton("Salvar");
        salvarButton.setBounds(alinhamentoX + 170, 400, 150, 32);
        this.add(salvarButton);

        cancelarButton = new JButton("Cancelar");
        cancelarButton.setBounds(alinhamentoX, 400, 150, 32);
        this.add(cancelarButton);
    }

    public Hospede getHospede() throws NoSuchFieldException, NullPointerException {

        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
        Instant dataNascimento = dataNascimentoDate.getDate().toInstant();

        Hospede hospede = new Hospede();
        hospede.setNome(nomeField.getText());
        hospede.setSobreNome(sobrenomeField.getText());
        hospede.setDataNascimento(LocalDate.ofInstant(dataNascimento, zoneId));
        hospede.setTelefone(telefoneField.getText());
        hospede.setNacionalidade((Nacionalidade) nacionalidadeLista.getSelectedItem());

        boolean isEmpty = hospede.getNome().isBlank() || hospede.getSobreNome().isBlank() ||
                hospede.getTelefone().isBlank();

        if (isEmpty) {
            throw new NoSuchFieldException("Algum campo não foi preenchido.");
        }

        return hospede;
    }

    public void resetarCampos() {
        nomeField.setText("");
        sobrenomeField.setText("");
        dataNascimentoDate.setDate(null);
        nacionalidadeLista.setSelectedItem(Nacionalidade.BRASILEIRO);
        telefoneField.setText("");
        reservaIdField.setText("");
    }
}
