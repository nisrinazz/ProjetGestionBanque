package dao;

import presentation.modele.Client;
import presentation.modele.Compte;

import java.util.List;

public interface ICompteDAO extends IDao<Compte,String>{

    List<Compte> findByOwner(Client c);
    public List<Compte> findByKeywordLike(String keyword);

}
