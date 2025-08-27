package service;

import dto.PlayerDto;

import java.util.UUID;

public class MatchService {

    private final static MatchService INSTANCE = new MatchService();

    private Map<UUID, >

    public void startNewMatch(PlayerDto player1, PlayerDto player2){

    }

    public static MatchService getInstance(){
        return INSTANCE;
    }
}
