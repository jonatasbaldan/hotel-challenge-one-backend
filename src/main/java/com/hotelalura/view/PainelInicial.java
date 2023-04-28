package com.hotelalura.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PainelInicial extends JPanel {
    private PainelBuscar painelBuscar;
    private PainelRegistroReserva painelRegistroReserva;
    private PainelMenuLadoEsquerdo painelMenuLadoEsquerdo;
    private PainelHome painelHome;
    private JPanel painelDireito;

    public PainelInicial() {
        this.setLayout(null);
        this.setSize(800, 600);
        this.setBackground(new Color(0xf6f8fc));

        painelMenuLadoEsquerdo = new PainelMenuLadoEsquerdo();

        JButton homeButton = new BotaoMenuEsquerdo("Inicio", 80);
        homeButton.addActionListener(this::pintarPainelHome);
        painelMenuLadoEsquerdo.add(homeButton);

        JButton registroReservasButton = new BotaoMenuEsquerdo("Registro de Reservas", 140);
        registroReservasButton.addActionListener(this::pintarPainelReserva);
        painelMenuLadoEsquerdo.add(registroReservasButton);

        JButton buscarButton = new BotaoMenuEsquerdo("Buscar", 200);
        buscarButton.addActionListener(this::pintarPainelBuscar);
        painelMenuLadoEsquerdo.add(buscarButton);

        this.add(painelMenuLadoEsquerdo);
        this.setVisible(true);

        painelDireito = new PainelHome();
        painelDireito.setName("PainelHome");
    }

    private void pintarPainelHome(ActionEvent e) {
        if (!"PainelHome".equals(painelDireito.getName())) {
            this.remove(painelDireito);
            painelDireito = new PainelHome();
            painelDireito.setName("PainelHome");
            this.add(painelDireito);
            this.repaint();
            this.revalidate();
        }
    }

    public void pintarPainelReserva(ActionEvent e) {
        if (!"PainelReserva".equals(painelDireito.getName())){
            this.remove(painelDireito);
            painelDireito = new PainelRegistroReserva();
            painelDireito.setName("PainelReserva");
            this.add(painelDireito);
            this.repaint();
            this.revalidate();
        }
    }

    public void pintarPainelBuscar(ActionEvent e) {
        if (!"PainelBuscar".equals(painelDireito.getName())) {
            this.remove(painelDireito);
            painelDireito = new PainelBuscar();
            painelDireito.setName("PainelBuscar");
            this.add(painelDireito);
            this.repaint();
            this.revalidate();
        }
    }
}
