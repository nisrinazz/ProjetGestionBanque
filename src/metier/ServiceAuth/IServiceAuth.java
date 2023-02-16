package metier.ServiceAuth;


import presentation.modele.Compte;
import presentation.modele.Utilisateur;

import java.util.List;
import java.util.Map;

public interface IServiceAuth {

    public Utilisateur getSession() ;
    public void setSession(Utilisateur session);
    public void setSession();

    List<Compte> choisirCompte();
    Map<String,String> seConnecter(String login , String mdp);
    void seDeconnecter();

}
