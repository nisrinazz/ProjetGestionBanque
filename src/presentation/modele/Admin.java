package presentation.modele;

public class Admin  extends Utilisateur{

    private static  Admin ADMIN = new Admin();

    private Admin(){

        login       = "admin";
        motDePasse  = "1234";
        role        = "Admin";
        nom         =  "Admin";
        prenom      = "NISRINE";

    }


    public static Admin getInstance(){

        return ADMIN;
    }
    
    @Override
    public String toString() {
    	String chaine ; 
    	chaine  = "---------------------------------------------\n";
    	chaine += "| L'identifiant de l'admin :" + id + "\n" ;
    	chaine += "| Le nom complet de l'admin :" + getNomComplet() + "\n" ;
    	chaine += "| Le login de l'admin : " + login + "\n";
    	chaine += "| Le mot de passe de l'admin :"+ motDePasse + "\n" ; 
    	chaine += "---------------------------------------------\n";
    	return chaine ;
    }

}
