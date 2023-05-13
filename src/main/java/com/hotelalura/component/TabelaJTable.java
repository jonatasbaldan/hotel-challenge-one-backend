package com.hotelalura.component;

import com.hotelalura.util.FonteUtil;

import javax.swing.*;

import javax.swing.table.TableModel;


public class TabelaJTable extends JTable {
    public TabelaJTable(TableModel tabelaModel, String nomeTabela) {
        this.setModel(tabelaModel);
        this.setName(nomeTabela);
        this.getTableHeader().setReorderingAllowed(false);
        this.setCellSelectionEnabled(true);
    }
}
