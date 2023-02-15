package presentation.vue.LoginFrames;


import metier.ServiceAuth.ServiceAuth;

import presentation.modele.Compte;

import presentation.vue.AdminFrames.ClientCrudPanel;
import presentation.vue.AdminFrames.MainFrameAdmin;
import presentation.vue.palette.FormLogin;
import presentation.vue.palette.HeaderWithTitle;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

import presentation.modele.Admin;

public class LoginFrame extends JFrame {

    ClassLoader cl = getClass().getClassLoader();
    HeaderWithTitle header ;
    FormLogin formLogin ;
    Container container ;

    public void initPanels(){
        header = new HeaderWithTitle(Color.WHITE,Color.BLACK,new ImageIcon(cl.getResource("icons/lock.png")),"Sign In",new Font("Arial",Font.BOLD,24));
        formLogin = new FormLogin(Color.BLACK,"Login","Password",new Font("Arial",Font.CENTER_BASELINE,17),Color.WHITE,"Login",Color.WHITE,Color.BLACK,new Font("Arial",Font.CENTER_BASELINE,15));
    }
    public void initContainer(){
        container = getContentPane();
        initPanels();
        container.setLayout(new BorderLayout());
        container.add(header,BorderLayout.NORTH);
        container.add(formLogin,BorderLayout.CENTER);
    }

    public LoginFrame(){
       initContainer();
       setSize(420,420);
       setResizable(false);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setVisible(true);
        initActions();
    }

    public void initActions(){
        formLogin.getLoginBtn().addActionListener(click->{
            formLogin.getErrorLogin().setVisible(false);
            formLogin.getErrorMdp().setVisible(false);
            formLogin.getErrorForm().setVisible(false);
            String login = formLogin.getLoginField().getText();
            char[] pass =formLogin.getPassField().getPassword();
            String passwordStr =new String(pass);
            ServiceAuth serviceAuth = new ServiceAuth();
            Map<String , String> errorList = serviceAuth.seConnecter(login,passwordStr);
            if(serviceAuth.getSession()!=null && errorList.isEmpty()){
                dispose();
                if(serviceAuth.getSession() instanceof Admin) new MainFrameAdmin();
                else {
                   List<Compte> comptes = serviceAuth.choisirCompte();
                   comptes.forEach(c-> System.out.println(c));
                   new ChooseAccountFrame(comptes);
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

    public static void main(String[] args) {
        new LoginFrame();
    }
}
