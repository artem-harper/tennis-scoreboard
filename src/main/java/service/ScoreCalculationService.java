package service;

import dto.MatchScore;
import dto.Score;

public class ScoreCalculationService {

    private static final ScoreCalculationService INSTANCE = new ScoreCalculationService();

    private final int MAX_POINT = 4;
    private final int MAX_GAMES = 6;
    private final int MAX_TIEBREAK_GAMES = 7;
    private final int MAX_SETS = 2;

    private final int ADD_POINTS1 = 15;
    private final int ADD_POINTS2 = 10;

    private boolean isTieBreak = false;

    private int currentPoint1;
    private int currentPoint2;

    public void winPoint(MatchScore matchScore, int winnerId) {
        Score playerScore1 = matchScore.getPlayerScore1();
        Score playerScore2 = matchScore.getPlayerScore2();

        tieBreak(matchScore);

        if (winnerId == matchScore.getPlayer1().getId()) {

            if (advantage(playerScore1, playerScore2)) {
                addPoint(playerScore1, currentPoint1);
                currentPoint1++;
            }

            if (playerScore1.getGames()==6){
                System.out.println();
            }
            if (winGame(playerScore1, currentPoint1, currentPoint2)) {
                matchScore.resetPoints();
                currentPoint1 = 0;
                currentPoint2 = 0;
            }
            if (difference(playerScore1, playerScore2)) {
                if (winSet(playerScore1)) {
                    matchScore.resetGames();
                }
            }

        } else if (winnerId == matchScore.getPlayer2().getId()) {

            if (advantage(playerScore2, playerScore1)) {
                addPoint(playerScore2, currentPoint2);
                currentPoint2++;
            }
            if (winGame(playerScore2, currentPoint2, currentPoint1)) {
                matchScore.resetPoints();
                currentPoint2 = 0;
                currentPoint1 = 0;
            }
            if (difference(playerScore2, playerScore1)) {
                if (winSet(playerScore2)) {
                    matchScore.resetGames();
                }
            }
        }
    }

    public void tieBreak(MatchScore matchScore) {
        if (matchScore.getPlayerScore1().getGames() == 6 && matchScore.getPlayerScore2().getGames() == 6) {
            isTieBreak = true;

        } else {
            isTieBreak = false;
        }
    }

    public boolean difference(Score playerScore, Score opponentScore) {
        if (isTieBreak) {
            return true;
        }else {
            return Math.abs(playerScore.getGames() - opponentScore.getGames()) >= 2;
        }
    }

    public boolean advantage(Score playerScore, Score opponentScore) {

        if (playerScore.displayPoints().equals("40") && opponentScore.displayPoints().equals("40")) {
            playerScore.setAdvantage(true);
            return false;
        }
        if (opponentScore.isAdvantage) {
            opponentScore.setAdvantage(false);
            opponentScore.setPoints(40);
            return false;
        }
        playerScore.setAdvantage(false);
        return true;
    }

    private boolean winGame(Score playerScore1, int currentPoint, int playerPoint2) {
        if (isTieBreak) {
            if (currentPoint==7 && currentPoint-playerPoint2>=2){
                playerScore1.setGames(playerScore1.getGames() + 1);
                return true;
            } else if (currentPoint>7 && currentPoint-playerPoint2>=2 ) {
                playerScore1.setGames(playerScore1.getGames() + 1);
                return true;
            }
            return false;
        }
        if (currentPoint == MAX_POINT) {
            playerScore1.setGames(playerScore1.getGames() + 1);
            return true;
        }
        return false;
    }

    private boolean winSet(Score playerScore) {
        if (isTieBreak){
            if (playerScore.getGames() == MAX_TIEBREAK_GAMES) {
                playerScore.setSets(playerScore.getSets() + 1);
                return true;
            }
            return false;
        }
        if (playerScore.getGames() == MAX_GAMES) {
            playerScore.setSets(playerScore.getSets() + 1);
            return true;
        } else if (playerScore.getGames() == MAX_TIEBREAK_GAMES) {
            playerScore.setSets(playerScore.getSets() + 1);
            return true;
        }
        return false;
    }

    private void addPoint(Score playerScore, int currentPoint) {
        if (isTieBreak){
            playerScore.setPoints(playerScore.getPoints()+1);
            return;
        }
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
