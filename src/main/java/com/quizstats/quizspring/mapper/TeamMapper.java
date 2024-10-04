package com.quizstats.quizspring.mapper;

import com.quizstats.quizspring.controller.team.dto.TeamDTO;
import com.quizstats.quizspring.repository.team.Team;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TeamMapper {
    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    TeamDTO toDTO(Team team);

    List<TeamDTO> toDTOList(List<Team> teamList);
}
