package presentation.vue.ClientFrames;

import metier.Verifiable;
import metier.clients.IServiceClient;
import presentation.vue.palette.HeaderWithTitle;
import presentation.vue.palette.OneFieldForm;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class RetraitOpDialog extends JDialog implements Verifiable{

   private IServiceClient serviceClient ;
   private ClassLoader cl = getClass().getClassLoader();
   private HeaderWithTitle headerWithTitle ;
   private OneFieldForm retraitForm;
   private Container container ;

    public void initPanel(){
        headerWithTitle = new HeaderWithTitle(Color.WHITE,Color.BLACK,new ImageIcon(cl.getResource("icons/operation.png")),"Retrait",new Font("Verdana",Font.BOLD,24));
        retraitForm = new OneFieldForm(Color.BLACK,"Montant",Color.WHITE,new Font("Verdana",Font.PLAIN,14),Color.WHITE,"Valider",Color.WHITE,Color.BLACK,new Font("Verdana",Font.PLAIN,14));
        initAction();
    }

    public void initAction(){
        retraitForm.getSubmit().addActionListener(click->{
                retraitForm.getError().setVisible(false);
                String montant = retraitForm.getField().getText();
                Map<String, String> errors = serviceClient.retrait(montant);
                if (errors.isEmpty()) {
                    dispose();
                } else {
                    for (String error : errors.keySet()) {
                        retraitForm.getError().setVisible(true);
                        retraitForm.setError(errors.get(error));
                    }
                }}
            );
        }

    public void initContainer(){
        container = getContentPane();
        initPanel();
        container.setLayout(new BorderLayout());
        container.add(headerWithTitle,BorderLayout.NORTH);
        container.add(retraitForm,BorderLayout.CENTER);
    }
    public RetraitOpDialog(IServiceClient serviceClient){
        this.serviceClient = serviceClient ;
        initContainer();
        setResizable(false);
        setSize(500,300);
        setTitle("Retrait");
        setModal(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
