package presentation.vue.palette;
import presentation.modele.Compte;
import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.List;

public class TableModelAccount extends AbstractTableModel {
    private String[] columnsNames ;
    private Object[][] data ;



    public void initColumns(String...colNames){
        columnsNames = new String[colNames.length];
        for(int i=0 ; i<colNames.length;i++)
            columnsNames[i] = colNames[i];
    }

    public void initAccountsData(List<Compte> comptes){
        data = new Object[comptes.size()][columnsNames.length];
        int i=0 ;
        for(Compte compte : comptes){
            data[i][0]=compte.getNumeroCompte();
            data[i][1]=compte.getDateCreation().toLocalDate();
            data[i][2]=compte.getSolde() + " DH";
            data[i][3]=compte.getPropriétaire().getNomComplet();
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
