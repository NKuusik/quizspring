package com.quizstats.quizspring.controller.team;

import com.quizstats.quizspring.controller.team.dto.TeamDTO;
import com.quizstats.quizspring.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    List<TeamDTO> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{teamName}")
    TeamDTO getTeamByName(@PathVariable String teamName) {
        return teamService.getTeamByName(teamName);
    }
}
