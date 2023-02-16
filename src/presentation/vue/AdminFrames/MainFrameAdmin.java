package presentation.vue.AdminFrames;

import metier.ServiceAuth.IServiceAuth;
import metier.admin.IServiceAdmin;
import presentation.modele.Admin;
import presentation.vue.LoginFrames.LoginFrame;
import presentation.vue.palette.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrameAdmin extends JFrame {
   private Dimension screen = getToolkit().getScreenSize();
   private ClassLoader cl = getClass().getClassLoader();
   private ClientCrudPanel clientCrudPanel ;
   private AccountCrudPanel accountCrudPanel;
   private HeaderWithLinks headerAdmin ;
   private StatisticPanel statisticPanel ;
   private AgenceInfoPanel agenceInfoPanel ;
   private JPanel  cardPanel ;

    private IServiceAuth serviceAuth ;

   private IServiceAdmin serviceAdmin;

   private Container container ;

    public void initPanels(){
        headerAdmin =new HeaderWithLinks(Color.BLACK,new Font("Verdana", Font.CENTER_BASELINE,15),new Font("Verdana",Font.ITALIC,12),Color.WHITE,Color.WHITE,new ImageIcon(cl.getResource("icons/infoDropDown.png")),new Font("Verdana",Font.BOLD,17), Color.WHITE,"Statistiques","Clients","Agences","Comptes");
        headerAdmin.getSectionInformation().setNameUser(Admin.getInstance().getNomComplet());
        headerAdmin.getSectionInformation().setRoleUser(Admin.getInstance().getRole());
        agenceInfoPanel = new AgenceInfoPanel(serviceAdmin);
        clientCrudPanel=new ClientCrudPanel(serviceAdmin);
        accountCrudPanel=new AccountCrudPanel(serviceAdmin);
        statisticPanel=new StatisticPanel(serviceAdmin);
        cardPanel = new JPanel();
    }
    public void initActions(){
       headerAdmin.getLinks().forEach((name,link)->link.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(name.equals("Statistiques"))
                   statisticPanel.initStatData();
                if(name.equals("Comptes"))
                    accountCrudPanel.getTablePanelAccount().getTableModel().initAccountsData(serviceAdmin.listeComptes());
               changePanel(name);
            }
        }));
        headerAdmin.getSignOut().addActionListener(click->{
              dispose();
              serviceAuth.seDeconnecter();
              new LoginFrame(serviceAuth);
        });

    }

    public void changePanel(String name){
        CardLayout cl = (CardLayout)(cardPanel.getLayout());
        cl.show(cardPanel,name);
    }

    public void initContainer(){
        initPanels();
        container=getContentPane();
        container.setLayout(new BorderLayout());
        container.add(headerAdmin,BorderLayout.NORTH);
        cardPanel.setLayout(new CardLayout());
        cardPanel.add(clientCrudPanel,"Clients");
        cardPanel.add(accountCrudPanel,"Comptes");
        cardPanel.add(agenceInfoPanel,"Agences");
        cardPanel.add(statisticPanel,"Statistiques");
        container.add(cardPanel,BorderLayout.CENTER);
        changePanel("Statistiques");
        initActions();
    }

    public MainFrameAdmin(IServiceAuth serviceAuth, IServiceAdmin serviceAdmin){
        this.serviceAdmin = serviceAdmin;
        this.serviceAuth = serviceAuth;
        initContainer();
        setSize(screen.width,screen.height-20);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
