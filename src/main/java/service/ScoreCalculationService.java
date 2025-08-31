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

    private ScoreCalculationService(){

    }

    public void winPoint(MatchScore matchScore, int winnerId) {

        Score playerScore1 = matchScore.getPlayerScore1();
        Score playerScore2 = matchScore.getPlayerScore2();

        tieBreak(matchScore);

        if (winnerId == matchScore.getPlayer1().getId()) {

            if (advantage(playerScore1, playerScore2)) {
                addPoint(matchScore, playerScore1, matchScore.getCurrentPoint1());
                matchScore.setCurrentPoint1(matchScore.getCurrentPoint1() + 1);
            }

            if (winGame(matchScore, playerScore1, matchScore.getCurrentPoint1(), matchScore.getCurrentPoint2())) {
                matchScore.resetPoints();
            }
            if (difference(matchScore, playerScore1, playerScore2)) {
                if (winSet(matchScore, playerScore1)) {
                    matchScore.resetGames();
                }
            }

        } else if (winnerId == matchScore.getPlayer2().getId()) {

            if (advantage(playerScore2, playerScore1)) {
                addPoint(matchScore, playerScore2, matchScore.getCurrentPoint2());
                matchScore.setCurrentPoint2(matchScore.getCurrentPoint2() + 1);
            }
            if (winGame(matchScore, playerScore2, matchScore.getCurrentPoint2(), matchScore.getCurrentPoint1())) {
                matchScore.resetPoints();
            }
            if (difference(matchScore, playerScore2, playerScore1)) {
                if (winSet(matchScore, playerScore2)) {
                    matchScore.resetGames();
                }
            }
        }
    }

    public boolean isGameOver(MatchScore matchScore) {
        if (matchScore.getPlayerScore1().getSets() == MAX_SETS) {
            matchScore.getPlayerScore1().setStatus("WIN");
            matchScore.setWinner(matchScore.getPlayer1());
            return true;
        } else if (matchScore.getPlayerScore2().getSets() == MAX_SETS) {
            matchScore.getPlayerScore2().setStatus("WIN");
            matchScore.setWinner(matchScore.getPlayer2());
            return true;
        }
        return false;
    }

    public void tieBreak(MatchScore matchScore) {
        if (matchScore.getPlayerScore1().getGames() == 6 && matchScore.getPlayerScore2().getGames() == 6) {
            matchScore.setTieBreak(true);
        } else {
            matchScore.setTieBreak(false);
        }
    }

    public boolean difference(MatchScore matchScore, Score playerScore, Score opponentScore) {
        if (matchScore.isTieBreak()) {
            return true;
        } else {
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

    private boolean winGame(MatchScore matchScore, Score playerScore1, int currentPoint, int playerPoint2) {
        if (matchScore.isTieBreak()) {
            if (currentPoint == 7 && currentPoint - playerPoint2 >= 2) {
                playerScore1.setGames(playerScore1.getGames() + 1);
                return true;
            } else if (currentPoint > 7 && currentPoint - playerPoint2 >= 2) {
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

    private boolean winSet(MatchScore matchScore, Score playerScore) {
        if (matchScore.isTieBreak()) {
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

    private void addPoint(MatchScore matchScore, Score playerScore, int currentPoint) {
        if (matchScore.isTieBreak()) {
            playerScore.setPoints(playerScore.getPoints() + 1);
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