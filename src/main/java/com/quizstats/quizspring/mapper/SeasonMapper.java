package com.quizstats.quizspring.mapper;

import com.quizstats.quizspring.controller.season.dto.SeasonDTO;
import com.quizstats.quizspring.repository.season.Season;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SeasonMapper {

    SeasonMapper INSTANCE = Mappers.getMapper(SeasonMapper.class);

    SeasonDTO toDTO(Season season);

    List<SeasonDTO> toDTOList(List<Season> seasonList);

}
