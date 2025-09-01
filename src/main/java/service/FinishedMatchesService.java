package service;

import dto.MatchDto;
import entity.Match;
import mapper.MatchMapper;
import repository.MatchRepository;

import java.util.List;

public class FinishedMatchesService {

    private static final FinishedMatchesService INSTANCE = new FinishedMatchesService();

    MatchRepository matchRepository = MatchRepository.getInstance();
    MatchMapper matchMapper = MatchMapper.getInstance();

    private FinishedMatchesService(){

    }

    public MatchDto saveFinishedMatch(MatchDto matchDto){
        Match match = matchMapper.mapToEntity(matchDto);

        return matchMapper.mapToDto(matchRepository.save(match));
    }

    public List<MatchDto> findAllMatches(){
        return matchRepository.findAll().stream()
                .map(match -> matchMapper.mapToDto(match))
                .toList();
    }

    public List<MatchDto> findMatchesPage (int page){
        return matchRepository.findMatchesPage(page)
                .stream().map(match -> matchMapper.mapToDto(match))
                .toList();
    }

    public List<MatchDto> findMatchesPageFilterByName (int page, String name){
        return matchRepository.findMatchesPageByName(page, name)
                .stream().map(match -> matchMapper.mapToDto(match))
                .toList();
    }

    public List<MatchDto> findAllMatchesByName(String name){
        return matchRepository.findAllMatchesByName(name).stream()
                .map(match -> matchMapper.mapToDto(match))
                .toList();
    }

    public static FinishedMatchesService getInstance(){
        return INSTANCE;
    }
}
