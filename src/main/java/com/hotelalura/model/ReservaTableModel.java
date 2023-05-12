package com.hotelalura.model;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class ReservaTableModel extends AbstractTableModel {
    private String[] columns = {"Id", "Data Entrada", "Data Saída", "Valor", "Forma de Pagamento"};
    private List<Reserva> reservaList;


    public ReservaTableModel(List<Reserva> reservas) {
        this.reservaList = reservas;
    }

    @Override
    public int getRowCount() {
        return reservaList.size();
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
                return reservaList.get(rowIndex).getId();
            case 1:
                return reservaList.get(rowIndex).getDataEntrada();
            case 2:
                return reservaList.get(rowIndex).getDataSaida();
            case 3:
                return reservaList.get(rowIndex).getValor();
            case 4:
                return reservaList.get(rowIndex).getFormaPagamento();
            default:
                return "invalid";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                reservaList.get(rowIndex).setId((Long) aValue);
                break;
            case 1:
                reservaList.get(rowIndex).setDataEntrada((LocalDate) aValue);
                break;
            case 2:
                reservaList.get(rowIndex).setDataSaida((LocalDate) aValue);
                break;
            case 3:
                reservaList.get(rowIndex).setValor((BigDecimal) aValue);
                break;
            case 4:
                reservaList.get(rowIndex).setFormaPagamento((FormaPagamento) aValue);
                break;
            default:
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void removeRow(int rowIndex) {
        reservaList.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addRow(Reserva reserva) {
        reservaList.add(reserva);
        fireTableRowsInserted(reservaList.size(), reservaList.size() + 1);
    }

    public Reserva getReserva(int row) {
        return reservaList.get(row);
    }

    public void atualizarTabela(List<Reserva> reservas) {
        reservaList = reservas;
        fireTableDataChanged();
    }
}
