package dao;


import presentation.modele.Client;

import java.util.List;

public interface IClientDAO extends IDao<Client, Long> {
    public List<Client> findByKeywordLike(String keyWord);


}
