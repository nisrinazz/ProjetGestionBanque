package presentation.vue.LoginFrames;


import metier.ServiceAuth.IServiceAuth;
import metier.ServiceAuth.ServiceAuth;

import metier.admin.IServiceAdmin;
import metier.admin.ServiceAdmin;
import metier.clients.IServiceClient;
import metier.clients.ServiceClient;
import presentation.modele.Compte;

import presentation.vue.AdminFrames.MainFrameAdmin;
import presentation.vue.ClientFrames.MainFrameClient;
import presentation.vue.palette.FormLogin;
import presentation.vue.palette.HeaderWithTitle;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;
import java.util.Map;

import presentation.modele.Admin;

public class LoginFrame extends JFrame {

   private ClassLoader cl = getClass().getClassLoader();
   private HeaderWithTitle header ;
  private   FormLogin formLogin ;
   private Container container ;
   private IServiceAuth serviceAuth ;

    public void initPanels(){
        header = new HeaderWithTitle(Color.WHITE,Color.BLACK,new ImageIcon(cl.getResource("icons/lock.png")),"Sign In",new Font("Arial",Font.BOLD,24));
        formLogin = new FormLogin(Color.BLACK,"Login","Password",new Font("Arial",Font.CENTER_BASELINE,17),Color.WHITE,"Login",Color.WHITE,Color.BLACK,new Font("Arial",Font.CENTER_BASELINE,15),new ImageIcon(cl.getResource("icons/hidePass.png")),new ImageIcon(cl.getResource("icons/showPass.png")));
    }
    public void initContainer(){
        container = getContentPane();
        initPanels();
        container.setLayout(new BorderLayout());
        container.add(header,BorderLayout.NORTH);
        container.add(formLogin,BorderLayout.CENTER);
    }

    public LoginFrame(IServiceAuth serviceAuth){
        this.serviceAuth = serviceAuth;
       initContainer();
        setResizable(false);
       setSize(420,420);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initActions();
       setVisible(true);

    }

    public void initActions(){
        formLogin.getLoginBtn().addActionListener(click->{
            formLogin.getErrorLogin().setVisible(false);
            formLogin.getErrorMdp().setVisible(false);
            formLogin.getErrorForm().setVisible(false);
            String login = formLogin.getLoginField().getText();
            char[] pass =formLogin.getPassField().getPassword();
            String passwordStr =new String(pass);
            Map<String , String> errorList = serviceAuth.seConnecter(login,passwordStr);
            if(serviceAuth.getSession()!=null && errorList.isEmpty()){
                dispose();
                IServiceAdmin serviceAdmin = new ServiceAdmin();
                if(serviceAuth.getSession() instanceof Admin) {
                    new MainFrameAdmin(serviceAuth,serviceAdmin);
                }
                else {
                   List<Compte> comptes = serviceAuth.choisirCompte();
                   if(comptes.size() == 1) {
                       IServiceClient serviceClient = new ServiceClient(comptes.get(0));
                       new MainFrameClient(serviceAuth,serviceAdmin,serviceClient);
                   }
                  else new ChooseAccountFrame(serviceAuth,serviceAdmin);
                }
            }
            else if(!errorList.isEmpty()){
                for(String error : errorList.keySet())
                {
                    if(error.equalsIgnoreCase("login")) {
                        formLogin.getErrorLogin().setVisible(true);
                        formLogin.setErrorLogin(errorList.get(error));
                    }
                    else {
                        formLogin.getErrorMdp().setVisible(true);
                        formLogin.setErrorMdp(errorList.get(error));
                    }
                }
                    }
            else {
                formLogin.getErrorForm().setVisible(true);
                formLogin.setErrorForm("Utilisateur inexistant");
            }
                }
            );


    }
}
