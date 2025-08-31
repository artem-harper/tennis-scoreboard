package dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

public class MatchScore {

    private UUID MatchUUID;
    private PlayerDto player1;
    private PlayerDto player2;
    private PlayerDto winner;

    private Score playerScore1 = new Score();
    private Score playerScore2 = new Score();

    private boolean isTieBreak = false;
    private int currentPoint1;
    private int currentPoint2;

    public MatchScore(UUID matchUUID, PlayerDto player1, PlayerDto player2) {
        MatchUUID = matchUUID;
        this.player1 = player1;
        this.player2 = player2;
    }

    public void resetPoints(){
        this.currentPoint1 = 0;
        this.currentPoint2 = 0;
        playerScore1.setPoints(0);
        playerScore2.setPoints(0);
    }

    public void resetGames(){
        playerScore1.setGames(0);
        playerScore2.setGames(0);

    }
}
