package presentation.vue.palette;

import presentation.modele.Client;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModelClient extends AbstractTableModel {

    private String[] columnsNames ;
    private Object[][] data ;



    public void initColumns(String...colNames){
        columnsNames = new String[colNames.length];
        for(int i=0 ; i<colNames.length;i++)
            columnsNames[i] = colNames[i];
    }

    public void initClientsData(List<Client> clients){
        data = new Object[clients.size()][columnsNames.length];
        int i=0 ;
        for(Client client : clients){
            data[i][0] =  client.getId();
            data[i][1] =  client.getNom();
            data[i][2] =  client.getPrenom();
            data[i][3] =  client.getLogin();
            data[i][4] =  client.getMotDePasse();
            data[i][5] =  client.getCin();
            data[i][6] =  client.getEmail();
            data[i][7] =  client.getTel();
            data[i][8] =  client.getSexe();
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
