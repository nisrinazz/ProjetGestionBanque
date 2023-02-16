package presentation.vue.AdminFrames;
import metier.admin.IServiceAdmin;
import metier.admin.ServiceAdmin;
import presentation.vue.palette.AccountForm;
import presentation.vue.palette.HeaderWithTitle;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class AddAccountDialog extends JDialog {
   private IServiceAdmin serviceAdmin ;
   private ClassLoader cl = getClass().getClassLoader();
   private HeaderWithTitle headerWithTitle ;
  private   AccountForm accountForm;

  private Container container;


    public void initPanel(){
        headerWithTitle = new HeaderWithTitle(Color.WHITE,Color.BLACK,new ImageIcon(cl.getResource("icons/addAccount.png")),"Add Account",new Font("Verdana",Font.BOLD,24));
         accountForm = new AccountForm(Color.BLACK,Color.WHITE,new Font("Verdana",Font.PLAIN,14),Color.WHITE,Color.WHITE,Color.BLACK,new Font("Verdana",Font.PLAIN,14));
    }

    public void initContainer(){
        container = getContentPane();
        initPanel();
        initActions();
        container.setLayout(new BorderLayout());
        container.add(headerWithTitle,BorderLayout.NORTH);
        container.add(accountForm,BorderLayout.CENTER);
    }

    public void initActions(){
        accountForm.getSubmitBtn().addActionListener(click->{
            accountForm.getIdClientError().setVisible(false);
            accountForm.getSoldeError().setVisible(false);
            Long id = (Long)accountForm.getIdClientField().getSelectedItem();
            String solde = accountForm.getSoldeField().getText();
            Map<String,String> errors = serviceAdmin.nouveauCompte(id,solde);
            if(errors.isEmpty()) {
                dispose();
            }
            else {
                    for(String error : errors.keySet()) {
                        if(error.equals("idClient")) {
                            accountForm.getIdClientError().setVisible(true);
                            accountForm.setIdClientError(errors.get(error));
                        }
                        else {
                            accountForm.getSoldeError().setVisible(true);
                            accountForm.setSoldeError(errors.get(error));
                        }
                    }
            }
        });
    }

    public AddAccountDialog(IServiceAdmin serviceAdmin){
        this.serviceAdmin = serviceAdmin;
        initContainer();
        setResizable(false);
        setSize(700,300);
        setTitle("Add new Account");
        setModal(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
