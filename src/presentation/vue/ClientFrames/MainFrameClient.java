package presentation.vue.ClientFrames;
import presentation.modele.Compte;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrameClient extends JFrame {
    Dimension screen = getToolkit().getScreenSize();
    Compte compte;
   Container container ;
   LogsPanel logsInfoPanel ;
   StatClientPanel statClientPanel ;

   public void initPanels(){
       logsInfoPanel=new LogsPanel(compte);
       statClientPanel = new StatClientPanel(compte);
   }
   public void initContainer(){
       initPanels();
       container = getContentPane();
       container.setLayout(new CardLayout());
       container.add(logsInfoPanel,"Logs & Opérations");
       container.add(statClientPanel,"Accueil");
       CardLayout cl = (CardLayout)(container.getLayout());
       cl.show(container,"Accueil");
       initActions();
   }
    public void initActions(){
        CardLayout cl = (CardLayout)(container.getLayout());

        logsInfoPanel.getSideMenuPanel().getLinks().forEach((name,link)->link.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(name.equals("Accueil"))
                    statClientPanel.getInfoClientPanel().initData();
                cl.show(container,name);
            }
        }));
        statClientPanel.getSideMenuPanel().getLinks().forEach((name,link)->link.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cl.show(container,name);
            }
        }));

    }
   public MainFrameClient(Compte compte){
       this.compte=compte;
       initContainer();
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setSize(screen.width,screen.height-20);
       setVisible(true);
   }

}
