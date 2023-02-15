package presentation.vue.ClientFrames;

import metier.Verifiable;
import metier.clients.ServiceClient;
import presentation.modele.Compte;
import presentation.vue.palette.HeaderWithTitle;
import presentation.vue.palette.OneFieldForm;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class RetraitOpDialog extends JDialog implements Verifiable{
    Compte compte ;
    ClassLoader cl = getClass().getClassLoader();
    HeaderWithTitle headerWithTitle ;
    OneFieldForm retraitForm;
    Container container ;

    public void initPanel(){
        headerWithTitle = new HeaderWithTitle(Color.WHITE,Color.BLACK,new ImageIcon(cl.getResource("icons/operation.png")),"Retrait",new Font("Verdana",Font.BOLD,24));
        retraitForm = new OneFieldForm(Color.BLACK,"Montant",Color.WHITE,new Font("Verdana",Font.PLAIN,14),Color.WHITE,"Valider",Color.WHITE,Color.BLACK,new Font("Verdana",Font.PLAIN,14));
        initAction();
    }

    public void initAction(){
        ServiceClient serviceClient = new ServiceClient(compte);
        retraitForm.getSubmit().addActionListener(click->{
            retraitForm.getError().setVisible(false);
            String montant = retraitForm.getField().getText();
            Map<String,String> errors = serviceClient.retrait(montant);
            if(errors.isEmpty()) {
                dispose();
            }
            else {
                for(String error : errors.keySet()) {
                        retraitForm.getError().setVisible(true);
                        retraitForm.setError(errors.get(error));
                }
            }
        });
    }

    public void initContainer(){
        container = getContentPane();
        initPanel();
        setLayout(new BorderLayout());
        add(headerWithTitle,BorderLayout.NORTH);
        add(retraitForm,BorderLayout.CENTER);
    }
    public RetraitOpDialog(Compte compte){
        this.compte = compte;
        initContainer();
        setResizable(false);
        setSize(500,300);
        setTitle("Retrait");
        setModal(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
