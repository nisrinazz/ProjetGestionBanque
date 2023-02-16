package metier.admin;

import presentation.modele.Agence;
import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.modele.TableauDeBord;

import java.util.List;
import java.util.Map;

public interface IServiceAdmin {
    Map<String,String> ajouterClient(String nom, String prenom, String login, String mdp, String email, String cin, String tel, String sexe);
     boolean supprimerClientParId(long id);

     List<Client> listeClients();
     Client chercherClientParId(long id);
     Map<String,String> modifierClient(long id,String nom,String prenom,String login,String mdp,String tel,String cin,String email,String sexe);
     List<Client> chercherClientParMotCle(String keyword);
     List<Compte> chercherCompteParMotCle(String keyword);
     Compte chercherCompteParId(String numCompte);
    TableauDeBord   calculerEtAfficherStatistiques();

     List<Compte> listeComptes();

     Map<String,String> nouveauCompte(Long id,String soldeStr);

     boolean supprimerCompte(String id);
    List<Client>    trierClientParNom();
    List<Client>    trierClientParCin();
    List<Client>    trierClientParEmail();
    List<Client>    trierClientParSoldeCompte();

     List<Client> trierParSexe();

     List<Agence> listeAgences();

     List<Agence> chercherAgenceParMotCle(String keyword);

     List<Client> trierClientParPrenom();
    List<Compte>    trierComptesParSolde();
    List<Compte>    trierComptesParDateDeCreation();
    List<Compte>    trierComptesParNomPropriétaire();

}
