package repository;

import entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface Repository <K extends Serializable, T extends BaseEntity<K>>{

    T save(T object);

    T findById(K id);

    void delete(K id);

    List<T> findAll();

}
