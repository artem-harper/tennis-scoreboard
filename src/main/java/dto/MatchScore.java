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

    private Score playerScore1 = new Score();
    private Score playerScore2 = new Score();

    public MatchScore(UUID matchUUID, PlayerDto player1, PlayerDto player2) {
        MatchUUID = matchUUID;
        this.player1 = player1;
        this.player2 = player2;
    }

    public void resetPoints(){
        playerScore1.setPoints(0);
        playerScore2.setPoints(0);
    }
}
