package repository;

import entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class PlayerRepository extends BaseRepository<Integer, Player>{

    private final static PlayerRepository INSTANCE = new PlayerRepository();

    private PlayerRepository() {
        super(Player.class);
    }

    public Optional<Player> findByName(Player player) {
        try (Session session = sessionFactory.openSession()){

            session.beginTransaction();

            Player player1 = session.createQuery("from Player p where p.name=:name", Player.class)
                    .setParameter("name", player.getName())
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            session.getTransaction().commit();

            return Optional.ofNullable(player1);
        }
    }

    public static PlayerRepository getInstance(){
        return INSTANCE;
    }

    public void deleteAll() {
        try (Session session = sessionFactory.openSession()){

            session.beginTransaction();

            session.createNativeMutationQuery("DROP table players").executeUpdate();

            session.getTransaction().commit();

        }
    }
}
