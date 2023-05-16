package com.hotelalura.component;

import com.hotelalura.util.FonteUtil;

public class TextoTituloLabel extends TextoLabel {
    public TextoTituloLabel(String nome, int x, int y) {
        super(nome, x, y);
        this.setSize(500, 32);
        this.setFont(FonteUtil.getFontePadraoTitutlo());
    }
}
