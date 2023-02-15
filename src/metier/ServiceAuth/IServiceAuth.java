package metier.ServiceAuth;


import presentation.modele.Compte;

import java.util.List;
import java.util.Map;

public interface IServiceAuth {

    List<Compte> choisirCompte();
    Map<String,String> seConnecter(String login , String mdp);

    void seDeconnecter();

}
