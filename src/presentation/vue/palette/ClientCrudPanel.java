package presentation.vue.palette;

import metier.admin.IServiceAdmin;
import metier.admin.ServiceAdmin;
import presentation.modele.Admin;
import presentation.modele.Client;
import presentation.vue.AdminFrames.AddClientJDialog;
import presentation.vue.AdminFrames.UpdateClientDialog;
import presentation.vue.palette.FooterCrud;
import presentation.vue.palette.HeaderWithLinks;
import presentation.vue.palette.TablePanelClient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClientCrudPanel extends JPanel {
    ClassLoader cl = getClass().getClassLoader();

    FooterCrud footerCrudClient;
    TablePanelClient tablePanelClient ;
    FilterSortPanel sortPanel ;

    IServiceAdmin serviceAdmin;


    void initPanels(){
        footerCrudClient =  new FooterCrud(Color.BLACK,"Chercher...",Color.BLACK, Color.GRAY, new ImageIcon(cl.getResource("icons/search.png")), new ImageIcon(cl.getResource("icons/searchHover.png")),Color.WHITE, new ImageIcon(cl.getResource("icons/add.png")), new ImageIcon(cl.getResource("icons/edit.png")), new ImageIcon(cl.getResource("icons/delete.png")));
        tablePanelClient = new TablePanelClient(serviceAdmin,Color.BLACK,Color.WHITE,new Font("Verdana",Font.BOLD,19),new Font("Verdana",Font.CENTER_BASELINE,16));
        sortPanel = new FilterSortPanel("Trier par :",new Font("Verdana",Font.BOLD,18),Color.BLACK,new Font("Verdana",Font.ITALIC,18),Color.BLACK,"Aucun","Nom","Prénom","Sexe","CIN","Email","Solde des comptes");
    }

    public ClientCrudPanel(IServiceAdmin serviceAdmin){
        this.serviceAdmin = serviceAdmin;
        initPanels();
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20,0,0,0));
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(sortPanel);
        panel.add(tablePanelClient);
        setLayout(new BorderLayout());
        add(footerCrudClient,BorderLayout.SOUTH);
        add(panel,BorderLayout.CENTER);
        initActions();
    }

    public void initActions(){
        //Trier
        sortPanel.getList().forEach((name, button)->button.addActionListener(click->{
            if(name.equals("Nom")) tablePanelClient.getTableModel().initClientsData(serviceAdmin.trierClientParNom());
            else if(name.equals("Prénom")) tablePanelClient.getTableModel().initClientsData(serviceAdmin.trierClientParPrenom());
            else if(name.equals("Sexe")) tablePanelClient.getTableModel().initClientsData(serviceAdmin.trierParSexe());
            else if(name.equals("CIN")) tablePanelClient.getTableModel().initClientsData(serviceAdmin.trierClientParCin());
            else if(name.equals("Email")) tablePanelClient.getTableModel().initClientsData(serviceAdmin.trierClientParEmail());
            else if(name.equals("Solde des comptes")) tablePanelClient.getTableModel().initClientsData(serviceAdmin.trierClientParSoldeCompte());
            else tablePanelClient.getTableModel().initClientsData(serviceAdmin.listeClients());
        }));
        //Bouton d'ajout
        footerCrudClient.getTableCrudPanel().getAddButton().addActionListener(click->{
             new AddClientJDialog(serviceAdmin);
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
                   new UpdateClientDialog(client,serviceAdmin);
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
