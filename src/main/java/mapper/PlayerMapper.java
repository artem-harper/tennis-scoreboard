package mapper;

import dto.PlayerDto;
import entity.Player;
import jakarta.persistence.Id;

public class PlayerMapper implements Mapper<PlayerDto, Player>{

    private final static PlayerMapper INSTANCE = new PlayerMapper();

    @Override
    public Player mapToEntity(PlayerDto object) {
        return Player.builder()
                .id(object.getId())
                .name(object.getName())
                .build();
    }

    @Override
    public PlayerDto mapToDto(Player object) {
        return PlayerDto.builder()
                .id(object.getId())
                .name(object.getName())
                .build();
    }

    public static PlayerMapper getInstance(){
        return INSTANCE;
    }
}
