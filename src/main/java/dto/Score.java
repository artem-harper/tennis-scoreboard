package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Score {

    private int Sets;
    private int Games;
    private int Points;

    public boolean isAdvantage=false;

    public Score(int sets, int games, int points) {
        Sets = sets;
        Games = games;
        Points = points;
    }

    public String displayPoints(){
        if (isAdvantage){
            return "AD";
        }
        return String.valueOf(Points);
    }
}
