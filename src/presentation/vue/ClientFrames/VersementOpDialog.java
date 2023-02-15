package presentation.vue.ClientFrames;

import metier.Verifiable;
import metier.clients.ServiceClient;
import presentation.modele.Compte;
import presentation.vue.palette.HeaderWithTitle;
import presentation.vue.palette.OneFieldForm;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class VersementOpDialog extends JDialog implements Verifiable {
    ClassLoader cl = getClass().getClassLoader();
    Compte compte ;
    HeaderWithTitle headerWithTitle ;
    OneFieldForm versementForm;
    Container container ;

    public void initPanel(){
        headerWithTitle = new HeaderWithTitle(Color.WHITE,Color.BLACK,new ImageIcon(cl.getResource("icons/operation.png")),"Versement",new Font("Verdana",Font.BOLD,24));
        versementForm = new OneFieldForm(Color.BLACK,"Montant",Color.WHITE,new Font("Verdana",Font.PLAIN,14),Color.WHITE,"Valider",Color.WHITE,Color.BLACK,new Font("Verdana",Font.PLAIN,14));
        initActions();
    }
    public void initActions(){
        ServiceClient serviceClient = new ServiceClient(compte);
        versementForm.getSubmit().addActionListener(click-> {
            versementForm.getError().setVisible(false);
            String montant = versementForm.getField().getText();
            Map<String, String> errors = serviceClient.versement(montant);
            if (errors.isEmpty()) {
                dispose();
            } else {
                for (String error : errors.keySet()) {
                    versementForm.getError().setVisible(true);
                    versementForm.setError(errors.get(error));
                }
            }
        });

    }

    public void initContainer(){
        container = getContentPane();
        initPanel();
        setLayout(new BorderLayout());
        add(headerWithTitle,BorderLayout.NORTH);
        add(versementForm,BorderLayout.CENTER);
    }
    public VersementOpDialog(Compte compte){
        this.compte=compte;
        initContainer();
        setResizable(false);
        setSize(500,300);
        setTitle("Versement");
        setModal(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
