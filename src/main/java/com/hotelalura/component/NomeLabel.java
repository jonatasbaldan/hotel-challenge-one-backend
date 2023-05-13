package com.hotelalura.component;

import com.hotelalura.util.FonteUtil;

import javax.swing.*;

public class NomeLabel extends JLabel {
    public NomeLabel(String nome, int x, int y) {
        this.setText(nome);
        this.setLocation(x, y);
        this.setSize(150, 32);
        this.setFont(FonteUtil.getFontePadrao());
    }
}

