package presentation.vue.palette;
import dao.dbFiles.AgenceDAOFile;
import metier.admin.IServiceAdmin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class TablePanelAgence extends JPanel {
    private JTable table ;
    private TableModelAgence tableModel;
    private JScrollPane scrollPane ;

    IServiceAdmin serviceAdmin;
    public TableModelAgence getTableModel() {
        return tableModel;
    }

    public JTable getTable() {
        return table;
    }


    private void initTable(Color bgHeaderColor , Color fgHeaderColor , Font headerFont , Font rowsFont){
        tableModel = new TableModelAgence();
        tableModel.initColumns("Id", "Nom Agence", "Email",
                "Telephone", "Adresse");
        tableModel.initAgenceData(serviceAdmin.listeAgences());
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
    public TablePanelAgence(IServiceAdmin serviceAdmin ,Color bgHeaderColor ,Color fgHeaderColor , Font headerFont , Font rowsFont){
        this.serviceAdmin = serviceAdmin;
        setBorder(new EmptyBorder(40,-10,-10,-1));
        initTable(bgHeaderColor,fgHeaderColor,headerFont,rowsFont);
        setLayout(new BorderLayout());
        add(scrollPane,BorderLayout.CENTER);
    }

}
