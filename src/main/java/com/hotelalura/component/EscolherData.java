package com.hotelalura.component;

import com.hotelalura.util.IconeUrlUtil;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;

public class EscolherData extends JDateChooser {
    public EscolherData(int x, int y) {
        ImageIcon dataNascimentoIcon = new IconeUrlUtil().getIcone("images/icone-calendario.png");
        this.setIcon(dataNascimentoIcon);
        this.setSize(150, 32);
        this.setLocation(x, y);
        this.getCalendarButton().setPreferredSize(new Dimension(32, 32));
        this.setDateFormatString("dd-MM-yyy");
    }
}
