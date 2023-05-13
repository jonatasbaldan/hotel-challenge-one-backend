package com.hotelalura.component;

import com.hotelalura.util.FonteUtil;

import javax.swing.*;

public class EntradaDeTextoField extends JTextField {
    public EntradaDeTextoField(String placeHolderNome, int x, int y) {
        this.setSize(150, 32);
        this.setLocation(x, y);
        this.setFont(FonteUtil.getFontePadrao());

        PlaceHolderText placeHolder = new PlaceHolderText(placeHolderNome, this);
        placeHolder.changeAlpha(0.3f);
        placeHolder.setFont(FonteUtil.getFontePadraoPlaceHolder());
    }
}
