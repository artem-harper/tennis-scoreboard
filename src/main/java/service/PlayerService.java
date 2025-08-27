package service;

import dto.PlayerDto;
import entity.Player;
import mapper.PlayerMapper;
import repository.PlayerRepository;

public class PlayerService {

    private final static PlayerService INSTANCE = new PlayerService();

    private final PlayerRepository playerRepository = PlayerRepository.getInstance();
    private final PlayerMapper playerMapper = PlayerMapper.getInstance();


    public PlayerDto save(PlayerDto createPlayerDto){
        Player player = playerMapper.mapToEntity(createPlayerDto);

        return playerMapper.mapToDto(playerRepository.save(player));
    }

    public void findByName(PlayerDto createPlayerDto){

    }

    public static PlayerService getInstance() {
        return INSTANCE;
    }
}
