package metier.admin;

import presentation.modele.Banque;
import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.modele.TableauDeBord;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface IServiceAdmin {
    Map<String,String> ajouterClient(String nom, String prenom, String login, String mdp, String email, String cin, String tel, String sexe);
    public boolean supprimerClientParId(long id);

    public List<Client> listeClients();
    public Client chercherClientParId(long id);
    public Map<String,String> modifierClient(long id,String nom,String prenom,String login,String mdp,String tel,String cin,String email,String sexe);
    public List<Client> chercherClientParMotCle(String keyword);
    public List<Compte> chercherCompteParMotCle(String keyword);
    public Compte chercherCompteParId(String numCompte);
    TableauDeBord   calculerEtAfficherStatistiques();

    public List<Compte> listeComptes();

    public Map<String,String> nouveauCompte(Long id,String soldeStr);

    public boolean supprimerCompte(String id);
    List<Client>    trierClientParNom();
    List<Client>    trierClientParCin();
    List<Client>    trierClientParEmail();
    List<Client>    trierClientParSoldeCompte();
    List<Compte>    trierComptesParSolde();
    List<Compte>    trierComptesParDateDeCreation();
    List<Compte>    trierComptesParNomPropriétaire();

}
