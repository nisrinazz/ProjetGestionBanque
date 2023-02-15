package presentation.vue.palette;

import dao.dbFiles.CompteDAOFile;
import dao.dbFiles.LogDAOFile;
import presentation.modele.Client;
import presentation.modele.Compte;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class TablePanelLogs extends JPanel {
    private JTable table;
    private Compte compte ;
    private TableModelLogs tableModel;

    private JScrollPane scrollPane ;

    public TableModelLogs getTableModel() {
        return tableModel;
    }

    public JTable getTable() {
        return table;
    }


    private void initTable(Color bgHeaderColor , Color fgHeaderColor , Font headerFont , Font rowsFont){
        tableModel = new TableModelLogs();
        tableModel.initColumns("Type", "Date","Message");
        tableModel.initLogsData(new LogDAOFile().findAll(compte));
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
    public TablePanelLogs(Compte compte ,Color bgHeaderColor ,Color fgHeaderColor , Font headerFont , Font rowsFont){
        this.compte = compte;
        setBorder(new EmptyBorder(40,-10,-10,-1));
        initTable(bgHeaderColor,fgHeaderColor,headerFont,rowsFont);
        setLayout(new BorderLayout());
        add(scrollPane,BorderLayout.CENTER);
    }

}
