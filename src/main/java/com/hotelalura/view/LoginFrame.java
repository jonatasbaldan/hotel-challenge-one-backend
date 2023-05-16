package com.hotelalura.view;

import com.hotelalura.component.EntradaDeTextoField;
import com.hotelalura.component.TextoLabel;
import com.hotelalura.component.TextoTituloLabel;
import com.hotelalura.component.PlaceHolderText;
import com.hotelalura.controller.MainController;
import com.hotelalura.util.FonteUtil;
import com.hotelalura.util.IconeUrlUtil;
import com.hotelalura.util.LoginUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {
    private EntradaDeTextoField usuarioField;
    private JPasswordField senhaField;

    public LoginFrame() throws HeadlessException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setSize( 300, 550); //sets x-dimension and y-dimension
        this.setResizable(false);

        this.setTitle("Sistema de Reservas Hotel Alura");

        this.setIconImage(new IconeUrlUtil().getIcone("images/icone-janela.png").getImage());
        this.setFont(FonteUtil.getFontePadrao());

        JPanel loginPanel = new JPanel();
        loginPanel.setBounds(0, 0,300, 550);
        loginPanel.setBackground(Color.white);
        loginPanel.setLayout(null);

        ImageIcon loginImageIcon = new IconeUrlUtil().getIcone("images/login-image.jpg");
        JLabel loginImageLabel = new JLabel(loginImageIcon);
        loginImageLabel.setBounds(0, -15, 300, 200);
        loginPanel.add(loginImageLabel);

        final int X = 60;

        TextoTituloLabel tituloLabel = new TextoTituloLabel("Login do usuário", X, 200);
        tituloLabel.setFont(FonteUtil.getFontePadraoTitutlo().deriveFont(Font.BOLD, 16));
        this.add(tituloLabel);

        TextoLabel usuarioLabel = new TextoLabel("Usuário", X, 250);
        this.add(usuarioLabel);

        usuarioField = new EntradaDeTextoField("Usuário", X, 280);
        this.add(usuarioField);

        TextoLabel senhaLabel = new TextoLabel("Senha", X, 330);
        loginPanel.add(senhaLabel);

        senhaField = new JPasswordField();
        senhaField.setBounds(X, 360, 150, 32);
        PlaceHolderText placeHolder = new PlaceHolderText("Senha", senhaField);
        placeHolder.setFont(FonteUtil.getFontePadrao());
        placeHolder.changeAlpha(0.3f);
        loginPanel.add(senhaField);

        JButton loginButton = new JButton("Entrar");
        loginButton.addActionListener(this::onEntrar);
        loginButton.setBounds(X, 420, 150, 32);
        loginPanel.add(loginButton);

        this.setVisible(true);
        this.add(loginPanel);
    }

    public void onEntrar(ActionEvent e) {
        String usuario = usuarioField.getText();
        String senha = String.valueOf(senhaField.getPassword());

        if (LoginUtil.LOGIN.equals(usuario) && LoginUtil.SENHA.equals(senha)) {
         this.dispose();
            new MainFrame(new MainController().getPainelPrincial());
        } else {
            JOptionPane.showMessageDialog(new Frame(), "Login Inválido, tente novamente.", "Erro ao entrar",
            JOptionPane.ERROR_MESSAGE);
        }
    }
}

