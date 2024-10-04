package com.quizstats.quizspring.controller.team;

import com.quizstats.quizspring.controller.team.dto.TeamDTO;
import com.quizstats.quizspring.service.team.TeamService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TeamControllerTest {

    TeamDTO teamDTO1 = new TeamDTO("test team1");
    TeamDTO teamDTO2 = new TeamDTO("test team2");

    @Mock
    private TeamService teamService;

    @InjectMocks
    private TeamController teamController;

    @Test
    void getAllTeams() {

        // given
        BDDMockito.given(teamService.getAllTeams()).willReturn(Arrays.asList(teamDTO1, teamDTO2));

        // when
        List<TeamDTO> result = teamController.getAllTeams();

        // then
        assertEquals(2, result.size());
        assertTrue(result.contains(teamDTO1));
        assertTrue(result.contains(teamDTO2));
    }

    @Test
    void getTeamByName() {

        // given
        BDDMockito.given(teamService.getTeamByName("test team1")).willReturn(teamDTO1);
        BDDMockito.given(teamService.getTeamByName("test team2")).willReturn(teamDTO2);

        // when
        TeamDTO result1 = teamController.getTeamByName("test team1");
        TeamDTO result2 = teamController.getTeamByName("test team2");

        // then
        assertEquals(teamDTO1, result1);
        assertEquals(teamDTO2, result2);
    }
}