package com.hotelalura.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() throws HeadlessException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setLayout(null);
        this.setSize(800, 600); //sets x-dimension and y-dimension
        this.setResizable(false);

        this.add(new PainelInicial());

        this.setVisible(true);
    }
}