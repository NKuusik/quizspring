package com.quizstats.quizspring.mapper;

import com.quizstats.quizspring.controller.game.dto.GameDTO;
import com.quizstats.quizspring.controller.season.dto.SeasonDTO;
import com.quizstats.quizspring.repository.game.Game;
import com.quizstats.quizspring.repository.season.Season;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GameMapper {
    GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);

    List<GameDTO> toDTOList(List<Game> gameList);

}
