package com.quizstats.quizspring.service.team;

import com.quizstats.quizspring.controller.team.dto.TeamDTO;
import com.quizstats.quizspring.mapper.TeamMapper;
import com.quizstats.quizspring.repository.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamDTO> getAllTeams() {
        return TeamMapper.INSTANCE.toDTOList(teamRepository.findAll());
    }

    public TeamDTO getTeamByName(String name) {
        return teamRepository.findByName(name).map(TeamMapper.INSTANCE::toDTO)
            .orElseThrow(() -> new RuntimeException("Team not found with name: " + name));
    }
}
