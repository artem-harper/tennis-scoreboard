package mapper;

import dto.MatchDto;
import entity.Match;
import entity.Player;

public class MatchMapper implements Mapper<MatchDto, Match> {

    private static final MatchMapper INSTANCE = new MatchMapper();

    PlayerMapper playerMapper = PlayerMapper.getInstance();

    private MatchMapper(){

    }

    @Override
    public Match mapToEntity(MatchDto matchDto) {
        return Match.builder()
                .player1(playerMapper.mapToEntity(matchDto.getPlayer1()))
                .player2(playerMapper.mapToEntity(matchDto.getPlayer2()))
                .winner(playerMapper.mapToEntity(matchDto.getWinner()))
                .build();
    }

    @Override
    public MatchDto mapToDto(Match match) {
        return MatchDto.builder()
                .player1(playerMapper.mapToDto(match.getPlayer1()))
                .player2(playerMapper.mapToDto(match.getPlayer2()))
                .winner(playerMapper.mapToDto(match.getWinner()))
                .build();
    }

    public static MatchMapper getInstance(){
        return INSTANCE;
    }
}
