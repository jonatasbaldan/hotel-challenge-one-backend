package com.hotelalura.model;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
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

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                hospedeList.get(rowIndex).setId((Long) aValue);
                break;
            case 1:
                hospedeList.get(rowIndex).setNome((String) aValue);
                break;
            case 2:
                hospedeList.get(rowIndex).setSobreNome((String) aValue);
                break;
            case 3:
                hospedeList.get(rowIndex).setDataNascimento((LocalDate) aValue);
                break;
            case 4:
                hospedeList.get(rowIndex).setNacionalidade((Nacionalidade) aValue);
                break;
            case 5:
                hospedeList.get(rowIndex).setTelefone((String) aValue);
                break;
            default:
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public void fireTableCellUpdated(int rowIndex, int columnIndex) {
        fireTableChanged(new TableModelEvent(this, rowIndex, rowIndex, columnIndex));
    }

    public void addRow(Hospede hospede) {
        hospedeList.add(hospede);
        fireTableRowsInserted(hospedeList.size(), hospedeList.size() + 1);
    }

    public void removeRow(int rowIndex) {
        hospedeList.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public Hospede getHospede(int rowIndex) {
        return hospedeList.get(rowIndex);
    }

    public void atualizarHospedes(List<Hospede> hospedes) {
        hospedeList = hospedes;
        fireTableDataChanged();
    }
}
