package presentation.vue.AdminFrames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrameAdmin extends JFrame {
    Dimension screen = getToolkit().getScreenSize();
    ClientCrudPanel clientCrudPanel ;
    AccountCrudPanel accountCrudPanel;
    MainStatisticPanel statisticPanel ;

    Container container ;

    public void initPanels(){
        clientCrudPanel=new ClientCrudPanel();
        accountCrudPanel=new AccountCrudPanel();
        statisticPanel=new MainStatisticPanel();
    }
    public void initActions(){
        CardLayout cl = (CardLayout)(container.getLayout());

        clientCrudPanel.headerAdmin.getLinks().forEach((name,link)->link.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(name.equals("Statistiques"))
                    statisticPanel.getStatisticPanel().initStatData();
                cl.show(container,name);
            }
        }));
        accountCrudPanel.headerAdmin.getLinks().forEach((name,link)->link.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(name.equals("Statistiques"))
                    statisticPanel.getStatisticPanel().initStatData();
                cl.show(container,name);
            }
        }));
        statisticPanel.getHeaderAdmin().getLinks().forEach((name,link)->link.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cl.show(container,name);
            }
        }));

    }

    public void initContainer(){
        initPanels();
        container=getContentPane();
        container.setLayout(new CardLayout());
        container.add(clientCrudPanel,"Client");
        container.add(accountCrudPanel,"Compte");
        container.add(statisticPanel,"Statistiques");
        CardLayout cl = (CardLayout)(container.getLayout());
        cl.show(container,"Statistiques");
        initActions();
    }

    public MainFrameAdmin(){
        initContainer();
        setSize(screen.width,screen.height-20);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrameAdmin();
    }
}
