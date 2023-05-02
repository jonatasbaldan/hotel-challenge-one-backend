package com.hotelalura.model;

import com.hotelalura.model.Hospede;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class HospedeTableModel extends AbstractTableModel {

    private String[] columns = {"Id", "Nome", "Sobrenome", "Data Nascimento", "Nacionalidade", "Telefone"};
    private List<Hospede> hospedeList;

    public HospedeTableModel(List<Hospede> hospedes) {
        this.hospedeList = hospedes;
    }

    @Override
    public int getRowCount() {
        return hospedeList.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return hospedeList.get(rowIndex).getId();

            case 1:
                return hospedeList.get(rowIndex).getNome();

            case 2:
                return hospedeList.get(rowIndex).getSobreNome();

            case 3:
                return hospedeList.get(rowIndex).getDataNascimento();
            case 4:
                return hospedeList.get(rowIndex).getNacionalidade();
            case 5:
                return hospedeList.get(rowIndex).getTelefone();
            default:
                return "invalid";
        }
    }
}
