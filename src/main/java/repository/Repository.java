package repository;

import java.io.Serializable;
import java.util.Optional;

public interface Repository <K extends Serializable, T>{
    T save(T object);

    Optional<T> findByName(T object);

}
