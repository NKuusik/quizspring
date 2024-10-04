package com.quizstats.quizspring.service.team;

import com.quizstats.quizspring.controller.team.dto.TeamDTO;
import com.quizstats.quizspring.mapper.TeamMapper;
import com.quizstats.quizspring.repository.team.Team;
import com.quizstats.quizspring.repository.team.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TeamServiceTest {

    Team team1 =  new Team(1, "test team1");
    Team team2 =  new Team(2, "test team2");

    TeamDTO teamDTO1 = TeamMapper.INSTANCE.toDTO(team1);
    TeamDTO teamDTO2 = TeamMapper.INSTANCE.toDTO(team2);

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    @Test
    void getAllTeams() {

        // given
        BDDMockito.given(teamRepository.findAll()).willReturn(Arrays.asList(team1, team2));

        // when
        List<TeamDTO> result = teamService.getAllTeams();

        // then
        assertEquals(2, result.size());
        assertTrue(result.contains(teamDTO1));
        assertTrue(result.contains(teamDTO2));
    }

    @Test
    void getTeamByName() {

        // given
        BDDMockito.given(teamRepository.findByName("test team1")).willReturn(Optional.of(team1));
        BDDMockito.given(teamRepository.findByName("test team2")).willReturn(Optional.of(team2));
        BDDMockito.given(teamRepository.findByName("test team3")).willReturn(Optional.empty());

        // when
        TeamDTO result1 = teamService.getTeamByName("test team1");
        TeamDTO result2 = teamService.getTeamByName("test team2");

        // then
        assertEquals(teamDTO1, result1);
        assertEquals(teamDTO2, result2);
        assertThrows(RuntimeException.class, () -> teamService.getTeamByName("test team3"));
    }
}