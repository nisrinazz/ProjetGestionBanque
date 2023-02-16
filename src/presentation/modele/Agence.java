package presentation.modele;

import dao.dbFiles.FileBasePaths;

public class Agence {
//utilisé pou dbVolatile
    private Long                 idBanque;
    private String              nomBanque;
    private String              adresseBanque;
    private String              telBanque;
    private String              emailBanque;

    public Agence(String nom, String adresse, String tel, String mail)
                    {
                        nomBanque       = nom;
                        telBanque       = tel;
                        adresseBanque   = adresse;
                        emailBanque     = mail;
                        FileBasePaths.createFileBase();
                    }

    public Long             getIdBanque() {
        return idBanque;
    }
    public String           getNomBanque() {
        return nomBanque;
    }
    public String           getEmailBanque() {
        return emailBanque;
    }
    public String           getTelBanque() {
        return telBanque;
    }
    public String           getAdresseBanque() {
        return adresseBanque;
    }

    public void             setIdBanque(Long idBanque) {
        this.idBanque = idBanque;
    }
    public void             setNomBanque(String nomBanque) {
        this.nomBanque = nomBanque;
    }
    public void             setEmailBanque(String emailBanque) {
        this.emailBanque = emailBanque;
    }
    public void             setAdresseBanque(String adresseBanque) {
        this.adresseBanque = adresseBanque;
    }
    public void             setTelBanque(String telBanque) {
        this.telBanque = telBanque;
    }
    @Override
    public String toString() {
    	String chaine ;
    	chaine  ="--------------------------------------------------\n";
    	chaine += "| L'id de la banque             : "+ idBanque + "\n";
    	chaine += "| Le nom de la banque           :"+ nomBanque + "\n";
    	chaine += "| L'email de la banque          :"+ emailBanque + "\n";
    	chaine += "| L'adresse de la banque        : "+ adresseBanque + "\n";
    	chaine += "| Le numéro de tel de la banque :"+ telBanque + "\n"; 
    	chaine +="--------------------------------------------------\n";
    	return chaine;
    }
}
