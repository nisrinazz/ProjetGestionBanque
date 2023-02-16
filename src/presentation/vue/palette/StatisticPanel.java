package presentation.vue.palette;

import metier.admin.IServiceAdmin;
import metier.admin.ServiceAdmin;
import presentation.modele.TableauDeBord;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StatisticPanel extends JPanel {
   ClassLoader cl = getClass().getClassLoader();
  private  StatElement nbrClientPanel;
   private StatElement nbrComptePanel;
   private StatElement nbrFemmePanel;
  private   StatElement nbrHommePanel;
  private   StatElement clientPlusRichePanel;
  private   StatElement maxSoldePanel;
  private   StatElement minSoldePanel;
  private   StatElement totalEntreePanel;
   private StatElement opAujPanel;

   IServiceAdmin serviceAdmin ;

   Font fontTitle = new Font("Verdana",Font.BOLD,25);
   Font fontInfo = new Font("Verdana",Font.CENTER_BASELINE,21);

   public void initStatData(){
       TableauDeBord tableauDeBord = serviceAdmin.calculerEtAfficherStatistiques();
       String nbrClient = tableauDeBord.getNombreTotaleClient().toString();
       String nbrCompte = tableauDeBord.getNombreTotaleCompte().toString();
       String nbrFemme = tableauDeBord.getTotalClientsFemme().toString();
       String nbrHomme = tableauDeBord.getTotaleClientsHomme().toString();
       String cliRiche = tableauDeBord.getNomClientLePlusRiche()!=null ? tableauDeBord.getNomClientLePlusRiche() : "Aucun";
       String maxSolde = tableauDeBord.getMaxSolde().toString() + " DH";
       String minSolde = tableauDeBord.getMinSolde().toString() + " DH";
       String totalEntree = tableauDeBord.getTotalEntree().toString() + " DH";
       String operationAuj = tableauDeBord.getNbrOpAuj().toString();
       nbrClientPanel.setInfo(nbrClient);
       nbrComptePanel.setInfo(nbrCompte);
       nbrFemmePanel.setInfo(nbrFemme);
       nbrHommePanel.setInfo(nbrHomme);
       clientPlusRichePanel.setInfo(cliRiche);
       maxSoldePanel.setInfo(maxSolde);
       minSoldePanel.setInfo(minSolde);
       totalEntreePanel.setInfo(totalEntree);
       opAujPanel.setInfo(operationAuj);
   }

   public void initPanels(){
       nbrClientPanel = new StatElement(new Color(255,224,150),30,new ImageIcon(cl.getResource("icons/rating.png")),"Total Client",new Color(191,166,110),new Color(191,166,110),fontTitle,fontInfo);
       nbrComptePanel = new StatElement(new Color(190,216,215),30,new ImageIcon(cl.getResource("icons/bank-account.png")),"Total Compte",new Color(139,158,157),new Color(139,158,157),fontTitle,fontInfo);
       nbrFemmePanel = new StatElement(new Color(177,156,216),30,new ImageIcon(cl.getResource("icons/woman.png")),"Total Femme",new Color(132,116,156),new Color(132,116,156),fontTitle,fontInfo);
       nbrHommePanel = new StatElement(new Color(136,158,206),30,new ImageIcon(cl.getResource("icons/man.png")),"Total Homme",new Color(109,122,152),new Color(109,122,152),fontTitle,fontInfo);
       clientPlusRichePanel = new StatElement(new Color(77,148,208),30,new ImageIcon(cl.getResource("icons/rich-man.png")),"Client plus riche",new Color(58,109,148),new Color(58,109,148),fontTitle,fontInfo);
       maxSoldePanel = new StatElement(new Color(254,200,173),30,new ImageIcon(cl.getResource("icons/growth.png")),"Solde Maximal",new Color(181,147,129),new Color(181,147,129),fontTitle,fontInfo);
       minSoldePanel = new StatElement(new Color(171,210,172),30,new ImageIcon(cl.getResource("icons/decrease.png")),"Solde Minimal",new Color(132,159,133),new Color(132,159,133),fontTitle,fontInfo);
       totalEntreePanel = new StatElement(new Color(206,240,161),30,new ImageIcon(cl.getResource("icons/salary.png")),"Total Entrée",new Color(163,186,132),new Color(163,186,132),fontTitle,fontInfo);
       opAujPanel = new StatElement(new Color(200,86,107),30,new ImageIcon(cl.getResource("icons/credit-card.png")),"Opération d'aujourd'hui",new Color(138,64,77),new Color(138,64,77),fontTitle,fontInfo);
       initStatData();
   }

   public StatisticPanel(IServiceAdmin serviceAdmin){
       this.serviceAdmin = serviceAdmin;
       initPanels();
       GridLayout layout = new GridLayout(3,3);
       layout.setHgap(20);
       layout.setVgap(20);
       setLayout(layout);
       setBorder(new EmptyBorder(40,40,40,40));
       add(nbrClientPanel);
       add(nbrComptePanel);
       add(nbrFemmePanel);
       add(nbrHommePanel);
       add(clientPlusRichePanel);
       add(maxSoldePanel);
       add(minSoldePanel);
       add(totalEntreePanel);
       add(opAujPanel);

   }





}
