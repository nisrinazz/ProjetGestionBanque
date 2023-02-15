package presentation.vue.ClientFrames;
import metier.clients.ServiceClient;
import presentation.modele.Compte;
import presentation.vue.palette.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LogsPanel extends JPanel {
    Compte compte ;
    ClassLoader cl = getClass().getClassLoader();
    private TablePanelLogs tablePanelLogs;
    private HeaderWithLinks headerClient;

    private FilterPanel filterPanel;

    private SideMenuPanel sideMenuPanel;

    private FooterOperationsPanel footerOperations ;

    public SideMenuPanel getSideMenuPanel() {
        return sideMenuPanel;
    }

    public void initPanels(){
        headerClient =new HeaderWithLinks(Color.BLACK,new Font("Verdana", Font.CENTER_BASELINE,15),new Font("Verdana",Font.ITALIC,12),Color.WHITE,Color.WHITE,new ImageIcon(cl.getResource("icons/menu1.png")),new ImageIcon(cl.getResource("icons/infoDropDown.png")));
        headerClient.getSectionInformation().setNameUser(compte.getPropriétaire().getNomComplet());
        headerClient.getSectionInformation().setRoleUser(compte.getPropriétaire().getRole());
        tablePanelLogs = new TablePanelLogs(compte,Color.BLACK,Color.WHITE,new Font("Verdana",Font.BOLD,17),new Font("Verdana",Font.CENTER_BASELINE,14));
        sideMenuPanel = new SideMenuPanel(Color.BLACK,Color.WHITE,new Font("Verdana",Font.BOLD,17),"Accueil","Logs & Opérations","Changer le compte");
        filterPanel = new FilterPanel("Filtrer par :",new Font("Verdana",Font.BOLD,18),Color.BLACK,new Font("Verdana",Font.ITALIC,18),Color.BLACK,"Tous","Virement","Retrait","Versement","Opérations d'aujour'hui");
        footerOperations = new FooterOperationsPanel(Color.BLACK);
        initActions();
    }

    public void initActions(){
        ServiceClient serviceClient = new ServiceClient(compte);
        //SideMenuBar
        headerClient.getMenuDropDown().addActionListener(e->{
            if(sideMenuPanel.isVisible()) sideMenuPanel.setVisible(false);
            else sideMenuPanel.setVisible(true);
        });
        //FilterPanel
        filterPanel.getFilterby().forEach((name,button)->button.addActionListener(click->{
          if(name.equals("Virement")) tablePanelLogs.getTableModel().initLogsData(serviceClient.archiveVirements());
          else if(name.equals("Versement")) tablePanelLogs.getTableModel().initLogsData(serviceClient.archiveVersements());
          else if(name.equals("Retrait")) tablePanelLogs.getTableModel().initLogsData(serviceClient.archiveRetraits());
          else tablePanelLogs.getTableModel().initLogsData(serviceClient.listeLogs());
        }));
        //Opération

        footerOperations.getVersement().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new VersementOpDialog(compte);
                tablePanelLogs.getTableModel().initLogsData(serviceClient.listeLogs());
            }
        });
        footerOperations.getRetrait().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new RetraitOpDialog(compte);
                tablePanelLogs.getTableModel().initLogsData(serviceClient.listeLogs());
            }
        });
        footerOperations.getVirement().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new VirementOpDialog(compte);
                tablePanelLogs.getTableModel().initLogsData(serviceClient.listeLogs());
            }
        });

    }

    public LogsPanel(Compte compte){
        this.compte=compte;
        initPanels();
        setLayout(new BorderLayout());
        add(headerClient,BorderLayout.NORTH);
        add(sideMenuPanel,BorderLayout.WEST);
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20,0,0,0));
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(filterPanel);
        panel.add(tablePanelLogs);
        add(panel,BorderLayout.CENTER);
        add(footerOperations,BorderLayout.SOUTH);
    }

}
