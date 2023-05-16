package com.hotelalura.view;

import com.hotelalura.component.TextoLabel;
import com.hotelalura.component.TextoTituloLabel;
import com.hotelalura.util.FonteUtil;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InicioPanel extends JPanel {
/*    private JLabel qtdHospedesLabel;
    private JLabel qtdReservasLabel;*/
    public InicioPanel() {
        this.setBounds(240, 20, 520, 520);
        this.setLayout(null);
        this.setBackground(new Color(0xffffff));
        System.out.println("Painel Home");
        this.setVisible(true);

        JLabel tituloLabel = new TextoTituloLabel("Sistema de Reservas Hotel Alura", 60, 30);
        this.add(tituloLabel);

        /*HomeController homeController = new HomeController();
        String qtdReservas = String.valueOf(homeController.getNumeroTotalReservas());
        qtdReservasLabel = new NomeLabel(qtdReservas, 120, 90);
        qtdReservasLabel.setName("qtdReservas");
        qtdReservasLabel.setFont(FonteUtil.getFontePadrao().deriveFont(Font.BOLD, 24));
        this.add(qtdReservasLabel);

        JLabel reservasLabel = new NomeLabel("Reservas", 100, 120);
        reservasLabel.setFont(FonteUtil.getFontePadrao().deriveFont(Font.PLAIN, 16));
        this.add(reservasLabel);

        String qtdHospdes = String.valueOf(homeController.getNumeroTotalHospedes());
        qtdHospedesLabel = new NomeLabel(qtdHospdes, 330, 90);
        qtdHospedesLabel.setName("qtdHospedes");
        qtdHospedesLabel.setFont(FonteUtil.getFontePadrao().deriveFont(Font.BOLD, 24));
        this.add(qtdHospedesLabel);

        JLabel hospedesLabel = new NomeLabel("Hospedes", 305, 120);
        hospedesLabel.setFont(FonteUtil.getFontePadrao().deriveFont(Font.PLAIN, 16));
        this.add(hospedesLabel);*/

        JLabel bemVindoLabel = new TextoTituloLabel("Seja bem vindo!", 30, 170);
        bemVindoLabel.setFont(FonteUtil.getFontePadrao().deriveFont(Font.BOLD, 18));
        this.add(bemVindoLabel);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy");
        JLabel dataLabel = new TextoLabel(dtf.format(LocalDate.now()), 200, 90);
        dataLabel.setFont(FonteUtil.getFontePadraoTitutlo().deriveFont(Font.BOLD, 18));
        this.add(dataLabel);

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

/*    public void updateNumeroHospedesEReservas() {
        HomeController homeController = new HomeController();
        String qtdHospedes = String.valueOf(homeController.getNumeroTotalHospedes());
        String qtdReservas = String.valueOf(homeController.getNumeroTotalHospedes());
        qtdHospedesLabel.setText(qtdHospedes);
        qtdReservasLabel.setText(qtdReservas);
    }*/
}
