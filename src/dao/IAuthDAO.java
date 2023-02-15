package dao;

import presentation.modele.Utilisateur;

public interface IAuthDAO {
    Utilisateur findByLoginAndPass(String login, String pass);
    boolean loginExist(String login);

}
