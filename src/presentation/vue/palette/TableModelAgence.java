package presentation.vue.palette;

import presentation.modele.Agence;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModelAgence extends AbstractTableModel {
    private String[] columnsNames ;
    private Object[][] data ;



    public void initColumns(String...colNames){
        columnsNames = new String[colNames.length];
        for(int i=0 ; i<colNames.length;i++)
            columnsNames[i] = colNames[i];
    }

    public void initAgenceData(List<Agence> agences){
        data = new Object[agences.size()][columnsNames.length];
        int i=0 ;
        for(Agence agence : agences){
            data[i][0] =  agence.getIdBanque();
            data[i][1] =  agence.getNomBanque();
            data[i][2] =  agence.getEmailBanque();
            data[i][3] =  agence.getTelBanque();
            data[i][4] =  agence.getAdresseBanque();
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
