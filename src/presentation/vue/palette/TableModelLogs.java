package presentation.vue.palette;
import presentation.modele.Log;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModelLogs extends AbstractTableModel {
    private String[] columnsNames ;
    private Object[][] data ;

    public void initColumns(String...colNames){
        columnsNames = new String[colNames.length];
        for(int i=0 ; i<colNames.length;i++)
            columnsNames[i] = colNames[i];
    }

    public void initLogsData(List<Log> logs){
        data = new Object[logs.size()][columnsNames.length];
        int i=0 ;
        for(Log log : logs){
            data[i][0] =  log.getType();
            data[i][1] = log.getDate();
            data[i][2] = log.getMessage();
            i++;
        }
        this.fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return columnsNames[column];
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnsNames.length;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }
}
