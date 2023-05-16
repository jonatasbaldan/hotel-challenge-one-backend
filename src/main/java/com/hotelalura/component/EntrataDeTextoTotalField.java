package com.hotelalura.component;

import javax.swing.*;
import java.awt.*;

public class EntrataDeTextoTotalField extends JTextField {
    public EntrataDeTextoTotalField(int x, int y) {
        this.setFont(new Font("Roboto", Font.BOLD, 16));
        this.setText("R$ 0,00");
        this.setEditable(false);
        this.setFocusable(false);
        this.setBackground(null);
        this.setBorder(null);
        this.setSize(150, 32);
        this.setLocation(x, y);
    }
}
