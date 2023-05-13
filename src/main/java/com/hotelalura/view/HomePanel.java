package com.hotelalura.view;

import com.hotelalura.component.NomeLabel;
import com.hotelalura.component.NomeTituloLabel;
import com.hotelalura.util.DateHotelUtil;
import com.hotelalura.util.FonteUtil;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class HomePanel extends JPanel {
    public HomePanel() {
        this.setBounds(240, 20, 520, 520);
        this.setLayout(null);
        this.setBackground(new Color(0xffffff));
        System.out.println("Painel Home");
        this.setVisible(true);

        JLabel tituloLabel = new NomeTituloLabel("Sistema de Reservas Hotel Alura", 60, 30);
        this.add(tituloLabel);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy");
        JLabel horasLabel = new NomeLabel(dtf.format(LocalDateTime.now()), 90, 72);
        horasLabel.setFont(FonteUtil.getFontePadrao().deriveFont(Font.PLAIN, 16));
        this.add(horasLabel);

        JLabel bemVindoLabel = new NomeTituloLabel("Seja bem vindo!", 30, 170);
        bemVindoLabel.setFont(FonteUtil.getFontePadrao().deriveFont(Font.BOLD, 18));
        this.add(bemVindoLabel);

        JTextArea avisosArea = new JTextArea();
        avisosArea.setText("""
                Sistema de reservas de hotéis. Controle e gerencie de forma otimizada e fácil o fluxo de reservas e hóspedes do hotel.
                
                Esta ferramenta permitirá que você mantenha um controle completo e detalhado de suas reservas e hóspedes, você terá acesso a ferramentas especiais para tarefas específicas como:
                
                - Registro de Reservas e Hóspedes;
                
                - Edição de Reservas e Hóspedes existentes;
                
                - Excluir todos os tipos de registros.""");

        avisosArea.setWrapStyleWord(true);
        avisosArea.setLineWrap(true);
        avisosArea.setEditable(false);
        avisosArea.setBounds(25, 220, 400, 300);
        avisosArea.setBackground(Color.white);
        avisosArea.setFont(FonteUtil.getFontePadrao().deriveFont(Font.PLAIN, 16));
        this.add(avisosArea);
    }
}
