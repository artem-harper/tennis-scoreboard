package service;

import dto.PlayerDto;

public class MatchService {

    private final static MatchService INSTANCE = new MatchService();

    public void startNewMatch(PlayerDto player1, PlayerDto player2){

    }

    public static MatchService getInstance(){
        return INSTANCE;
    }
}
