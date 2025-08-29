package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Score {

    private int Sets;
    private int Games;
    private int Points;

    public boolean isAdvantage=false;

    public String displayPoints(){
        if (isAdvantage){
            return "AD";
        }
        return String.valueOf(Points);
    }
}
