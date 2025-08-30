package service;

import dto.Score;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScoreCalculationServiceTest {

    @Test
    void isAdvantage(){
        ScoreCalculationService scoreCalculationService = ScoreCalculationService.getInstance();

        boolean result = scoreCalculationService.advantage(new Score(0,0,40),
                new Score(0,0,40));

        assertFalse(result);
    }
}
