package dto;

import entity.Player;
import lombok.*;

@EqualsAndHashCode
@AllArgsConstructor
@Builder
@ToString
@Getter
public class MatchDto {

    private PlayerDto player1;
    private PlayerDto player2;

    private PlayerDto winner;

}
