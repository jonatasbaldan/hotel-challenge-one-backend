package com.hotelalura.controller;

import com.hotelalura.model.Hospede;
import com.hotelalura.model.Reserva;
import com.hotelalura.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.DateTimeException;
import java.util.Objects;

public class MainController {

    private JPanel menuEsquerdoPanel;
    private PrincipalPanel principalPanel;
    private JPanel homePanel;
    private RegistrarReservaPanel registrarReservaPainel;
    private BuscarPanel buscarPanel;
    private CadastrarHospedePanel cadastrarHospedePanel;
    private RegistrarReservaController registrarReservaController;
    private CadastrarHospedeController cadastrarHospedeController;
    private JButton homeButton;
    private JButton registroReservasButton;
    private JButton buscarButton;

    public MainController () {

        cadastrarHospedeController = new CadastrarHospedeController();
        registrarReservaController = new RegistrarReservaController();
        principalPanel = new PrincipalPanel();
        principalPanel.setName("Principal");
        menuEsquerdoPanel = new MenuEsquerdoPanel();
        menuEsquerdoPanel.setName("MenuEsquerdo");
        homePanel = new HomePanel();
        homePanel.setName("Home");
        registrarReservaPainel = new RegistrarReservaPanel();
        registrarReservaPainel.setName("RegistroReserva");
        buscarPanel = new BuscarPanel();
        buscarPanel.setName("PainelBuscar");

        homeButton = new MenuEsquerdoButton("Inicio", 80);
        homeButton.addActionListener(e -> onMudarPainel(homePanel));
        menuEsquerdoPanel.add(homeButton);

        registroReservasButton = new MenuEsquerdoButton("Cadastrar Hospede", 140);
        registroReservasButton.addActionListener(e -> onMudarPainel(registrarReservaPainel));
        menuEsquerdoPanel.add(registroReservasButton);

        buscarButton = new MenuEsquerdoButton("Buscar", 200);
        buscarButton.addActionListener(e -> onMudarPainel(buscarPanel));
        menuEsquerdoPanel.add(buscarButton);

        registrarReservaPainel.avancarButton.addActionListener(this::onAvancarCadastro);

        principalPanel = new PrincipalPanel();
        principalPanel.add(menuEsquerdoPanel);
        principalPanel.setVisible(true);
    }

    public void onMudarPainel(JPanel painel) {
        System.out.println(painel.getName());

        if ("CadastroHospede".equals(principalPanel.getNomePainel()) && "RegistroReserva".equals(painel.getName())) {
            principalPanel.atualizarPainelDireito(cadastrarHospedePanel);
        }
        else if (!painel.getName().equals(principalPanel.getNomePainel())) {
            System.out.println(painel.getName() + " if");
            principalPanel.atualizarPainelDireito(painel);
        }
    }

    public void onAvancarCadastro(ActionEvent e) {
        Reserva reserva = registrarReservaPainel.getReserva();
        registrarReservaController.prepararReserva(reserva);

        try {
            registrarReservaPainel.temDisponibilidade();

            cadastrarHospedePanel = new CadastrarHospedePanel(reserva);
            cadastrarHospedePanel.setName("CadastroHospede");
            cadastrarHospedePanel.salvarButton.addActionListener(ev -> onConcluirCadastro(reserva));
            cadastrarHospedePanel.cancelarButton.addActionListener(ev -> onCancelarCadastro(reserva));
            buscarButton.setEnabled(false);
            homeButton.setEnabled(false);
            onMudarPainel(cadastrarHospedePanel);
        } catch (DateTimeException ex) {
            JFrame tempFrame = new JFrame();
            JOptionPane.showMessageDialog(tempFrame, ex.getMessage(), "Erro ao reservar data", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onConcluirCadastro(Reserva reserva) {

        try {
            Hospede hospede = cadastrarHospedePanel.getHospede();
            System.out.println(hospede);

            cadastrarHospedeController.cadastrarHospede(hospede, buscarPanel.getHospedeTabelaModel());
            reserva.setHospede(hospede);
            registrarReservaController.cadastrarReserva(reserva, buscarPanel.getReservaTableModel());
            registrarReservaPainel.resetarCampos();
            cadastrarHospedePanel.resetarCampos();

            JFrame confirmacaoCadastroFrame = new JFrame();
            JOptionPane.showMessageDialog(confirmacaoCadastroFrame,"Cadastro concluido com sucesso!");

            buscarButton.setEnabled(true);
            homeButton.setEnabled(true);

            onMudarPainel(buscarPanel);

        } catch (NoSuchFieldException | NullPointerException e) {
            JFrame mensagemErroFrame = new JFrame();
            String mensagemErro = e.getClass().getSimpleName().equals("NullPointerException") ? "Selecione uma data válida" :
                    e.getMessage();

            JOptionPane.showMessageDialog(mensagemErroFrame, mensagemErro,
                    "Erro ao concluir o cadastro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancelarCadastro(Reserva reserva) {
        JFrame escolhaFrame = new JFrame();
        int escolha = JOptionPane.showConfirmDialog(escolhaFrame, "Tem certeza que deseja cancelar?",
                "Cancelar cadastro", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

        // Yes = 0, No = 1
        if (escolha == 0) {
            registrarReservaController.removerReserva(reserva);
            registrarReservaPainel.resetarCampos();

            buscarButton.setEnabled(true);
            homeButton.setEnabled(true);

            onMudarPainel(buscarPanel);
        }
    }

    public JPanel getPainelPrincial() {
        return principalPanel;
    }
}