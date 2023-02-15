package presentation.vue.LoginFrames;

import dao.dbFiles.CompteDAOFile;
import metier.admin.ServiceAdmin;
import presentation.modele.Compte;
import presentation.vue.ClientFrames.MainFrameClient;
import presentation.vue.palette.ChooseAccountForm;
import presentation.vue.palette.HeaderWithTitle;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChooseAccountFrame extends JFrame {
    List<Compte> listeCompte;
    ClassLoader cl = getClass().getClassLoader();
    HeaderWithTitle header;
    ChooseAccountForm chooseAccountForm ;
    Container container;



    public void initPanels(){
        header = new HeaderWithTitle(Color.WHITE,Color.BLACK ,new ImageIcon(cl.getResource("icons/user.png")) ,new Font("Arial",Font.BOLD,24));
        chooseAccountForm = new ChooseAccountForm(listeCompte,Color.BLACK,"Valider","Annuler",Color.WHITE,Color.BLACK,new Font("Arial",Font.CENTER_BASELINE,15),"Vos comptes sont :",new Font("Arial",Font.CENTER_BASELINE,18),Color.WHITE);
        initActions();

    }

    public void initActions(){
        chooseAccountForm.getSubmitBtn().addActionListener(click->{
            chooseAccountForm.getErrorLabel().setVisible(false);
            ServiceAdmin serviceAdmin = new ServiceAdmin();
            String id = (String)chooseAccountForm.getNumCompteField().getSelectedItem();
            if(id != null) {
                dispose();
                new MainFrameClient(serviceAdmin.chercherCompteParId(id));
            }
            else {
                chooseAccountForm.getErrorLabel().setVisible(true);
                chooseAccountForm.setErrorLabel("Champ obligatoire");
            }
        });
    }
    public void initContainer(){
        initPanels();
        container=getContentPane();
        container.setLayout(new BorderLayout());
        container.add(header,BorderLayout.NORTH);
        container.add(chooseAccountForm,BorderLayout.CENTER);
    }

    public ChooseAccountFrame(List<Compte>comptes){
        this.listeCompte=comptes;
        initContainer();
        chooseAccountForm.initCompteData(comptes);
        this.header.setTitle("Hello ," + comptes.get(0).getPropriétaire().getPrenom());
        setSize(450,330);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
