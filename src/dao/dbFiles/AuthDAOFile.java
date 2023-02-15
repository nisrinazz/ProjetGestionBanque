package dao.dbFiles;

import dao.IAuthDAO;
import dao.IClientDAO;
import presentation.modele.Admin;
import presentation.modele.Utilisateur;

public class AuthDAOFile implements IAuthDAO {

    static final Admin admin = Admin.getInstance();
    IClientDAO clientDAO = new ClientDAOFile();

    public static Admin getAdmin() {
        return admin;
    }

    public AuthDAOFile(){

    }


    @Override
    public Utilisateur findByLoginAndPass(String login, String pass) {

        Utilisateur  user = null;

        if(admin.getLogin().equals(login)&& admin.getMotDePasse().equals(pass))
            user = admin;
        else{
            user =
                    clientDAO.findAll()
                            .stream()
                            .filter(client -> {
                                return client.getLogin().equals(login)
                                        && client.getMotDePasse().equals(pass);
                            })
                            .findFirst()
                            .orElse(null);
        }
        return  user;
    }

    @Override
    public boolean loginExist(String login) {
        if(admin.getLogin().equals(login))  return true ;
        else {
            Utilisateur user =clientDAO.findAll().stream()
                    .filter(client -> {
                        return client.getLogin().equals(login);
                    })
                    .findFirst()
                    .orElse(null);
            return user != null;
        }
    }

}
