package presentation.vue.palette;

import metier.clients.ServiceClient;
import presentation.modele.Compte;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InfoClientPanel extends JPanel {
    Compte compte ;
    StatElement soldePanel ;
    StatElement derniereOpPanel ;
    StatElement numComptePanel ;
    StatElement nbrComptePanel;

    Font fontTitle = new Font("Verdana",Font.BOLD,40);
    Font fontInfo = new Font("Verdana",Font.CENTER_BASELINE,30);

    ClassLoader cl = getClass().getClassLoader();

    public void initData(){
        ServiceClient serviceClient = new ServiceClient(compte);
        String solde = serviceClient.soldeCompte().toString() + " DH";
        String derniereOp = serviceClient.derniereOperation().getType().name();
        String numCompte = compte.getNumeroCompte();
        String nbrCompte = serviceClient.nbrCompteClient().toString();

        soldePanel.setInfo(solde);
        derniereOpPanel.setInfo(derniereOp);
        numComptePanel.setInfo(numCompte);
        nbrComptePanel.setInfo(nbrCompte);

    }

    public void initPanels(){
        soldePanel = new StatElement(new Color(125,219,211),30,new ImageIcon(cl.getResource("icons/money.png")),"Solde Actuel",new Color(103,175,170),new Color(103,175,170),fontTitle,fontInfo);
        derniereOpPanel = new StatElement(new Color(241,112,95),30,new ImageIcon(cl.getResource("icons/last.png")),"Dernière Opération",new Color(198,91,75),new Color(198,91,75),fontTitle,fontInfo);
        numComptePanel = new StatElement(new Color(243,189,106),30,new ImageIcon(cl.getResource("icons/numAccount.png")),"Numéro du compte",new Color(206,160,91),new Color(206,160,91),fontTitle,fontInfo);
        nbrComptePanel = new StatElement(new Color(207,146,225),30,new ImageIcon(cl.getResource("icons/nbrAccount.png")),"Nombre de compte",new Color(162,118,175),new Color(162,118,175),fontTitle,fontInfo);
        initData();
    }

    public InfoClientPanel(Compte compte){
        this.compte=compte;
        initPanels();
        GridLayout layout = new GridLayout(2,2);
        layout.setHgap(20);
        layout.setVgap(20);
        setLayout(layout);
        setBorder(new EmptyBorder(40,40,40,40));
        add(soldePanel);
        add(derniereOpPanel);
        add(numComptePanel);
        add(nbrComptePanel);

    }
}

