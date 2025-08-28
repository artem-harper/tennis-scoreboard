package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MatchScore {

    private Integer playerId1;
    private Integer playerId2;

    private Integer score;

    public MatchScore(Integer playerId1, Integer playerId2) {
        this.playerId1 = playerId1;
        this.playerId2 = playerId2;
    }
}
