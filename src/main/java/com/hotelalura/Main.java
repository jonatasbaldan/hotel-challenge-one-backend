package com.hotelalura;

import com.formdev.flatlaf.FlatLightLaf;
import com.hotelalura.controller.MainController;
import com.hotelalura.view.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlatLightLaf.setup();
            new MainFrame(new MainController().getPainelPrincial());
        });
    }
}