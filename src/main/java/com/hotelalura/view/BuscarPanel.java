package com.hotelalura.view;

import com.hotelalura.controller.BuscarController;
import com.hotelalura.dao.HospedeDao;
import com.hotelalura.dao.ReservaDao;
import com.hotelalura.model.*;
import com.hotelalura.util.JpaUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;

public class BuscarPanel extends JPanel {
    private JButton registrarReservaButton;
    private JTextField buscarField;
    private JButton editarButton;
    private JButton excluirButton;
    private JTable hospedeTabela;
    private HospedeTableModel hospedeTabelaModel;
    private JTabbedPane paineisTabbed;
    private JTable reservaTabela;
    private ReservaTableModel reservaTabelaModel;
    private BuscarController buscarController;

    public BuscarPanel() {

        buscarController = new BuscarController();

        //TODO: Consertar Bug aonde o Hospede está fazendo 4 consultas.

        this.setBounds(240, 20, 520, 520);
        this.setBackground(new Color(0xffffff));
        System.out.println("Painel Buscar");
        this.setLayout(null);
        this.setVisible(true);

        buscarField = new JTextField();
        buscarField.setName("Campo Buscar");
        buscarField.setBounds(130, 30, 220, 32);
        this.add(buscarField);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.setBounds(350, 30, 120, 32);
        buscarButton.addActionListener(this::onBuscarDados);
        this.add(buscarButton);

        editarButton = new JButton("Editar");
        editarButton.setBounds(240, 80, 120, 32);
        editarButton.addActionListener(this::onEditarRegistro);
        this.add(editarButton);

        //TODO: Implementar os icones
        ImageIcon editarIcon = new ImageIcon();

        excluirButton = new JButton("Excluir");
        excluirButton.setBounds(380, 80, 120, 32);
        excluirButton.addActionListener(this::onDeletarRegistro);
        this.add(excluirButton);

        HospedeDao hospedeDao = new HospedeDao(JpaUtil.getEntityManager());
        List<Hospede> hospedes = hospedeDao.getHospedes();

        ReservaDao reservaDao = new ReservaDao(JpaUtil.getEntityManager());
        List<Reserva> reservas = reservaDao.getReservas();

        hospedeTabelaModel = new HospedeTableModel(hospedes);
        hospedeTabela = new JTable(hospedeTabelaModel);
        hospedeTabela.setName("Hospede");
        hospedeTabela.setCellSelectionEnabled(true);
        JScrollPane tabelaHospedeScroll = new JScrollPane(hospedeTabela);

        reservaTabelaModel = new ReservaTableModel(reservas);
        reservaTabela = new JTable(reservaTabelaModel);
        reservaTabela.setName("Reserva");
        reservaTabela.setCellSelectionEnabled(true);
        JScrollPane tabelaReservaScroll = new JScrollPane(reservaTabela);

        paineisTabbed = new JTabbedPane();
        paineisTabbed.setBounds(20, 90, 480, 360);
        paineisTabbed.addTab("Hospedes", tabelaHospedeScroll);
        paineisTabbed.addTab("Reservas", tabelaReservaScroll);
        this.add(paineisTabbed);

        registrarReservaButton = new JButton("Registrar Reserva");
        registrarReservaButton.addActionListener(this::onRegistarReserva);
        registrarReservaButton.setBounds(20, 470, 120, 32);
        this.add(registrarReservaButton);
    }

    public ReservaTableModel getReservaTableModel() {
        return reservaTabelaModel;
    }

    public HospedeTableModel getHospedeTabelaModel() {
        return hospedeTabelaModel;
    }

    private void onBuscarDados(ActionEvent e) {
        String painelSelecionado = paineisTabbed.getTitleAt(paineisTabbed.getSelectedIndex());

        switch (painelSelecionado) {
            case "Hospedes":
                String sobreNome = buscarField.getText();
                buscarController.buscarHospedes(sobreNome, hospedeTabelaModel);
                break;
            case "Reservas":
                    String id = buscarField.getText();
                    buscarController.buscarReserva(id, reservaTabelaModel);
        }
    }

