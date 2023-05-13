package com.hotelalura.component;

import com.hotelalura.util.FonteUtil;

public class NomeTituloLabel extends NomeLabel {
    public NomeTituloLabel(String nome, int x, int y) {
        super(nome, x, y);
        this.setSize(500, 32);
        this.setFont(FonteUtil.getFontePadraoTitutlo());
    }
}
