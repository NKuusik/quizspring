package com.quizstats.quizspring.service.game;

import com.quizstats.quizspring.controller.game.dto.GameDTO;
import com.quizstats.quizspring.mapper.GameMapper;
import com.quizstats.quizspring.repository.game.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<GameDTO> getAllGames() {
        return GameMapper.INSTANCE.toDTOList(gameRepository.findAll());
    }

    public List<GameDTO> getAllGamesBySeason(String seasonName) {
        return GameMapper.INSTANCE.toDTOList(gameRepository.findBySeason_Name(seasonName));
    }
}
