package com.hotelalura.component;

import com.hotelalura.util.FonteUtil;

import javax.swing.*;

public class BotaoButton extends JButton {
    public BotaoButton(String texto, int x, int y) {
        this.setText(texto);
        this.setFont(FonteUtil.getFontePadrao());
        this.setSize(150, 32);
        this.setLocation(x, y);
    }

    public BotaoButton(String texto, ImageIcon icone, int x, int y) {
        this(texto, x, y);
        this.setIcon(icone);
        this.setIconTextGap(8);
    }
}
