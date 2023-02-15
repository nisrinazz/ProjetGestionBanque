package presentation.vue.AdminFrames;

import presentation.modele.Admin;
import presentation.vue.palette.HeaderWithLinks;
import presentation.vue.palette.StatisticPanel;

import javax.swing.*;
import java.awt.*;


public class MainStatisticPanel extends JPanel {
   private ClassLoader cl = getClass().getClassLoader();
   private HeaderWithLinks headerAdmin ;
   private StatisticPanel statisticPanel;

    public StatisticPanel getStatisticPanel() {
        return statisticPanel;
    }

    public HeaderWithLinks getHeaderAdmin() {
        return headerAdmin;
    }

    public void initPanels(){
        headerAdmin = new HeaderWithLinks(Color.BLACK,new Font("Verdana", Font.CENTER_BASELINE,15),new Font("Verdana",Font.ITALIC,12),Color.WHITE,Color.WHITE,new ImageIcon(cl.getResource("icons/menu1.png")),new ImageIcon(cl.getResource("icons/infoDropDown.png")),new Font("Verdana",Font.BOLD,17), Color.WHITE,"Client","Banque","Compte","Statistiques");
        headerAdmin.getSectionInformation().setNameUser(Admin.getInstance().getNomComplet());
        headerAdmin.getSectionInformation().setRoleUser(Admin.getInstance().getRole());
        statisticPanel = new StatisticPanel();
    }
    public MainStatisticPanel(){
        initPanels();
        setLayout(new BorderLayout());
        add(headerAdmin,BorderLayout.NORTH);
        add(statisticPanel,BorderLayout.CENTER);
    }
}
