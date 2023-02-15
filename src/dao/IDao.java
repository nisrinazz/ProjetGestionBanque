package dao;

import java.util.List;

public interface IDao<T,ID> {

    List<T> findAll();
    T findById(ID id);
    T save(T t);
    T update(T t);
    boolean delete(ID id);


}
