package repository;

import entity.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;
import java.io.Serializable;
import java.util.List;


public abstract class BaseRepository<K extends Serializable, T extends BaseEntity<K>> implements Repository<K, T> {

    protected final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private final Class<T> clazz;

    protected BaseRepository(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T save(T object) {
        try (Session session = sessionFactory.openSession()){

            session.beginTransaction();

            session.persist(object);

            session.getTransaction().commit();
            return object;
        }
    }

    @Override
    public T findById(K id) {
        try (Session session = sessionFactory.openSession()){

            session.beginTransaction();

            T object = session.find(clazz, id);

            session.getTransaction().commit();
            return object;
        }
    }

    @Override
    public void delete(K id) {
        try (Session session = sessionFactory.openSession()){

            session.beginTransaction();

            session.remove(id);

            session.getTransaction().commit();

        }
    }

    @Override
    public List<T> findAll() {
        try (Session session = sessionFactory.openSession()){

            session.beginTransaction();

            List<T> list = session.createQuery("from " + clazz.getSimpleName(), clazz).getResultList();

            session.getTransaction().commit();

            return list;
        }
    }

}
