package presentation.vue.palette;

import dao.dbFiles.ClientDAOFile;
import metier.admin.IServiceAdmin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import java.awt.*;

public class TablePanelClient extends JPanel {
    private JTable table ;
    private TableModelClient tableModel;
    private JScrollPane scrollPane ;

    IServiceAdmin serviceAdmin ;

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
        tableModel.initClientsData(serviceAdmin.listeClients());
        table = new JTable(tableModel);
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
    public TablePanelClient(IServiceAdmin serviceAdmin ,Color bgHeaderColor ,Color fgHeaderColor , Font headerFont , Font rowsFont){
        this.serviceAdmin = serviceAdmin;
        setBorder(new EmptyBorder(40,-10,-10,-1));
        initTable(bgHeaderColor,fgHeaderColor,headerFont,rowsFont);
        setLayout(new BorderLayout());
        add(scrollPane,BorderLayout.CENTER);
    }

}
