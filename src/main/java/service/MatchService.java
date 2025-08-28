package service;

import dto.PlayerDto;
import entity.MatchScore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MatchService {

    private final static MatchService INSTANCE = new MatchService();

    private ConcurrentMap<UUID, MatchScore> matches = new ConcurrentHashMap<>();

    public void startNewMatch(PlayerDto player1, PlayerDto player2){
        UUID MatchUUID = UUID.randomUUID();

        MatchScore matchScore = new MatchScore(player1.getId(), player2.getId());

        matches.put(MatchUUID, matchScore);
    }

    public static MatchService getInstance(){
        return INSTANCE;
    }
}
