package presentation.vue.AdminFrames;

import metier.admin.ServiceAdmin;
import presentation.vue.palette.ClientForm;
import presentation.vue.palette.HeaderWithTitle;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class AddClientJDialog extends JDialog {
    ClassLoader cl = getClass().getClassLoader();
    HeaderWithTitle headerWithTitle ;
    ClientForm clientForm;
    Container container ;


    public void initPanel(){
      headerWithTitle = new HeaderWithTitle(Color.WHITE,Color.BLACK,new ImageIcon(cl.getResource("icons/AddUser.png")),"Add Client",new Font("Arial",Font.BOLD,24));
      clientForm = new ClientForm(Color.BLACK,"Ajouter",Color.WHITE,Color.BLACK,new Font("Arial",Font.BOLD,15),Color.WHITE,new Font("Arial",Font.PLAIN,14),Color.WHITE);
      initActions();
    }

   public void initActions(){
       ServiceAdmin serviceAdmin = new ServiceAdmin();
            clientForm.getSubmitBtn().addActionListener(e->{
            clientForm.getErrorNom().setVisible(false);
            clientForm.getErrorPrenom().setVisible(false);
            clientForm.getErrorLogin().setVisible(false);
            clientForm.getErrorMdp().setVisible(false);
            clientForm.getErrorTel().setVisible(false);
            clientForm.getErrorMail().setVisible(false);
            clientForm.getErrorCin().setVisible(false);
            String nom    =    clientForm.getNom().getText();
            String prenom =    clientForm.getPrenom().getText();
            String login =     clientForm.getLogin().getText();
            String mdp =       clientForm.getMdp().getText();
            String tel = clientForm.getTel().getText();
            String cin = clientForm.getCin().getText();
            String email = clientForm.getMail().getText();
            String sexe = (String)clientForm.getSexe().getSelectedItem();
           Map<String,String> errorList =  serviceAdmin.ajouterClient(nom,prenom,login,mdp,email,cin,tel,sexe);
           if(errorList.isEmpty())
               dispose();
           else {
               for(String error : errorList.keySet()) {
                      if(error.equalsIgnoreCase("nom"))
                      {   clientForm.getErrorNom().setVisible(true);
                          clientForm.setErrorNom(errorList.get(error));
                      }
                      else if(error.equalsIgnoreCase("prenom"))
                      {   clientForm.getErrorPrenom().setVisible(true);
                          clientForm.setErorrPrenom(errorList.get(error));
                      }
                      else if(error.equalsIgnoreCase("login"))
                      {   clientForm.getErrorLogin().setVisible(true);
                          clientForm.setErrorLogin(errorList.get(error));
                      }
                      else if(error.equalsIgnoreCase("mot de passe"))
                      {   clientForm.getErrorMdp().setVisible(true);
                          clientForm.setErrorMdp(errorList.get(error));
                      }
                      else if(error.equalsIgnoreCase("CIN"))
                      {   clientForm.getErrorCin().setVisible(true);
                          clientForm.setErrorCin(errorList.get(error));
                      }
                      else if(error.equalsIgnoreCase("email"))
                      {   clientForm.getErrorMail().setVisible(true);
                          clientForm.setErrorMail(errorList.get(error));
                      }
                      else
                      {   clientForm.getErrorTel().setVisible(true);
                          clientForm.setErrorTel(errorList.get(error));
                      }
               }
           }
            });
}

    public void initContainer(){
        container = getContentPane();
        initPanel();
        setLayout(new BorderLayout());
        add(headerWithTitle,BorderLayout.NORTH);
        add(clientForm,BorderLayout.CENTER);
    }

    public AddClientJDialog(){
        initContainer();
        setResizable(false);
        setSize(700,500);
        setTitle("Add new Client");
        setModal(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
