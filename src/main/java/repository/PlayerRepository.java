package repository;

import entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class PlayerRepository implements Repository<Integer, Player> {

    private final static PlayerRepository INSTANCE = new PlayerRepository();

    @Override
    public Player save(Player player) {
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession()){

            session.beginTransaction();

            session.persist(player);

            session.getTransaction().commit();
            return player;
        }
    }

    @Override
    public Optional<Player> findByName(Player player) {
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
             Session session = sessionFactory.openSession()){


            return Optional.ofNullable(player);
        }
    }

    public static PlayerRepository getInstance(){
        return INSTANCE;
    }

    public List<Player> findAll() {
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
             Session session = sessionFactory.openSession()){

            session.beginTransaction();

            List<Player> list = session.createQuery("from Player", Player.class).getResultList();

            session.getTransaction().commit();

            return list;
        }
    }

    public void deleteAll() {
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
             Session session = sessionFactory.openSession()){

            session.beginTransaction();

            session.createNativeMutationQuery("DROP table players").executeUpdate();

            session.getTransaction().commit();

        }
    }
}
