package service;

import dto.PlayerDto;
import entity.Player;
import mapper.PlayerMapper;
import repository.PlayerRepository;

import java.util.Optional;

public class PlayerService {

    private final static PlayerService INSTANCE = new PlayerService();

    private final PlayerRepository playerRepository = PlayerRepository.getInstance();
    private final PlayerMapper playerMapper = PlayerMapper.getInstance();


    public PlayerDto save(PlayerDto createPlayerDto){
        Player player = playerMapper.mapToEntity(createPlayerDto);

        findByName(createPlayerDto);

        return playerMapper.mapToDto(playerRepository.save(player));
    }

    public Optional<Player> findByName(PlayerDto createPlayerDto){
        Player player = playerMapper.mapToEntity(createPlayerDto);

        return playerRepository.findByName(player);
    }

    public static PlayerService getInstance() {
        return INSTANCE;
    }
}
