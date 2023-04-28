package com.hotelalura.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class PainelInicial extends JPanel {
    private PainelBuscar painelBuscar;
    private PainelRegistroReserva painelRegistroReserva;
    private PainelMenuLadoEsquerdo painelMenuLadoEsquerdo;
    private PainelHome painelHome;
    private JPanel testando;

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
    }

    private void pintarPainelHome(ActionEvent e) {
        if (Objects.nonNull(painelBuscar)) this.remove(painelBuscar);
        else if (Objects.nonNull(painelRegistroReserva)) this.remove(painelRegistroReserva);

        if (Objects.isNull(painelHome)) painelHome = new PainelHome();

        this.add(painelHome);
        this.repaint();
        this.revalidate();
    }

    public void pintarPainelReserva(ActionEvent e) {
        if (Objects.nonNull(painelBuscar)) this.remove(painelBuscar);
        else if (Objects.nonNull(painelHome)) this.remove(painelHome);

        if (Objects.isNull(painelRegistroReserva)) painelRegistroReserva = new PainelRegistroReserva();

        this.add(painelRegistroReserva);
        this.repaint();
        this.revalidate();
    }

    public void pintarPainelBuscar(ActionEvent e) {
        if (Objects.nonNull(painelRegistroReserva)) this.remove(painelRegistroReserva);
        else if (Objects.nonNull(painelHome)) this.remove(painelHome);

        if (Objects.isNull(painelBuscar)) painelBuscar = new PainelBuscar();

        this.add(painelBuscar);
        this.repaint();
        this.revalidate();
    }
}
