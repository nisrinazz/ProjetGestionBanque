package metier.admin;

import dao.IAgenceDAO;
import dao.IClientDAO;
import dao.ICompteDAO;
import dao.ILogsDAO;
import dao.dbFiles.AgenceDAOFile;
import dao.dbFiles.ClientDAOFile;
import dao.dbFiles.CompteDAOFile;
import dao.dbFiles.LogDAOFile;

import metier.Verifiable;
import metier.forms.AccountFormValidator;
import metier.forms.ClientFormValidator;
import presentation.modele.*;

import java.time.LocalDate;
import java.util.*;

public class ServiceAdmin implements IServiceAdmin, Verifiable {

	private IClientDAO clientDAO;
	private ICompteDAO compteDAO;

	private IAgenceDAO agenceDAO;
	private ILogsDAO logsDAO;


	public ServiceAdmin() {
		clientDAO = new ClientDAOFile();
		compteDAO = new CompteDAOFile();
		logsDAO = new LogDAOFile();
		agenceDAO = new AgenceDAOFile() ;
	}

	@Override
	public Map<String, String> ajouterClient(String nom, String prenom, String login, String mdp, String email, String cin, String tel, String sexe) {
		ClientFormValidator clientFormValidator = new ClientFormValidator();
		Client c = clientFormValidator.validerCreation(nom, prenom, login, mdp, email, cin, tel, sexe);
		if (c != null) {
			clientDAO.save(c);
		}
		return clientFormValidator.Errors();
	}

	@Override
	public boolean supprimerClientParId(long id) {
		return clientDAO.delete(id);
	}

	public Compte chercherCompteParId(String numCompte) {
		return compteDAO.findById(numCompte);
	}

	@Override
	public List<Client> listeClients() {
		return clientDAO.findAll();
	}

	@Override
	public Client chercherClientParId(long id) {
		return clientDAO.findById(id);
	}

	@Override
	public Map<String, String> modifierClient(long id, String nom, String prenom, String login, String mdp, String tel, String cin, String email, String sexe) {
		Client client = chercherClientParId(id);
		ClientFormValidator clientFormValidator = new ClientFormValidator();
		if (nom.trim().length() != 0 && !client.getNom().equals(nom)) {
			client.setNom(nom);
			clientFormValidator.validerNom(nom);
		}
		if (prenom.trim().length() != 0 && !client.getPrenom().equals(prenom)) {
			client.setPrenom(prenom);
			clientFormValidator.validerPrenom(prenom);
		}
		if (login.trim().length() != 0 && !client.getLogin().equals(login)) {
			client.setLogin(login);
			clientFormValidator.validerLogin(login);
		}
		if (mdp.trim().length() != 0 && !client.getMotDePasse().equals(mdp)) {
			client.setMotDePasse(mdp);
			clientFormValidator.validerMdp(mdp);
		}
		if (tel.trim().length() != 0 && !client.getTel().equals(tel)) {
			client.setTel(tel);
			clientFormValidator.validerTel(tel);
		}
		if (cin.trim().length() != 0 && !client.getCin().equals(cin)) {
			client.setCin(cin);
			clientFormValidator.validerCin(cin);
		}
		if (email.trim().length() != 0 && !client.getEmail().equals(email)) {
			client.setEmail(email);
			clientFormValidator.validerEmail(email);
		}
		if (!client.getSexe().getLibelle().equals(sexe)) {
			if (sexe.equals("FEMME")) client.setSexe(Sexe.FEMME);
			else if (sexe.equals("HOMME")) client.setSexe(Sexe.HOMME);
		}
		Map<String, String> errorList = clientFormValidator.Errors();
		if (errorList.isEmpty()) clientDAO.update(client);
		return errorList;
	}

	public List<Client> chercherClientParMotCle(String keyword) {
		if (keyword != null && keyword.trim().length() != 0)
			return clientDAO.findByKeywordLike(keyword);
		else return listeClients();
	}

	public List<Compte> chercherCompteParMotCle(String keyword) {
		if (keyword != null && keyword.trim().length() != 0)
			return compteDAO.findByKeywordLike(keyword);
		else return listeComptes();
	}

	public List<Compte> listeComptes() {
		return compteDAO.findAll();
	}

	@Override
	public Map<String, String> nouveauCompte(Long id, String soldeStr) {
		AccountFormValidator accountFormValidator = new AccountFormValidator();
		Compte compte = accountFormValidator.validerCreation(id, soldeStr);
		if (compte != null) {
			Compte compteAvecId = compteDAO.save(compte);
			String msg = "Compte " + compte.getNumeroCompte() + " nouvellement cr??e avec un solde initiale de " + compte.getSolde() + " DH";
			logsDAO.save(compteAvecId, TypeLog.CREATION, msg);
		}
		return accountFormValidator.Errors();
	}

	public boolean supprimerCompte(String id) {
		return compteDAO.delete(id);
	}

	private HashMap<String, Compte> maxSoldeMinSolde() {
		HashMap<String, Compte> map = new HashMap<>();
		Compte max = null, min = null;
		if (!compteDAO.findAll().isEmpty()) {
			max = compteDAO.findAll().get(0);
			min = compteDAO.findAll().get(0);
		}
		for (Compte c : compteDAO.findAll()) {
			if (c.getSolde() >= max.getSolde()) max = c;
			if (c.getSolde() <= min.getSolde()) min = c;
		}
		map.put("min", min);
		map.put("max", max);
		return map;
	}

