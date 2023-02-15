package presentation.vue.palette;

import dao.dbFiles.ClientDAOFile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class TablePanelClient extends JPanel {
    private JTable table ;
    private TableModelClient tableModel;
    private JScrollPane scrollPane ;

    public TableModelClient getTableModel() {
        return tableModel;
    }

    public JTable getTable() {
        return table;
    }


    private void initTable(Color bgHeaderColor ,Color fgHeaderColor , Font headerFont , Font rowsFont){
        tableModel = new TableModelClient();
        tableModel.initColumns("Id", "Nom", "Prénom",
                "Login", "Pass", "Cin", "Email", "Tel", "Sexe");
        tableModel.initClientsData(new ClientDAOFile().findAll());
        table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);
        table.setFont(rowsFont);
        table.setRowHeight(35);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setShowGrid(false);
        scrollPane = new JScrollPane(table);
        JTableHeader header = table.getTableHeader();
        header.setFont(headerFont);
        header.setForeground(fgHeaderColor);
        header.setBackground(bgHeaderColor);
        ((DefaultTableCellRenderer)header.getDefaultRenderer())
                .setHorizontalAlignment(SwingConstants.CENTER);
        JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);
         header.setBorder(new LineBorder(bgHeaderColor,1,false));
    }
    public TablePanelClient(Color bgHeaderColor ,Color fgHeaderColor , Font headerFont , Font rowsFont){
        setBorder(new EmptyBorder(40,-10,-10,-1));
        initTable(bgHeaderColor,fgHeaderColor,headerFont,rowsFont);
        setLayout(new BorderLayout());
        add(scrollPane,BorderLayout.CENTER);
    }

}
