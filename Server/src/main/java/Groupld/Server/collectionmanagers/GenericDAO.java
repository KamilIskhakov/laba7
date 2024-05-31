package Groupld.Server.collectionmanagers;

import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {
    void save(T entity);
    T findById(ID id);
    List<T> findAll();
    void update(T entity);
    void delete(T entity);
    Logger getLogger();
    SessionFactory getSessionFactory();

}
