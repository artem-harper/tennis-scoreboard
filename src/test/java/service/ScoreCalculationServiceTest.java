package service;

import dto.MatchScore;
import dto.PlayerDto;
import entity.Match;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreCalculationServiceTest {

    private static ScoreCalculationService scoreCalculationService;
    private static PlayerDto player1;
    private static PlayerDto player2;
    private static MatchScore matchScore;


    @BeforeAll
    static void init() {
        scoreCalculationService = ScoreCalculationService.getInstance();
    }

    @BeforeEach
    void createMatch() {
        player1 = PlayerDto.builder()
                .id(1)
                .name("Petya")
                .build();

        player2 = PlayerDto.builder()
                .id(2)
                .name("Vasya")
                .build();

        matchScore = new MatchScore(null, player1, player2);
    }

    @Test
    void gameShouldNotEndOnDeuce() {

        Integer player1Id = player1.getId();
        Integer player2Id = player2.getId();

        for (int i = 0; i < 3; i++) {
            scoreCalculationService.winPoint(matchScore, player1Id);
        }

        for (int i = 0; i < 3; i++) {
            scoreCalculationService.winPoint(matchScore, player2Id);
        }

        scoreCalculationService.winPoint(matchScore, player2Id);

        assertEquals(0, matchScore.getPlayerScore1().getGames());
        assertEquals(0, matchScore.getPlayerScore2().getGames());
        assertTrue(matchScore.getPlayerScore2().isAdvantage);
        assertFalse(matchScore.getPlayerScore1().isAdvantage);

    }

    @Test
    void playerShouldWinGameWith40And0Score() {

        Integer player1Id = player1.getId();

        for (int i = 0; i < 4; i++) {
            scoreCalculationService.winPoint(matchScore, player1Id);
        }

        assertEquals(1, matchScore.getPlayerScore1().getGames());
        assertEquals(0, matchScore.getPlayerScore2().getGames());
        assertEquals(0, matchScore.getPlayerScore1().getPoints());

    }

    @Test
    void with6And6GamesShouldStartTiebreak() {
        Integer player1Id = player1.getId();
        Integer player2Id = player2.getId();

        for (int i = 0; i < 20; i++) {
            scoreCalculationService.winPoint(matchScore, player1Id);
        }

        for (int i = 0; i < 20; i++) {
            scoreCalculationService.winPoint(matchScore, player2Id);
        }

        for (int i = 0; i < 5; i++) {
            scoreCalculationService.winPoint(matchScore, player1Id);
        }

        for (int i = 0; i < 5; i++) {
            scoreCalculationService.winPoint(matchScore, player2Id);
        }

        assertEquals(6, matchScore.getPlayerScore1().getGames());
        assertEquals(6, matchScore.getPlayerScore2().getGames());
        assertTrue(matchScore.isTieBreak());

    }

    @Test
    void playerShouldWinWith2sets(){
        Integer player1Id = player1.getId();

        for (int i = 0; i < 48; i++) {
            scoreCalculationService.winPoint(matchScore, player1Id);
        }

        assertEquals(2, matchScore.getPlayerScore1().getSets());
        assertTrue(scoreCalculationService.isGameOver(matchScore));
    }
}
