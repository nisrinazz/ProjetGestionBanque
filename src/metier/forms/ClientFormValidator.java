package metier.forms;

import dao.IClientDAO;
import dao.dbFiles.ClientDAOFile;
import metier.Verifiable;
import presentation.modele.Client;
import presentation.modele.Sexe;

import java.util.HashMap;
import java.util.Map;

public class ClientFormValidator implements Verifiable {
    private static final String FIELD_NOM="nom",FIELD_PRENOM="prenom",FIELD_LOGIN="login",FIELD_PASS="mot de passe",
            FIELD_TEL="tel",FIELD_CIN="CIN",FIELD_EMAIL="email",FIELD_SEXE="sexe";


    private Map<String , String> errors = new HashMap<>();
    private String resultMsg;
    private IClientDAO clientDAO ;

   public ClientFormValidator(){
           this.clientDAO = new ClientDAOFile();
   }

    public Map<String, String> Errors() {
        return errors;
    }
    public void setError(String fieldName, String errorMsg) {
        Errors().put(fieldName, errorMsg);
    }

    public String getResultMsg() {
        return resultMsg;
    }
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
    public IClientDAO getClientDAO() {
        return clientDAO;
    }
    public void setClientDAO(IClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    private boolean loginUnique(String login){
       for(Client c : clientDAO.findAll()){
           if(c.getLogin().equals(login)) return false ;
       }
       return true ;
    }

    private boolean emailUnique(String email){
        for(Client c : clientDAO.findAll()){
            if(c.getEmail().equals(email)) return false ;
        }
        return true ;
    }
    private boolean telUnique(String tel){
        for(Client c : clientDAO.findAll()){
            if(c.getTel().equals(tel)) return false ;
        }
        return true ;
    }

    private boolean cinUnique(String cin){
        for(Client c : clientDAO.findAll()){
            if(c.getCin().equals(cin)) return false ;
        }
        return true ;
    }


    public void verifierNom(String nom) throws FormException{
       if(nom != null && nom.trim().length()!=0){
           if(nom.trim().length()>10){
               throw new FormException("Nom doit avoir au maximum 10 caractère");
           }
       }
       else throw new FormException("Le champ nom est obligatoire");
    }

    public void verifierPrenom(String prenom) throws FormException{
        if(prenom != null && prenom.trim().length()!=0){
            if(prenom.trim().length()>10){
                throw new FormException("Prenom doit avoir au maximum 10 caractère");
            }
        }
       else throw new FormException("Le champ prenom est obligatoire");
    }

    public void verifierLogin(String login) throws FormException{
        if(login != null && login.trim().length()!=0){
            if(login.trim().length()<4 || login.trim().length()>10){
                throw new FormException("Login doit avoir entre 4 et 10 caractère");

            }
            else {
                if(!loginUnique(login))
                    throw new FormException("Login entré déjà existant");

            }
        }
       else throw new FormException("Le champ login est obligatoire");
    }

    public void verifierEmail(String email) throws FormException{
        if(email != null && email.trim().length()!=0){
            if(email.trim().length()<10 || email.trim().length()>30 ){
                throw new FormException("Email doit avoir au entre 10 et 30 caractère");

            }
            else {
                if(!emailUnique(email))
                    throw new FormException("Email entré déjà existant");

            }
        }
       else throw new FormException("Le champ email est obligatoire");
    }

    public void verifierCin(String cin) throws FormException{
        if(cin != null && cin.trim().length()!=0){
            if(cin.trim().length()<4 || cin.trim().length()>10 ){
                throw new FormException("Cin doit avoir entre 4 et 10 caractère");

            }
            else {
                if(!cinUnique(cin))
                    throw new FormException("Cin entré déjà existant");

            }
        }
       else throw new FormException("Le champ cin est obligatoire");
    }

    public void verifierTel(String tel) throws FormException{
        if(tel != null && tel.trim().length()!=0){
            if(tel.trim().length()<4 || tel.trim().length()>10 ){
                throw new FormException("Tel doit avoir entre 4 et 10 caractère");

            }
            else {
                if(!telUnique(tel))
                    throw new FormException("Tel entré déjà existant");
                else if (!isNumeric(tel))
                    throw new FormException("Tel doit contenir que des numéros");

            }
        }
      else  throw new FormException("Le champ tel est obligatoire");
    }


    public void verifierMdp(String mdp) throws FormException{
        if(mdp != null && mdp.trim().length()!=0){
            if(mdp.trim().length()<4 || mdp.trim().length()>10){
                throw new FormException("Mot de passe doit avoir entre 4 et 10 caractère");

            }
        }
       else throw new FormException("Le champ mot de passe est obligatoire");
    }
    /*
  //Utilisé avant avec la console
    public void verifierSexe(String sexe) throws  FormException{
       if(sexe != null && sexe.trim().length()!=0){
          if(!sexe.equalsIgnoreCase("F") && !sexe.equalsIgnoreCase("H")){
              throw new FormException("Sexe doit etre F / H");
          }
       }
      else throw new FormException("Le champ sexe est obligatoire !");
    }

    public void validerSexe(String sexe){
        try {
            verifierSexe(sexe);
        } catch (FormException e) {
           Errors().put(FIELD_SEXE,e.getMessage());
        }
    }
*/

    public void validerNom(String nom){
        try {
            verifierNom(nom);
        } catch (FormException e) {
            Errors().put(FIELD_NOM,e.getMessage());
        }
    }

    public void validerPrenom(String prenom){
        try {
            verifierPrenom(prenom);
        } catch (FormException e) {
            Errors().put(FIELD_PRENOM,e.getMessage());
        }

    }

    public void validerLogin(String login){
        try {
            verifierLogin(login);
        } catch (FormException e) {
            Errors().put(FIELD_LOGIN,e.getMessage());
        }

    }

    public void validerEmail(String email){
        try {
            verifierEmail(email);
        } catch (FormException e) {
            Errors().put(FIELD_EMAIL,e.getMessage());
        }

    }

    public void validerCin(String cin){
        try {
            verifierCin(cin);
        } catch (FormException e) {
            Errors().put(FIELD_CIN,e.getMessage());
        }

    }

    public void validerTel(String tel){
        try {
            verifierTel(tel);
        } catch (FormException e) {
            Errors().put(FIELD_TEL,e.getMessage());
        }
    }


    public void validerMdp(String mdp){
        try {
            verifierMdp(mdp);
        } catch (FormException e) {
            Errors().put(FIELD_PASS,e.getMessage());
        }

    }

    public Client validerCreation(String nom , String prenom , String login , String mdp , String email , String cin ,String tel , String sexe ){
       validerNom(nom);
       validerPrenom(prenom);
       validerCin(cin);
       validerEmail(email);
       validerLogin(login);
       validerMdp(mdp);
       validerTel(tel);
     //  validerSexe(sexe);
       if(Errors().isEmpty()){
           Client c;
           if(sexe.equalsIgnoreCase("FEMME"))
          c=new Client(login,mdp,nom,prenom,email,cin,tel, Sexe.FEMME);
           else
            c = new Client(login,mdp,nom,prenom,email,cin,tel, Sexe.HOMME);
           return c;
       }
       return null ;
    }








}
