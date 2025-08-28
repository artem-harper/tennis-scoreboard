package service;

import dto.PlayerDto;
import dto.MatchScore;
import dto.Score;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MatchService {

    private final static MatchService INSTANCE = new MatchService();

    private ConcurrentMap<UUID, MatchScore> matches = new ConcurrentHashMap<>();

    private final int MAX_POINT = 4;
    private final int MAX_GAMES = 6;

    private final int ADD_POINTS1 = 15;
    private final int ADD_POINTS2 = 10;

    private int currentPoint1;
    private int currentPoint2;

    public UUID startNewMatch(PlayerDto player1, PlayerDto player2){
        UUID MatchUUID = UUID.randomUUID();

        MatchScore matchScore = new MatchScore(MatchUUID, player1, player2) ;

        matches.put(MatchUUID, matchScore);

        return MatchUUID;
    }


    public void winPoint(MatchScore matchScore, int winnerId){
        Score playerScore1 = matchScore.getPlayerScore1();
        Score playerScore2 = matchScore.getPlayerScore2();

        if (currentPoint1==MAX_POINT){
            playerScore1.setGames(playerScore1.getGames()+1);
            currentPoint1=0;
            playerScore1.setPoints(0);
            return;
        }
        if (winnerId==matchScore.getPlayer1().getId()){
            addPoint(playerScore1, currentPoint1);
            currentPoint1++;

        }else if (winnerId == matchScore.getPlayer2().getId()){
            addPoint(playerScore2, currentPoint2);
            currentPoint2++;
        }

    }




    public void addPoint(Score playerScore, int currentPoint){
        if (currentPoint==0 || currentPoint==1){
            playerScore.setPoints(playerScore.getPoints()+ADD_POINTS1);
        }else{
            playerScore.setPoints(playerScore.getPoints()+ADD_POINTS2);
        }

    }

    public static MatchService getInstance(){
        return INSTANCE;
    }

    public ConcurrentMap<UUID, MatchScore> getMatches() {
        return matches;
    }

}
