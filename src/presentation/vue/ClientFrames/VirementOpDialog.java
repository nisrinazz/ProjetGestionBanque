package presentation.vue.ClientFrames;

import metier.clients.ServiceClient;
import presentation.modele.Compte;
import presentation.vue.palette.HeaderWithTitle;
import presentation.vue.palette.VirementForm;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class VirementOpDialog extends JDialog {
    ClassLoader cl = getClass().getClassLoader();
    Compte compte ;
    HeaderWithTitle headerWithTitle ;
    VirementForm virementForm;
    Container container ;

    public void initPanel(){
        headerWithTitle = new HeaderWithTitle(Color.WHITE,Color.BLACK,new ImageIcon(cl.getResource("icons/operation.png")),"Virement",new Font("Verdana",Font.BOLD,24));
        virementForm = new VirementForm(Color.BLACK,Color.WHITE,new Font("Verdana",Font.PLAIN,14),Color.WHITE,Color.WHITE,Color.BLACK,new Font("Verdana",Font.PLAIN,14));
        initActions();
    }
    public void initActions() {
        virementForm.getSubmitBtn().addActionListener(click->{
            virementForm.getMontantError().setVisible(false);
            virementForm.getIdCompteError().setVisible(false);
            ServiceClient serviceClient = new ServiceClient(compte);
            String numCompte = virementForm.getIdCompteField().getText();
            String montant = virementForm.getMontantField().getText();
            System.out.println(numCompte + " "+montant);
            Map<String, String> errors = serviceClient.virement(numCompte,montant);
            if (errors.isEmpty()) {
                dispose();
            } else {
                for (String error : errors.keySet()) {
                   if(error.equals("numCompte")) {
                        virementForm.getIdCompteError().setVisible(true);
                        virementForm.setIdCompteError(errors.get(error));
                    }
                   else{
                       virementForm.getMontantError().setVisible(true);
                       virementForm.setMontantError(errors.get(error));
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
        add(virementForm,BorderLayout.CENTER);
    }
    public VirementOpDialog(Compte compte){
        this.compte=compte;
        initContainer();
        setResizable(false);
        setSize(770,300);
        setTitle("Virement");
        setModal(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
