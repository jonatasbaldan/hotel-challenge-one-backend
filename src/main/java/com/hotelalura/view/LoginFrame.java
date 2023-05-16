package com.hotelalura.view;

import com.hotelalura.util.FonteUtil;
import com.hotelalura.util.IconeUrlUtil;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    public LoginFrame(JPanel painelPrincial) throws HeadlessException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setLayout(null);
        this.setSize(800, 600); //sets x-dimension and y-dimension
        this.setResizable(false);

        this.setTitle("Sistema de Reservas Hotel Alura");

        this.setIconImage(new IconeUrlUtil().getIcone("images/icone-janela.png").getImage());
        this.add(painelPrincial);
        this.setFont(FonteUtil.getFontePadrao());
        this.setVisible(true);

        JPanel loginSide = new JPanel();
        loginSide.setBounds(400, 0,400, 600);
        loginSide.setBackground(Color.BLUE);
        this.add(loginSide);


        JPanel imageSide = new JPanel();
        imageSide.setBounds(0, 0, 400, 600);
        imageSide.setBackground(Color.RED);
        this.add(imageSide);
    }
}
