package presentation.vue.AdminFrames;

import metier.admin.IServiceAdmin;
import metier.admin.ServiceAdmin;
import presentation.modele.Admin;
import presentation.modele.Client;
import presentation.vue.palette.FooterCrud;
import presentation.vue.palette.HeaderWithLinks;
import presentation.vue.palette.TablePanelClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClientCrudPanel extends JPanel {
    ClassLoader cl = getClass().getClassLoader();
     HeaderWithLinks headerAdmin ;
    FooterCrud footerCrudClient;
    TablePanelClient tablePanelClient ;


    void initPanels(){
        footerCrudClient =  new FooterCrud(Color.BLACK,"Chercher...",Color.BLACK, Color.GRAY, new ImageIcon(cl.getResource("icons/search.png")), new ImageIcon(cl.getResource("icons/searchHover.png")),Color.WHITE, new ImageIcon(cl.getResource("icons/add.png")), new ImageIcon(cl.getResource("icons/edit.png")), new ImageIcon(cl.getResource("icons/delete.png")));
        headerAdmin =new HeaderWithLinks(Color.BLACK,new Font("Verdana", Font.CENTER_BASELINE,15),new Font("Verdana",Font.ITALIC,12),Color.WHITE,Color.WHITE,new ImageIcon(cl.getResource("icons/menu1.png")),new ImageIcon(cl.getResource("icons/infoDropDown.png")),new Font("Verdana",Font.BOLD,17), Color.WHITE,"Client","Banque","Compte","Statistiques");
        headerAdmin.getSectionInformation().setNameUser(Admin.getInstance().getNomComplet());
        headerAdmin.getSectionInformation().setRoleUser(Admin.getInstance().getRole());
        tablePanelClient = new TablePanelClient(Color.BLACK,Color.WHITE,new Font("Verdana",Font.BOLD,19),new Font("Verdana",Font.CENTER_BASELINE,16));
    }

    public ClientCrudPanel(){
        initPanels();
        setLayout(new BorderLayout());
        add(headerAdmin,BorderLayout.NORTH);
        add(footerCrudClient,BorderLayout.SOUTH);
        add(tablePanelClient,BorderLayout.CENTER);
        initActions();
    }

    public void initActions(){
        IServiceAdmin serviceAdmin = new ServiceAdmin();
        //Bouton d'ajout
        footerCrudClient.getTableCrudPanel().getAddButton().addActionListener(click->{
             new AddClientJDialog();
            tablePanelClient.getTableModel().initClientsData(serviceAdmin.listeClients());

        });
        //Bouton de suppression
        footerCrudClient.getTableCrudPanel().getDeleteButton().addActionListener(click->{
            int row = tablePanelClient.getTable().getSelectedRow();
              if(row == -1)
                  JOptionPane.showMessageDialog(this,
                          "Veuillez choisir un client d'abord",
                          "A L E R T",
                          JOptionPane.ERROR_MESSAGE);
              else {
                     long id = (long)tablePanelClient.getTableModel().getValueAt(row,0);
                     String nom = (String)tablePanelClient.getTableModel().getValueAt(row,1);
                     String prenom = (String)tablePanelClient.getTableModel().getValueAt(row,2);
                     String nomComplet = nom + " " + prenom ;
                     serviceAdmin.supprimerClientParId(id);
                     tablePanelClient.getTableModel().initClientsData(serviceAdmin.listeClients());
                     JOptionPane.showMessageDialog(this,
                          "Le Client "+nomComplet+ " a été supprimé avec succès",
                          "I N F O",
                          JOptionPane.INFORMATION_MESSAGE);
              }
        });
        //Bouton de modification
        footerCrudClient.getTableCrudPanel().getEditButton().addActionListener(click->{
            int row = tablePanelClient.getTable().getSelectedRow();
            if(row == -1)
                JOptionPane.showMessageDialog(this,
                        "Veuillez choisir un client d'abord",
                        "A L E R T",
                        JOptionPane.ERROR_MESSAGE);
            else {
                long id = (long)tablePanelClient.getTableModel().getValueAt(row,0);
                Client client = serviceAdmin.chercherClientParId(id);
                   new UpdateClientDialog(client);
                   tablePanelClient.getTableModel().initClientsData(serviceAdmin.listeClients());
            }
        });
        //Recherche
       footerCrudClient.getSearchBar().getSearchButton().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    footerCrudClient.getSearchBar().getSearchButton().doClick();
                }
            }
        });
        footerCrudClient.getSearchBar().getSearchButton().addActionListener(click->{
            String keyword = footerCrudClient.getSearchBar().getSearchField().getText();
            if(!footerCrudClient.getSearchBar().getHint().equals(keyword))
                    tablePanelClient.getTableModel().initClientsData(serviceAdmin.chercherClientParMotCle(keyword));
            else
                tablePanelClient.getTableModel().initClientsData(serviceAdmin.listeClients());
        });
    }

}
