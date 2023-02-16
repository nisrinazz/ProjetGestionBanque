package dao;

import presentation.modele.Agence;

import java.util.List;

public interface IAgenceDAO {
    Agence save(Agence agence);
    Agence findFirst();
    List<Agence> findAll();

    public List<Agence> findByKeywordLike(String keyword);
}
