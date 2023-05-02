package com.hotelalura.view;

import javax.swing.*;
import java.awt.*;

public class PrincipalPanel extends JPanel {
    private JPanel painelDireito;

    public PrincipalPanel() {
        this.setLayout(null);
        this.setSize(800, 600);
        this.setBackground(new Color(0xf6f8fc));

        painelDireito = new HomePanel();
        painelDireito.setName("PainelHome");
        this.add(painelDireito);
    }

    public void atualizarPainelDireito(JPanel painel) {
        this.remove(painelDireito);
        painelDireito = painel;
        this.add(painel);
        this.repaint();
        this.revalidate();
    }

    public String getNomePainel() {
        return painelDireito.getName();
    }
}
