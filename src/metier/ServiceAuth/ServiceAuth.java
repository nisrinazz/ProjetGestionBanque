package metier.ServiceAuth;

import dao.IAuthDAO;
import dao.IClientDAO;
import dao.ICompteDAO;
import dao.dbFiles.AuthDAOFile;
import dao.dbFiles.ClientDAOFile;
import dao.dbFiles.CompteDAOFile;
import metier.forms.LoginFormValidator;
import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.modele.Utilisateur;

import java.util.List;
import java.util.Map;

public class ServiceAuth implements IServiceAuth {
   private Utilisateur session;
   ICompteDAO compteDAO ;
   IClientDAO clientDAO;


    public Utilisateur getSession() {
        return session;
    }

    public void setSession(Utilisateur session) {
        this.session = session;
    }

    public void setSession(){
        session = clientDAO.findById(session.getId());
    }
    public ServiceAuth(){
        this.clientDAO = new ClientDAOFile();
        this.compteDAO = new CompteDAOFile();
    }

    @Override
    public List<Compte> choisirCompte(){
        return compteDAO.findByOwner((Client)session);
    }

    @Override
    public Map<String,String> seConnecter(String login , String mdp){
        IAuthDAO authDAO = new AuthDAOFile();
        LoginFormValidator loginFormValidator = new LoginFormValidator(authDAO);
        Utilisateur user = loginFormValidator.validerSession(login,mdp);
        if(user != null)
           session =user;

        return loginFormValidator.Errors();
}

    @Override
    public void seDeconnecter() {
        session=null;
    }

}
