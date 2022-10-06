package game;

import database.ScoreModel;

import javax.swing.table.AbstractTableModel;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TableModel extends AbstractTableModel {

    private final String[] header = {"Rank", "Nome", "Jogadas", "Tempo", "Data", "Jogo", "Board"};
    private final ArrayList<ScoreModel> tabelaList;

    public TableModel(ArrayList<ScoreModel> tabelaList) {
        this.tabelaList = tabelaList;
    }

    @Override
    public int getRowCount() {

        return tabelaList.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return switch (columnIndex) {
            case 0 -> String.format("%dº ", rowIndex + 1);
            case 1 -> tabelaList.get(rowIndex).getNome();
            case 2 -> tabelaList.get(rowIndex).getJogadas();
            case 3 -> tabelaList.get(rowIndex).getTempo().toString();
            case 4 -> tabelaList.get(rowIndex).getData().format(formatter);
            case 5 -> tabelaList.get(rowIndex).getJogo();
            case 6 -> tabelaList.get(rowIndex).getBoard();
            default -> null;
        };

    }

    @Override
    public String getColumnName(int indice) {
        return header[indice];
    }
}
