package com.hotelalura.component;

import com.hotelalura.util.FonteUtil;

import javax.swing.*;

public class MenuEsquerdoButton extends JButton {
    public MenuEsquerdoButton(String nomeBotao, int posicaoColuna) {
        this.setText(nomeBotao);
        this.setFont(FonteUtil.getFonteBotaoPadrao());
        this.setBounds(20, posicaoColuna, 180, 32);
    }
}