package repository;

import entity.Match;
import org.hibernate.Session;
import java.util.List;

public class MatchRepository extends BaseRepository<Integer, Match> {

    private static final MatchRepository INSTANCE = new MatchRepository();

    private final PlayerRepository playerRepository = PlayerRepository.getInstance();

    private MatchRepository() {
        super(Match.class);
    }

    @Override
    public Match save(Match match) {
        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            session.persist(match);

            session.getTransaction().commit();

            return match;
        }
    }

    public List<Match> findMatchesPage(int page) {
        int matchesOnPage = 5;
        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            List<Match> matches = session.createQuery("from Match m join fetch Player p1 on m.player1.id=p1.id" +
                                                      " join fetch  Player p2 on m.player2.id = p2.id", Match.class)
                    .setFirstResult((page - 1) * matchesOnPage)
                    .setMaxResults(matchesOnPage)
                    .getResultList();

            session.getTransaction().commit();

            return matches;
        }
    }

    public List<Match> findMatchesPageByName(int page, String name) {
        int matchesOnPage = 5;
        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            List<Match> matches = session.createQuery("from Match m where lower(m.player1.name)=:name1 or lower(m.player2.name)=:name2",
                            Match.class)
                    .setParameter("name1", name)
                    .setParameter("name2", name)
                    .setFirstResult((page - 1) * matchesOnPage)
                    .setMaxResults(matchesOnPage)
                    .getResultList();

            session.getTransaction().commit();

            return matches;
        }
    }

    public List<Match> findAllMatchesByName(String name) {
        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            List<Match> matches = session.createQuery("from Match m where lower(m.player1.name)=:name1 or lower(m.player2.name)=:name2",
                            Match.class)
                    .setParameter("name1", name)
                    .setParameter("name2", name)
                    .getResultList();

            session.getTransaction().commit();

            return matches;
        }
    }

    public static MatchRepository getInstance() {
        return INSTANCE;
    }

}
