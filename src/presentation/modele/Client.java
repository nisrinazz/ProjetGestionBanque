package presentation.modele;

import java.util.ArrayList;
import java.util.List;


public class Client extends Utilisateur implements Comparable<Client>{

    private String email, cin, tel;
    private Sexe sexe;

    private List<Compte>  comptesClient = new ArrayList<>();;

    public String       getCin() {
        return cin;
    }
    public String       getTel() {
        return tel;
    }
    public String       getEmail() {
        return email;
    }
    public Sexe getSexe() {
        return sexe;
    }

    public List<Compte> getComptesClient() {
        return comptesClient;
    }

    public void         setComptesClient(List<Compte> comptesClient) {
        this.comptesClient = comptesClient;
    }
    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }
    public void         setEmail(String email) {
        this.email = email;
    }
    public void         setTel(String tel) {
        this.tel = tel;
    }
    public void         setCin(String cin) {
        this.cin = cin;
    }

    public Client(){

    }

    public Client(String login, String pass){
        super(login, pass, "Client");
    }

    public Client(String login, String pass, String n, String p){
        super(login, pass, "Client");
        setNom(n);
        setPrenom(p);
    }
    public Client(String login, String pass, String n, String p, String mail, String cin, String tel, Sexe sexe){
        super(login, pass, "Client");
        setNom(n);
        setPrenom(p);
        setTel(tel);
        setEmail(mail);
        setCin(cin);
        setSexe(sexe);
    }



    @Override
    public String toString() {

        String      clientStr  = "------------------------------------------------------\n";
                    clientStr += "| Identifiant du Client     : "   + this.id        + "\n";
                    clientStr += "| Nom Complet               : "   + this.getNomComplet() + "\n" ;
                    clientStr += "| Adresse email             : "   + this.email     + "\n" ;
                    clientStr += "| Numéro téléphone          : "   + this.tel       + "\n" ;
                    clientStr += "| Numéro de CIN             : "   + this.cin       + "\n" ;
                    clientStr += "------------------------------------------------------\n";

        return clientStr;
    }
   
    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof Client) {
    		Client client = (Client)obj;
    		if(this.id == client.id) return true ;
    	    return false ;
    	}
    	return false ;
    }
	@Override
	public int compareTo(Client c) {
		if(getNomComplet().compareTo(c.getNomComplet())==0) 
		{
			if(email.compareTo(c.email) == 0)
			{   
			return cin.compareTo(c.cin);
			}
			else return email.compareTo(c.email);
		}
		else return getNomComplet().compareTo(c.getNomComplet());
		
	}

}
