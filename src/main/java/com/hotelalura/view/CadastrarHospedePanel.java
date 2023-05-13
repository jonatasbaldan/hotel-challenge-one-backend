package com.hotelalura.view;

import com.hotelalura.component.BotaoButton;
import com.hotelalura.component.EscolherData;
import com.hotelalura.component.NomeLabel;
import com.hotelalura.component.NomeTituloLabel;
import com.hotelalura.component.EntradaDeTextoField;
import com.hotelalura.model.*;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
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
    public final JButton salvarButton;
    public final JButton cancelarButton;

    public CadastrarHospedePanel(Reserva reserva) {

        this.setLayout(null);
        this.setBounds(240, 20, 520, 520);
        this.setBackground(new Color(0xffffff));
        System.out.println("Painel Cadastro");
        this.setVisible(true);

        int alinhamentoXEsquerdo = 90;
        int alinhamentoXDireito = 260;

        JLabel tituloHospedeLabel = new NomeTituloLabel("Cadastrar Hospede", alinhamentoXEsquerdo, 40);
        this.add(tituloHospedeLabel);

        JLabel nomeLabel = new NomeLabel("Nome", alinhamentoXEsquerdo, 170);
        this.add(nomeLabel);

        nomeField = new EntradaDeTextoField("Seu nome", alinhamentoXEsquerdo, 200);
        this.add(nomeField);

        JLabel sobrenomeLabel = new NomeLabel("Sobrenome", alinhamentoXDireito, 170);
        this.add(sobrenomeLabel);

        sobrenomeField = new EntradaDeTextoField("Seu sobrenome", alinhamentoXDireito, 200);
        this.add(sobrenomeField);

        JLabel dataNascimentoLabel = new NomeLabel("Data de Nascimento", alinhamentoXEsquerdo, 250);
        this.add(dataNascimentoLabel);

        dataNascimentoDate = new EscolherData(alinhamentoXEsquerdo, 280);
        this.add(dataNascimentoDate);

        JLabel nacionalidadeLabel = new NomeLabel("Nacionalidade", alinhamentoXDireito, 250);
        this.add(nacionalidadeLabel);

        nacionalidadeLista = new JComboBox<>(Nacionalidade.values());
        nacionalidadeLista.setSelectedItem(Nacionalidade.BRASILEIRO);
        nacionalidadeLista.setBounds(alinhamentoXDireito, 280, 150, 32);
        this.add(nacionalidadeLista);

        JLabel telefoneLabel = new NomeLabel("Telefone", alinhamentoXEsquerdo, 330);
        this.add(telefoneLabel);

        telefoneField = new EntradaDeTextoField("2299883393", alinhamentoXEsquerdo, 360);
        this.add(telefoneField);

        JLabel reservaIdLabel = new NomeLabel("Numero da Reserva", alinhamentoXDireito, 330);
        this.add(reservaIdLabel);

        reservaIdField = new JTextField(String.valueOf(reserva.getId()));
        reservaIdField.setBounds(alinhamentoXDireito, 360, 150, 32);
        reservaIdField.setEditable(false);
        this.add(reservaIdField);

        salvarButton = new BotaoButton("Salvar", alinhamentoXEsquerdo, 450);
        this.add(salvarButton);

        cancelarButton = new BotaoButton("Cancelar", alinhamentoXDireito, 450);
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
            throw new NoSuchFieldException("Por favor, preencha todos os campos.");
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
