package service;

import dto.PlayerDto;
import dto.MatchScore;
import dto.Score;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class OngoingMatchesService {

    private final static OngoingMatchesService INSTANCE = new OngoingMatchesService();

    private ConcurrentMap<UUID, MatchScore> matches = new ConcurrentHashMap<>();


    public UUID startNewMatch(PlayerDto player1, PlayerDto player2){
        UUID MatchUUID = UUID.randomUUID();

        MatchScore matchScore = new MatchScore(MatchUUID, player1, player2) ;

        matches.put(MatchUUID, matchScore);

        return MatchUUID;
    }

    public void removeEndedMatch(UUID MatchUUID){
        matches.remove(MatchUUID);
    }

    public static OngoingMatchesService getInstance(){
        return INSTANCE;
    }

    public ConcurrentMap<UUID, MatchScore> getMatches() {
        return matches;
    }

}