	private HashMap<Sexe, Integer> nbrCliFemmeNbrCliHomme() {
		HashMap<Sexe, Integer> map = new HashMap<>();
		int nbrF = 0;
		int nbrH = 0;
		for (Client client : clientDAO.findAll()) {
			if (client.getSexe().equals(Sexe.FEMME)) nbrF++;
			if (client.getSexe().equals(Sexe.HOMME)) nbrH++;
		}
		map.put(Sexe.FEMME, nbrF);
		map.put(Sexe.HOMME, nbrH);
		return map;
	}

	private Double totalEntree() {
		Double somme = 0.0;
		for (Compte compte : compteDAO.findAll()) {
			somme += compte.getSolde();
		}
		return somme;
	}

	private Integer nbrOpAuj() {
		int i = 0;
		for (Compte compte : compteDAO.findAll()) {
			for (Log log : logsDAO.findAll(compte)) {
				if (log.getDate().compareTo(LocalDate.now())==0) i++;
			}
		}
		return i;
	}

	@Override
	public TableauDeBord calculerEtAfficherStatistiques() {
		TableauDeBord tabDeBord = new TableauDeBord();
		tabDeBord.setNombreTotaleClient((long) clientDAO.findAll().size());
		tabDeBord.setTotalClientsFemme((long) nbrCliFemmeNbrCliHomme().get(Sexe.FEMME));
		tabDeBord.setTotaleClientsHomme((long) nbrCliFemmeNbrCliHomme().get(Sexe.HOMME));
		tabDeBord.setNombreTotaleCompte((long) compteDAO.findAll().size());
		tabDeBord.setMaxSolde(maxSoldeMinSolde().get("max")!=null ? maxSoldeMinSolde().get("max").getSolde() : 0.0);
		tabDeBord.setMinSolde(maxSoldeMinSolde().get("min")!= null ? maxSoldeMinSolde().get("min").getSolde() : 0.0 );
		tabDeBord.setNbrOpAuj(nbrOpAuj());
		tabDeBord.setTotalEntree(totalEntree());
		tabDeBord.setNomClientLePlusRiche(maxSoldeMinSolde().get("max")!=null ? maxSoldeMinSolde().get("max").getPropri??taire().getNomComplet() : null);
		return tabDeBord;
	}
	@Override
	public List<Client> trierClientParNom() {
		List<Client> list = clientDAO.findAll();
		 Collections.sort(list, (c1 , c2) -> c1.getNom().compareToIgnoreCase(c2.getNom()));
		return list ;
	}

	@Override
	public List<Client> trierParSexe() {
		List<Client> list = clientDAO.findAll();
		Collections.sort(list, (c1 , c2) -> c1.getSexe().getLibelle().compareToIgnoreCase(c2.getSexe().getLibelle()));
		return list ;
	}

	@Override
	public List<Agence> listeAgences() {
		return agenceDAO.findAll();
	}

	@Override
	public List<Agence> chercherAgenceParMotCle(String keyword) {
		if (keyword != null && keyword.trim().length() != 0)
			return agenceDAO.findByKeywordLike(keyword);
		else return listeAgences();
	}

	@Override
	public List<Client> trierClientParPrenom() {
		List<Client> list = clientDAO.findAll();
		Collections.sort(list, (c1 , c2) -> c1.getPrenom().compareToIgnoreCase(c2.getPrenom()));
		return list ;
	}

	@Override
	public List<Client> trierClientParCin() {
		List<Client> list = clientDAO.findAll();
		 Collections.sort(list , (c1 , c2) -> c1.getCin().compareToIgnoreCase(c2.getCin()));
		    return list;
	}

	@Override
	public List<Client> trierClientParEmail() {
		List<Client> list = clientDAO.findAll();
		 Collections.sort(list , (c1 , c2) -> c1.getEmail().compareToIgnoreCase(c2.getEmail()));
		    return list;
	}
    
	private int maxSolde(Client c1 , Client c2) {
		Double maxc1=0.0 , maxc2=0.0 ;
		for(Compte compte : compteDAO.findByOwner(c1) ) {
			if(compte.getSolde() >= maxc1 ) maxc1 = compte.getSolde();
		}
		for(Compte compte : compteDAO.findByOwner(c2)) {
			if(compte.getSolde() >= maxc2 ) maxc2 = compte.getSolde();
		}
		return maxc1.compareTo(maxc2);
	}
	@Override
	public List<Client> trierClientParSoldeCompte() {
		List<Client> list = clientDAO.findAll();
		 Collections.sort(list , this::maxSolde);
		    return list;
	}
	

	@Override
	public List<Compte> trierComptesParSolde() {
		List<Compte> list = compteDAO.findAll();
		Collections.sort(list , (c1 , c2) -> c1.getSolde().compareTo(c2.getSolde()));
		return list;
	}

	@Override
	public List<Compte> trierComptesParDateDeCreation() {
		List<Compte> list = compteDAO.findAll();
		Collections.sort(list,(c1 , c2) -> c1.getDateCreation().compareTo(c2.getDateCreation()));
		return list ; 
	}

	@Override
	public List<Compte> trierComptesParNomPropri??taire() {
		List<Compte> list = compteDAO.findAll();
		Collections.sort(list,(c1 , c2) -> c1.getPropri??taire().getNomComplet().compareToIgnoreCase(c2.getPropri??taire().getNomComplet()));
		return list ; 
	}
    





}
