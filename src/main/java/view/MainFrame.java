package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() throws HeadlessException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setLayout(null);
        this.setSize(320, 493); //sets x-dimension and y-dimension
        this.setResizable(false);
        this.setVisible(true);
    }
}