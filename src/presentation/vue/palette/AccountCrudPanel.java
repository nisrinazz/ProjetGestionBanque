package presentation.vue.palette;

import metier.admin.IServiceAdmin;
import metier.admin.ServiceAdmin;
import presentation.vue.AdminFrames.AddAccountDialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AccountCrudPanel extends JPanel {
   private ClassLoader cl = getClass().getClassLoader();
   private FooterCrud footerCrudAccount;
   private FilterSortPanel sortPanel ;

   private TablePanelAccount tablePanelAccount ;

  private   IServiceAdmin serviceAdmin ;

    public TablePanelAccount getTablePanelAccount() {
        return tablePanelAccount;
    }

    void initPanels(){
        tablePanelAccount = new TablePanelAccount(serviceAdmin,Color.BLACK,Color.WHITE,new Font("Verdana",Font.BOLD,19),new Font("Verdana",Font.CENTER_BASELINE,16));
        footerCrudAccount = new FooterCrud(Color.BLACK,"Chercher...",Color.BLACK, Color.GRAY, new ImageIcon(cl.getResource("icons/search.png")), new ImageIcon(cl.getResource("icons/searchHover.png")),Color.WHITE, new ImageIcon(cl.getResource("icons/add.png")), new ImageIcon(cl.getResource("icons/delete.png")));
        sortPanel = new FilterSortPanel("Trier par :",new Font("Verdana",Font.BOLD,18),Color.BLACK,new Font("Verdana",Font.ITALIC,18),Color.BLACK,"Aucun","Nom Propriétaire","Date de création","Solde");
    }

    public AccountCrudPanel(IServiceAdmin serviceAdmin){
        this.serviceAdmin = serviceAdmin;
        initPanels();
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20,0,0,0));
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(sortPanel);
        panel.add(tablePanelAccount);
        setLayout(new BorderLayout());
        add(panel,BorderLayout.CENTER);
        add(footerCrudAccount,BorderLayout.SOUTH);
        initActions();
    }

   public void initActions(){
       //Trier
       sortPanel.getList().forEach((name, button)->button.addActionListener(click->{
           if(name.equals("Nom Propriétaire")) tablePanelAccount.getTableModel().initAccountsData(serviceAdmin.trierComptesParNomPropriétaire());
           else if(name.equals("Date de création")) tablePanelAccount.getTableModel().initAccountsData(serviceAdmin.trierComptesParDateDeCreation());
           else if(name.equals("Solde")) tablePanelAccount.getTableModel().initAccountsData(serviceAdmin.trierComptesParSolde());
           else tablePanelAccount.getTableModel().initAccountsData(serviceAdmin.listeComptes());
       }));

       //Bouton d'ajout
        footerCrudAccount.getTableCrudPanel().getAddButton().addActionListener(click->{
            new AddAccountDialog(serviceAdmin);
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
