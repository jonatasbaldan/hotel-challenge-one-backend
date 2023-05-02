package com.hotelalura.model;

import com.hotelalura.model.Hospede;
import com.hotelalura.model.Reserva;

import javax.swing.table.AbstractTableModel;
import java.util.List;

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
}
