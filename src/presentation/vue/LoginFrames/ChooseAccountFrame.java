package presentation.vue.LoginFrames;

import dao.dbFiles.CompteDAOFile;
import metier.ServiceAuth.IServiceAuth;
import metier.admin.IServiceAdmin;
import metier.admin.ServiceAdmin;
import metier.clients.IServiceClient;
import metier.clients.ServiceClient;
import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.vue.ClientFrames.MainFrameClient;
import presentation.vue.palette.ChooseAccountForm;
import presentation.vue.palette.HeaderWithTitle;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;

public class ChooseAccountFrame extends JFrame {
    private List<Compte> listeCompte;
   private ClassLoader cl = getClass().getClassLoader();
   private HeaderWithTitle header;
   private ChooseAccountForm chooseAccountForm ;
   private Container container;
   private IServiceAuth serviceAuth ;
    private IServiceAdmin serviceAdmin ;

    public ChooseAccountForm getChooseAccountForm() {
        return chooseAccountForm;
    }

    public void initPanels(){
        header = new HeaderWithTitle(Color.WHITE,Color.BLACK ,new ImageIcon(cl.getResource("icons/user.png")) ,new Font("Arial",Font.BOLD,24));
        chooseAccountForm = new ChooseAccountForm(listeCompte,Color.BLACK,"Valider","Annuler",Color.WHITE,Color.BLACK,new Font("Arial",Font.CENTER_BASELINE,15),"Vos comptes sont :",new Font("Arial",Font.CENTER_BASELINE,18),Color.WHITE);
        chooseAccountForm.initCompteData(listeCompte);
        initActions();

    }

    public void initActions(){
        chooseAccountForm.getSubmitBtn().addActionListener(click->{
            chooseAccountForm.getErrorLabel().setVisible(false);
            String id = (String)chooseAccountForm.getNumCompteField().getSelectedItem();
            if(id != null) {
                dispose();
                IServiceClient serviceClient = new ServiceClient(serviceAdmin.chercherCompteParId(id));
                new MainFrameClient(serviceAuth,serviceAdmin,serviceClient);
            }
            else {
                chooseAccountForm.getErrorLabel().setVisible(true);
                chooseAccountForm.setErrorLabel("Champ obligatoire");
            }
        });
        chooseAccountForm.getCancelBtn().addActionListener(click->{
            dispose();
            serviceAuth.seDeconnecter();
            new LoginFrame(serviceAuth);
        });
    }
    public void initContainer(){
        initPanels();
        container=getContentPane();
        container.setLayout(new BorderLayout());
        container.add(header,BorderLayout.NORTH);
        container.add(chooseAccountForm,BorderLayout.CENTER);
    }

    public ChooseAccountFrame(IServiceAuth serviceAuth, IServiceAdmin serviceAdmin){
        this.serviceAuth = serviceAuth;
        this.serviceAdmin = serviceAdmin;
        this.listeCompte = serviceAuth.choisirCompte() ;
        initContainer();
        this.header.setTitle("Hello ," + serviceAuth.getSession().getPrenom() );
        setSize(450,330);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
