package com.hotelalura.component;

import com.hotelalura.util.FonteUtil;

import javax.swing.*;

public class TextoLabel extends JLabel {
    public TextoLabel(String nome, int x, int y) {
        this.setText(nome);
        this.setLocation(x, y);
        this.setSize(150, 32);
        this.setFont(FonteUtil.getFontePadrao());
    }
}
