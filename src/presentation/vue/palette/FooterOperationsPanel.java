package presentation.vue.palette;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FooterOperationsPanel extends JPanel {
   private ClassLoader cl = getClass().getClassLoader();
   private OperationPanel retrait;
   private OperationPanel virement ;
  private  OperationPanel versement ;

    public OperationPanel getRetrait() {
        return retrait;
    }

    public OperationPanel getVersement() {
        return versement;
    }

    public OperationPanel getVirement() {
        return virement;
    }

    public void initPanels(){
        retrait = new OperationPanel(new Color(155,207,182), "Retrait",new Color(101,134,118),new Font("Verdana",Font.BOLD,17),new ImageIcon(cl.getResource("icons/flecheRetrait.png")));
        virement = new OperationPanel(new Color(243,231,190), "Virement",new Color(178,169,138),new Font("Verdana",Font.BOLD,17),new ImageIcon(cl.getResource("icons/flecheVirement.png")));
        versement = new OperationPanel(new Color(241,141,150), "Versement",new Color(161,106,113),new Font("Verdana",Font.BOLD,17),new ImageIcon(cl.getResource("icons/flecheVersement.png")));


    }

    public FooterOperationsPanel(Color bgColor){
        initPanels();
        setBackground(bgColor);
        setLayout(new FlowLayout(FlowLayout.RIGHT,30,0));
        add(retrait);
        add(virement);
        add(versement);
        setBorder(new EmptyBorder(15,15,20,3));
    }
}
