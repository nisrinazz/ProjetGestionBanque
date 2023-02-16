package presentation.vue.ClientFrames;
import metier.ServiceAuth.IServiceAuth;
import metier.admin.IServiceAdmin;
import metier.clients.IServiceClient;
import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.vue.AdminFrames.UpdateClientDialog;
import presentation.vue.LoginFrames.ChooseAccountFrame;
import presentation.vue.LoginFrames.LoginFrame;
import presentation.vue.palette.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrameClient extends JFrame {
  private   Dimension screen = getToolkit().getScreenSize();
   private ClassLoader cl = getClass().getClassLoader();
  private Container container ;
   private LogsPanel logsOpPanel;
  private InfoClientPanel infoClientPanel;

 private    JPanel  cardPanel ;
    private HeaderWithoutLinks headerClient;
    private SideMenuPanel sideMenuPanel;
  private IServiceAuth serviceAuth ;
  private IServiceClient serviceClient ;
  private IServiceAdmin serviceAdmin;


    public void changePanel(String name){
        CardLayout cl = (CardLayout)(cardPanel.getLayout());
        cl.show(cardPanel,name);
    }

    public void initInfo(){
        headerClient.getSectionInformation().setNameUser(serviceClient.getCompte().getPropriétaire().getNomComplet());
        headerClient.getSectionInformation().setRoleUser(serviceClient.getCompte().getPropriétaire().getRole());
    }

   public void initPanels(){
       headerClient =new HeaderWithoutLinks(Color.BLACK,new Font("Verdana", Font.CENTER_BASELINE,15),new Font("Verdana",Font.ITALIC,12),Color.WHITE,Color.WHITE,new ImageIcon(cl.getResource("icons/menu1.png")),new ImageIcon(cl.getResource("icons/infoDropDown.png")));
       sideMenuPanel = new SideMenuPanel(Color.BLACK,Color.WHITE,new Font("Verdana",Font.BOLD,17),"Accueil","Logs & Opérations","Changer le compte","Modifier le profil");
       initInfo();
       logsOpPanel =new LogsPanel(serviceClient);
       infoClientPanel = new InfoClientPanel(serviceClient);
       cardPanel = new JPanel();
   }
   public void initContainer(){
       initPanels();
       container = getContentPane();
       container.setLayout(new BorderLayout());
       container.add(headerClient,BorderLayout.NORTH);
       container.add(sideMenuPanel,BorderLayout.WEST);
       cardPanel.setLayout(new CardLayout());
       cardPanel.add(logsOpPanel,"Logs & Opérations");
       cardPanel.add(infoClientPanel,"Accueil");
       container.add(cardPanel,BorderLayout.CENTER);
       changePanel("Accueil");
       initActions();
   }
    public void initActions(){
        headerClient.getSideMenuBtn().addActionListener(e->{
            if(sideMenuPanel.isVisible()) sideMenuPanel.setVisible(false);
            else sideMenuPanel.setVisible(true);
        });
        sideMenuPanel.getLinks().forEach((name,link)->link.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(name.equals("Accueil"))
                    infoClientPanel.initData();
                else if(name.equals("Changer le compte"))
                {     dispose();
                     new ChooseAccountFrame(serviceAuth,serviceAdmin);
                }
                else if(name.equals("Modifier le profil"))
                {
                    new UpdateClientDialog((Client)serviceAuth.getSession(),serviceAdmin);
                    initInfo();
                    serviceAuth.setSession();
                }
                changePanel(name);
            }
        }));

        headerClient.getSignOut().addActionListener(click->{
            dispose();
            serviceAuth.seDeconnecter();
            new LoginFrame(serviceAuth);
        });

    }
   public MainFrameClient(IServiceAuth serviceAuth,IServiceAdmin serviceAdmin , IServiceClient serviceClient){
       this.serviceAdmin = serviceAdmin ;
       this.serviceClient = serviceClient ;
       this.serviceAuth = serviceAuth;
       initContainer();
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setSize(screen.width,screen.height-20);
       setVisible(true);
   }

}
