package presentation.vue.palette;

import metier.admin.IServiceAdmin;
import metier.admin.ServiceAdmin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class AgenceInfoPanel extends JPanel {
    ClassLoader cl = getClass().getClassLoader();
    FooterCrud footerSearch;
    TablePanelAgence tablePanelAgence ;

    IServiceAdmin serviceAdmin ;


    void initPanels() {
       tablePanelAgence = new TablePanelAgence(serviceAdmin,Color.BLACK,Color.WHITE,new Font("Verdana",Font.BOLD,19),new Font("Verdana",Font.CENTER_BASELINE,16));
        footerSearch = new FooterCrud(Color.BLACK, "Chercher...", Color.BLACK, Color.GRAY, new ImageIcon(cl.getResource("icons/search.png")), new ImageIcon(cl.getResource("icons/searchHover.png")),Color.WHITE);
    }

    public AgenceInfoPanel(IServiceAdmin serviceAdmin){
        this.serviceAdmin = serviceAdmin;
        initPanels();
        setLayout(new BorderLayout());
        add(tablePanelAgence,BorderLayout.CENTER);
        add(footerSearch,BorderLayout.SOUTH);
        initActions();
    }

    public void initActions(){
        footerSearch.getSearchBar().getSearchButton().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    footerSearch.getSearchBar().getSearchButton().doClick();
                }
            }
        });
        footerSearch.getSearchBar().getSearchButton().addActionListener(click->{
            String keyword = footerSearch.getSearchBar().getSearchField().getText();
            if(!footerSearch.getSearchBar().getHint().equals(keyword))
                tablePanelAgence.getTableModel().initAgenceData(serviceAdmin.chercherAgenceParMotCle(keyword));
            else
                tablePanelAgence.getTableModel().initAgenceData(serviceAdmin.listeAgences());
        });

    }

}
