package com.hotelalura.view;

import javax.swing.*;
import java.awt.*;

public class BotaoMenuEsquerdo extends JButton {
    public BotaoMenuEsquerdo(String nomeBotao, int posicaoColuna) {
        this.setText(nomeBotao);
        this.setFont(new Font("Roboto", Font.BOLD, 13));
        this.setBounds(20, posicaoColuna, 180, 32);
    }
}