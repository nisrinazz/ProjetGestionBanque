package presentation.vue.ClientFrames;

import metier.clients.ServiceClient;
import presentation.modele.Compte;
import presentation.vue.palette.*;

import javax.swing.*;
import java.awt.*;

public class StatClientPanel extends JPanel {
    Compte compte ;
    ClassLoader cl = getClass().getClassLoader();
    private HeaderWithLinks headerClient;
    private SideMenuPanel sideMenuPanel;
    private InfoClientPanel infoClientPanel ;

    public SideMenuPanel getSideMenuPanel() {
        return sideMenuPanel;
    }

    public InfoClientPanel getInfoClientPanel() {
        return infoClientPanel;
    }

    public void initPanels(){
        headerClient =new HeaderWithLinks(Color.BLACK,new Font("Verdana", Font.CENTER_BASELINE,15),new Font("Verdana",Font.ITALIC,12),Color.WHITE,Color.WHITE,new ImageIcon(cl.getResource("icons/menu1.png")),new ImageIcon(cl.getResource("icons/infoDropDown.png")));
        headerClient.getSectionInformation().setNameUser(compte.getPropriétaire().getNomComplet());
        headerClient.getSectionInformation().setRoleUser(compte.getPropriétaire().getRole());
        sideMenuPanel = new SideMenuPanel(Color.BLACK,Color.WHITE,new Font("Verdana",Font.BOLD,17),"Accueil","Logs & Opérations","Changer le compte");
        infoClientPanel = new InfoClientPanel(compte);
        initActions();
    }

    public void initActions(){
        ServiceClient serviceClient = new ServiceClient(compte);
        //SideMenuBar
        headerClient.getMenuDropDown().addActionListener(e->{
            if(sideMenuPanel.isVisible()) sideMenuPanel.setVisible(false);
            else sideMenuPanel.setVisible(true);
        });
    }

    public StatClientPanel(Compte compte){
        this.compte=compte;
        initPanels();
        setLayout(new BorderLayout());
        add(headerClient,BorderLayout.NORTH);
        add(sideMenuPanel,BorderLayout.WEST);
        add(infoClientPanel,BorderLayout.CENTER);
    }

}
