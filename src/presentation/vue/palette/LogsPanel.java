package presentation.vue.palette;
import metier.clients.IServiceClient;
import metier.clients.ServiceClient;
import presentation.modele.Compte;
import presentation.vue.ClientFrames.RetraitOpDialog;
import presentation.vue.ClientFrames.VersementOpDialog;
import presentation.vue.ClientFrames.VirementOpDialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LogsPanel extends JPanel {

    ClassLoader cl = getClass().getClassLoader();
    private TablePanelLogs tablePanelLogs;

    private FilterSortPanel filterPanel;


    private FooterOperationsPanel footerOperations ;

    IServiceClient serviceClient;


    public void initPanels(){
        tablePanelLogs = new TablePanelLogs(serviceClient,Color.BLACK,Color.WHITE,new Font("Verdana",Font.BOLD,17),new Font("Verdana",Font.CENTER_BASELINE,14));
        filterPanel = new FilterSortPanel("Filtrer par :",new Font("Verdana",Font.BOLD,18),Color.BLACK,new Font("Verdana",Font.ITALIC,18),Color.BLACK,"Tous","Virement","Retrait","Versement","Opérations d'aujour'hui");
        footerOperations = new FooterOperationsPanel(Color.BLACK);
        initActions();
    }

    public void initActions(){
        //FilterPanel
        filterPanel.getList().forEach((name, button)->button.addActionListener(click->{
          if(name.equals("Virement")) tablePanelLogs.getTableModel().initLogsData(serviceClient.archiveVirements());
          else if(name.equals("Versement")) tablePanelLogs.getTableModel().initLogsData(serviceClient.archiveVersements());
          else if(name.equals("Retrait")) tablePanelLogs.getTableModel().initLogsData(serviceClient.archiveRetraits());
          else if(name.equals("Opérations d'aujour'hui")) tablePanelLogs.getTableModel().initLogsData(serviceClient.operationsAuj());
          else tablePanelLogs.getTableModel().initLogsData(serviceClient.listeLogs());
        }));
        //Opération

        footerOperations.getVersement().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(serviceClient.autorisationOp()){
                new VersementOpDialog(serviceClient);
                tablePanelLogs.getTableModel().initLogsData(serviceClient.listeLogs());}
                else {
                    JOptionPane.showMessageDialog(getParent(),
                            "Vous avez abouti le nombre max d'opérations par jour",
                            "A L E R T",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        footerOperations.getRetrait().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(serviceClient.autorisationOp()) {
                    new RetraitOpDialog(serviceClient);
                    tablePanelLogs.getTableModel().initLogsData(serviceClient.listeLogs());
                }
                else {
                    JOptionPane.showMessageDialog(getParent(),
                            "Vous avez abouti le nombre max d'opérations par jour",
                            "A L E R T",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        footerOperations.getVirement().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(serviceClient.autorisationOp()) {
                    new VirementOpDialog(serviceClient);
                    tablePanelLogs.getTableModel().initLogsData(serviceClient.listeLogs());
                }
                else {
                    JOptionPane.showMessageDialog(getParent(),
                            "Vous avez abouti le nombre max d'opérations par jour",
                            "A L E R T",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    public LogsPanel(IServiceClient serviceClient){
        this.serviceClient = serviceClient;
        initPanels();
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20,0,0,0));
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(filterPanel);
        panel.add(tablePanelLogs);
        add(panel,BorderLayout.CENTER);
        add(footerOperations,BorderLayout.SOUTH);
    }

}
