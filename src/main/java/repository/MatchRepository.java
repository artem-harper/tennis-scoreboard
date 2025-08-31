package repository;

import entity.Match;
import entity.Player;
import org.hibernate.Session;

import java.util.List;
import java.util.Objects;

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

            List<Match> matches = session.createQuery("from Match", Match.class)
                    .setFirstResult((page - 1) * matchesOnPage)
                    .setMaxResults(matchesOnPage)
                    .getResultList();

            session.getTransaction().commit();

            return matches;
        }
    }

    public static MatchRepository getInstance() {
        return INSTANCE;
    }

}
