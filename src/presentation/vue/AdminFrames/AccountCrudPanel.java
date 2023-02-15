package presentation.vue.AdminFrames;

import metier.admin.IServiceAdmin;
import metier.admin.ServiceAdmin;
import presentation.modele.Admin;
import presentation.vue.palette.FooterCrud;
import presentation.vue.palette.HeaderWithLinks;
import presentation.vue.palette.TablePanelAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AccountCrudPanel extends JPanel {
    ClassLoader cl = getClass().getClassLoader();
    HeaderWithLinks headerAdmin ;
    FooterCrud footerCrudAccount;

    TablePanelAccount tablePanelAccount ;


    void initPanels(){
        headerAdmin =new HeaderWithLinks(Color.BLACK,new Font("Verdana", Font.CENTER_BASELINE,15),new Font("Verdana",Font.ITALIC,12),Color.WHITE,Color.WHITE,new ImageIcon(cl.getResource("icons/menu1.png")),new ImageIcon(cl.getResource("icons/infoDropDown.png")),new Font("Verdana",Font.BOLD,17), Color.WHITE,"Client","Banque","Compte","Statistiques");
        headerAdmin.getSectionInformation().setNameUser(Admin.getInstance().getNomComplet());
        headerAdmin.getSectionInformation().setRoleUser(Admin.getInstance().getRole());
        tablePanelAccount = new TablePanelAccount(Color.BLACK,Color.WHITE,new Font("Verdana",Font.BOLD,19),new Font("Verdana",Font.CENTER_BASELINE,16));
        footerCrudAccount = new FooterCrud(Color.BLACK,"Chercher...",Color.BLACK, Color.GRAY, new ImageIcon(cl.getResource("icons/search.png")), new ImageIcon(cl.getResource("icons/searchHover.png")),Color.WHITE, new ImageIcon(cl.getResource("icons/add.png")), new ImageIcon(cl.getResource("icons/delete.png")));
    }

    public AccountCrudPanel(){
        initPanels();
        initActions();
        setLayout(new BorderLayout());
        add(footerCrudAccount,BorderLayout.SOUTH);
        add(tablePanelAccount,BorderLayout.CENTER);
        add(headerAdmin,BorderLayout.NORTH);
    }

   public void initActions(){
       IServiceAdmin serviceAdmin = new ServiceAdmin();
        //Bouton d'ajout
        footerCrudAccount.getTableCrudPanel().getAddButton().addActionListener(click->{
            new AddAccountDialog();
            tablePanelAccount.getTableModel().initAccountsData(serviceAdmin.listeComptes());
        });
        //Bouton de suppression
        footerCrudAccount.getTableCrudPanel().getDeleteButton().addActionListener(click->{
            int row = tablePanelAccount.getTable().getSelectedRow();
            if(row == -1){
                JOptionPane.showMessageDialog(this,
                        "Veuillez choisir un compte d'abord",
                        "A L E R T",
                        JOptionPane.ERROR_MESSAGE);
            }
            else{
                String id = (String) tablePanelAccount.getTableModel().getValueAt(row,0);
                serviceAdmin.supprimerCompte(id);
                tablePanelAccount.getTableModel().initAccountsData(serviceAdmin.listeComptes());
            }
        });

       footerCrudAccount.getSearchBar().getSearchButton().addKeyListener(new KeyAdapter() {
           @Override
           public void keyPressed(KeyEvent e) {
               if(e.getKeyCode() == KeyEvent.VK_ENTER){
                   footerCrudAccount.getSearchBar().getSearchButton().doClick();
               }
           }
       });
       footerCrudAccount.getSearchBar().getSearchButton().addActionListener(click->{
           String keyword = footerCrudAccount.getSearchBar().getSearchField().getText();
           if(!footerCrudAccount.getSearchBar().getHint().equals(keyword))
               tablePanelAccount.getTableModel().initAccountsData(serviceAdmin.chercherCompteParMotCle(keyword));
           else
               tablePanelAccount.getTableModel().initAccountsData(serviceAdmin.listeComptes());
       });


    }

}
