package service;

import dto.MatchScore;
import dto.Score;

public class ScoreCalculationService {

    private static final ScoreCalculationService INSTANCE = new ScoreCalculationService();

    private final int MAX_POINT = 4;
    private final int ADVANTAGE_POINTS = 6;
    private final int MAX_GAMES = 6;
    private final int MAX_SETS = 2;

    private final int ADD_POINTS1 = 15;
    private final int ADD_POINTS2 = 10;

    private int currentPoint1;
    private int currentPoint2;

    public void winPoint(MatchScore matchScore, int winnerId) {
        Score playerScore1 = matchScore.getPlayerScore1();
        Score playerScore2 = matchScore.getPlayerScore2();

        if (winnerId == matchScore.getPlayer1().getId()) {

            if (advantage(playerScore1, playerScore2)) {
                addPoint(playerScore1, currentPoint1);
                currentPoint1++;
            }
            if (winGame(playerScore1, currentPoint1)) {
                matchScore.resetPoints();
                currentPoint1 = 0;
                currentPoint2 = 0;
            }
            winSet(playerScore1);

        } else if (winnerId == matchScore.getPlayer2().getId()) {

            if (advantage(playerScore2, playerScore1)) {
                addPoint(playerScore2, currentPoint2);
                currentPoint2++;
            }
            if (winGame(playerScore2, currentPoint2)) {
                matchScore.resetPoints();
                currentPoint2 = 0;
                currentPoint1 = 0;
            }
            winSet(playerScore2);
        }

    }

    public boolean advantage(Score playerScore, Score opponentScore) {

        if (playerScore.displayPoints().equals("40") && opponentScore.displayPoints().equals("40")) {
            playerScore.setAdvantage(true);
            return false;
        }
        if (opponentScore.isAdvantage){
            opponentScore.setAdvantage(false);
            opponentScore.setPoints(40);
            return false;
        }
        playerScore.setAdvantage(false);
        return true;
    }

    public boolean winGame(Score playerScore, int currentPoint) {
        if (currentPoint == MAX_POINT) {
            playerScore.setGames(playerScore.getGames() + 1);
            return true;
        }
        return false;
    }

    public void winSet(Score playerScore) {
        if (playerScore.getGames() == MAX_GAMES) {
            playerScore.setSets(playerScore.getSets() + 1);
            playerScore.setGames(0);
        }
    }

    public void addPoint(Score playerScore, int currentPoint) {
        if (currentPoint == 0 || currentPoint == 1) {
            playerScore.setPoints(playerScore.getPoints() + ADD_POINTS1);
        } else {
            playerScore.setPoints(playerScore.getPoints() + ADD_POINTS2);
        }

    }

    public static ScoreCalculationService getInstance() {
        return INSTANCE;
    }
}
