package com.hotelalura.controller;

import com.hotelalura.model.Hospede;
import com.hotelalura.model.Reserva;
import com.hotelalura.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class MainController {

    private JPanel painelMenuEsquerdo;
    private PrincipalPanel principalPanel;
    private JPanel painelHome;
    private RegistrarReservaPanel registroReservaPainel;
    private BuscarPanel painelBuscar;
    private CadastrarHospedePanel painelCadastroHospede;
    private ServiceController serviceController;

    public MainController () {

        serviceController = new ServiceController();
        principalPanel = new PrincipalPanel();
        principalPanel.setName("Painel Principal");
        painelMenuEsquerdo = new MenuEsquerdoPanel();
        painelMenuEsquerdo.setName("Painel Menu Esquerdo");
        painelHome = new HomePanel();
        painelHome.setName("Painel Home");
        registroReservaPainel = new RegistrarReservaPanel();
        registroReservaPainel.setName("Painel Registro Reserva");
        painelBuscar = new BuscarPanel();
        painelBuscar.setName("Painel Buscar");

        JButton homeButton = new MenuEsquerdoButton("Inicio", 80);
        homeButton.addActionListener(e -> mudarPainel(e, painelHome));
        painelMenuEsquerdo.add(homeButton);

        JButton registroReservasButton = new MenuEsquerdoButton("Cadastrar Hospede", 140);
        registroReservasButton.addActionListener(e -> mudarPainel(e, registroReservaPainel));
        painelMenuEsquerdo.add(registroReservasButton);

        JButton buscarButton = new MenuEsquerdoButton("Buscar", 200);
        buscarButton.addActionListener(e -> mudarPainel(e, painelBuscar));
        painelMenuEsquerdo.add(buscarButton);

        registroReservaPainel.avancarButton.addActionListener(this::avancarCadastro);

        principalPanel = new PrincipalPanel();
        principalPanel.add(painelMenuEsquerdo);
        principalPanel.setVisible(true);
    }

    public void mudarPainel(ActionEvent e, JPanel painel) {
        System.out.println(painel.getName());
        if (!painel.getName().equals(principalPanel.getNomePainel())) {
            System.out.println(painel.getName() + " if");
            principalPanel.atualizarPainelDireito(painel);
        }
    }

    public void avancarCadastro(ActionEvent e) {
        Reserva reserva = registroReservaPainel.getReserva();
        serviceController.cadastrarReserva(reserva);

        painelCadastroHospede = new CadastrarHospedePanel(reserva);
        painelCadastroHospede.salvarButton.addActionListener(this::concluirCadastro);
        principalPanel.atualizarPainelDireito(painelCadastroHospede);
    }

    private void concluirCadastro(ActionEvent e) {

        Hospede hospede = painelCadastroHospede.getHospede();

        serviceController.cadastrarHospede(hospede);
        registroReservaPainel = new RegistrarReservaPanel();

        JFrame confirmacaoCadastroFrame = new JFrame();
        JOptionPane.showMessageDialog(confirmacaoCadastroFrame,"Cadastro concluido com sucesso!");
        mudarPainel(e, painelBuscar);
    }

    public JPanel getPainelPrincial() {
        return principalPanel;
    }
}