    private void onEditarRegistro(ActionEvent e) {

        int indiceAba = paineisTabbed.getSelectedIndex();
        String abaSelecionada = paineisTabbed.getTitleAt(indiceAba);

        try {
            switch (abaSelecionada) {
                case "Hospedes":
                    editarRegistro(hospedeTabela);
                    break;
                case "Reservas":
                    editarRegistro(reservaTabela);
            }
        } catch (NumberFormatException ex) {
            JFrame frameError = new JFrame();
            JOptionPane.showMessageDialog(frameError, "Esse campo só aceita números.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        } catch (DateTimeParseException ex) {
            JFrame frameError = new JFrame();
            JOptionPane.showMessageDialog(frameError, "Esse campo só aceita data.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarRegistro(JTable tabela) {
        int row = tabela.getSelectedRow();
        int column = tabela.getSelectedColumn();

        String nomeColuna = "";
        try {
            nomeColuna = tabela.getColumnName(column);

            String mensagemDialogo = "Novo valor para " + nomeColuna;
            JFrame frame = new JFrame();

            Object antigoValorObj = tabela.getValueAt(row, column);
            String antigoValorNomeTipo = antigoValorObj.getClass().getSimpleName();
            Object novoValor = null;

            switch (antigoValorNomeTipo) {
                case "String":
                    String antigoValorString = (String) tabela.getValueAt(row, column);
                    novoValor = JOptionPane.showInputDialog(frame, mensagemDialogo, antigoValorString);
                    break;

                case "Long":
                    Long antigoValorLong = (Long) tabela.getValueAt(row, column);
                    novoValor = Long.parseLong(JOptionPane.showInputDialog(frame, mensagemDialogo, antigoValorLong));
                    break;

                case "LocalDate":
                    LocalDate antigoValorDate = (LocalDate) tabela.getValueAt(row, column);
                    novoValor = LocalDate.parse(JOptionPane.showInputDialog(frame, mensagemDialogo, antigoValorDate));
                    break;

                case "FormaPagamento":
                    FormaPagamento antigoValorPagamento = (FormaPagamento) tabela.getValueAt(row, column);
                    FormaPagamento[] formasPagamento = FormaPagamento.values();
                    novoValor = JOptionPane.showInputDialog(frame, mensagemDialogo, "Editar valor",
                            JOptionPane.PLAIN_MESSAGE, null, formasPagamento, antigoValorPagamento);
                    break;

                case "Nacionalidade":
                    Nacionalidade antigoValorNacionalidade = (Nacionalidade) tabela.getValueAt(row, column);
                    Nacionalidade[] nacionalidades = Nacionalidade.values();
                    novoValor = JOptionPane.showInputDialog(frame, mensagemDialogo, "Editar valor",
                            JOptionPane.PLAIN_MESSAGE, null, nacionalidades, antigoValorNacionalidade);
                    break;

                case "BigDecimal":
                    BigDecimal antigoValorBigDecimal = (BigDecimal) tabela.getValueAt(row, column);
                    novoValor = new BigDecimal(JOptionPane.showInputDialog(frame, mensagemDialogo, antigoValorBigDecimal));
            }

            if (Objects.nonNull(novoValor)) {
                Long id = (Long) tabela.getValueAt(row, 0);
                int indiceAba = paineisTabbed.getSelectedIndex();
                String columnName = tabela.getColumnName(column);
                String abaSelecionada = paineisTabbed.getTitleAt(indiceAba);

                buscarController.editarCelulaRegistro(tabela, columnName, id,
                        abaSelecionada, novoValor, row, column);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(new Frame(), "Nenhuma célula foi selecionada",
                    "Erro ao editar uma célula.", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onDeletarRegistro(ActionEvent e) {
        int indiceAba = paineisTabbed.getSelectedIndex();
        String abaSelecionada = paineisTabbed.getTitleAt(indiceAba);

        int row = 0;
        JFrame frame;
        String mensagem = "";
        String tituloJanela = "";
        int opcao = 3;

        switch (abaSelecionada) {
            case "Hospedes":
                try {
                    row = hospedeTabela.getSelectedRow();
                    String nome = (String) hospedeTabela.getValueAt(row, 1);
                    String sobreNome = (String) hospedeTabela.getValueAt(row, 2);
                    String nomeESobreNome = nome + " " + sobreNome;

                    mensagem = "Tem certeza que deseja deletar o hospede: " + nomeESobreNome + "?";
                    tituloJanela = "Deletar registro do hospede";

                    opcao = JOptionPane.showConfirmDialog(new JFrame(), mensagem, tituloJanela, JOptionPane.YES_NO_OPTION);
                    if (opcao == 0) buscarController.removerRegistro(hospedeTabelaModel, reservaTabelaModel, row);
                }
                catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(new Frame(), "Nenhum hospede foi selecionado.",
                            "Erro ao selecionar um registro.", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "Reservas":
                try {
                    row = reservaTabela.getSelectedRow();
                    Long idReserva = (Long) reservaTabela.getValueAt(row, 0);

                    mensagem = "Tem certeza que deseja deletar o registro da reserva do número: " + idReserva + "?";
                    tituloJanela = "Deletar registro da reserva";

                    opcao = JOptionPane.showConfirmDialog(new JFrame(), mensagem, tituloJanela, JOptionPane.YES_NO_OPTION);
                    if (opcao == 0) buscarController.removerRegistro(reservaTabelaModel, row, idReserva);
                } catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(new Frame(), "Nenhuma reserva foi selecionada.",
                            "Erro ao selecionar um registro.", JOptionPane.ERROR_MESSAGE);
                }
        }
    }

    private void onRegistarReserva(ActionEvent e) {

        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(520, 520);

        RegistrarReservaPanel reservaPanel = new RegistrarReservaPanel();
        reservaPanel.setBounds(0, 0, 520, 520);
        reservaPanel.avancarButton.setText("Concluir");

        int row = hospedeTabela.getSelectedRow();

        if (row != -1) {
            Hospede hospede = hospedeTabelaModel.getHospede(row);

            reservaPanel.avancarButton.addActionListener(ex -> {
                Reserva reserva = reservaPanel.getReserva();
                reserva.setHospede(hospede);
                buscarController.persistirReserva(reserva, reservaTabelaModel);
                JOptionPane.showMessageDialog(new JFrame(), "Reserva feita com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            });

            frame.add(reservaPanel);
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(new Frame(), "Nenhum hospede foi selecionado.",
                    "Erro registro de reserva.", JOptionPane.ERROR_MESSAGE);
        }
    }
}
