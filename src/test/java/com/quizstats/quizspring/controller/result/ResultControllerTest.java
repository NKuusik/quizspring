package com.quizstats.quizspring.controller.result;

import com.quizstats.quizspring.controller.game.dto.GameDTO;
import com.quizstats.quizspring.controller.result.dto.ResultDTO;
import com.quizstats.quizspring.controller.season.dto.SeasonDTO;
import com.quizstats.quizspring.controller.team.dto.TeamDTO;
import com.quizstats.quizspring.exception.ApiRequestException;
import com.quizstats.quizspring.service.result.ResultService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ResultControllerTest {
    TeamDTO teamDTO1 = new TeamDTO("test team1");
    TeamDTO teamDTO2 = new TeamDTO("test team2");

    SeasonDTO seasonDTO1 = new SeasonDTO("test season1");
    SeasonDTO seasonDTO2 = new SeasonDTO("test season2");

    GameDTO gameDTO1 = GameDTO.builder()
        .gameNumber(1).season(seasonDTO1).build();

    GameDTO gameDTO2 = GameDTO.builder()
        .gameNumber(2).season(seasonDTO2).build();

    ResultDTO resultDTO1 = ResultDTO.builder()
            .resultPoints(5.0).team(teamDTO1).game(gameDTO1).build();

    ResultDTO resultDTO2 = ResultDTO.builder()
            .resultPoints(10.0).team(teamDTO2).game(gameDTO1).build();

    ResultDTO resultDTO3 = ResultDTO.builder()
            .resultPoints(7.0).team(teamDTO1).game(gameDTO2).build();

    ResultDTO resultDTO4 = ResultDTO.builder()
            .resultPoints(1.0).team(teamDTO2).game(gameDTO2).build();

    @Mock
    private ResultService resultService;

    @InjectMocks
    private ResultController resultController;

    @Test
    void getResultsByParams_team() {

        // given
        BDDMockito.given(resultService.getAllResultsByTeam("test team1"))
            .willReturn(Arrays.asList(resultDTO1, resultDTO3));
        BDDMockito.given(resultService.getAllResultsByTeam("test team2"))
            .willReturn(Arrays.asList(resultDTO2, resultDTO4));

        // when
        List<ResultDTO> result1 = resultController.getResultsByParams(Optional.of("test team1"),
            Optional.empty(), Optional.empty());
        List<ResultDTO> result2 = resultController.getResultsByParams(Optional.of("test team2"),
            Optional.empty(), Optional.empty());

        // then
        assertEquals(2, result1.size());
        assertEquals(2, result2.size());
        assertTrue(result1.contains(resultDTO1));
        assertTrue(result1.contains(resultDTO3));
        assertTrue(result2.contains(resultDTO2));
        assertTrue(result2.contains(resultDTO4));
    }

    @Test
    void getResultsByParams_game() {

        // given
        BDDMockito.given(resultService.getAllResultsByGame(1))
            .willReturn(Arrays.asList(resultDTO1, resultDTO2));
        BDDMockito.given(resultService.getAllResultsByGame(2))
            .willReturn(Arrays.asList(resultDTO3, resultDTO4));

        // when
        List<ResultDTO> result1 = resultController.getResultsByParams(Optional.empty(),
            Optional.of(1), Optional.empty());
        List<ResultDTO> result2 = resultController.getResultsByParams(Optional.empty(),
            Optional.of(2), Optional.empty());

        // then
        assertEquals(2, result1.size());
        assertEquals(2, result2.size());
        assertTrue(result1.contains(resultDTO1));
        assertTrue(result1.contains(resultDTO2));
        assertTrue(result2.contains(resultDTO3));
        assertTrue(result2.contains(resultDTO4));
    }


    @Test
    void getResultsByParams_team_game() {

        // given
        BDDMockito.given(resultService.getAllResultsByTeamAndGame("test team1", 1))
            .willReturn(Collections.singletonList(resultDTO1));
        BDDMockito.given(resultService.getAllResultsByTeamAndGame("test team2", 1))
            .willReturn(Collections.singletonList(resultDTO2));
        BDDMockito.given(resultService.getAllResultsByTeamAndGame("test team1", 2))
            .willReturn(Collections.singletonList(resultDTO3));
        BDDMockito.given(resultService.getAllResultsByTeamAndGame("test team2", 2))
            .willReturn(Collections.singletonList(resultDTO4));

        // when
        List<ResultDTO> result1 = resultController.getResultsByParams(Optional.of("test team1"),
            Optional.of(1), Optional.empty());
        List<ResultDTO> result2 = resultController.getResultsByParams(Optional.of("test team2"),
            Optional.of(1), Optional.empty());
        List<ResultDTO> result3 = resultController.getResultsByParams(Optional.of("test team1"),
            Optional.of(2), Optional.empty());
        List<ResultDTO> result4 = resultController.getResultsByParams(Optional.of("test team2"),
            Optional.of(2), Optional.empty());

        // then
        assertEquals(1, result1.size());
        assertEquals(1, result2.size());
        assertEquals(1, result1.size());
        assertEquals(1, result2.size());

        assertTrue(result1.contains(resultDTO1));
        assertTrue(result2.contains(resultDTO2));
        assertTrue(result3.contains(resultDTO3));
        assertTrue(result4.contains(resultDTO4));
    }


    @Test
    void getResultsByParams_season() {

        // given
        BDDMockito.given(resultService.getAllResultsBySeason("test season1"))
            .willReturn(Arrays.asList(resultDTO1, resultDTO2));
        BDDMockito.given(resultService.getAllResultsBySeason("test season2"))
            .willReturn(Arrays.asList(resultDTO3, resultDTO4));

        // when
        List<ResultDTO> result1 = resultController.getResultsByParams(Optional.empty(),
            Optional.empty(), Optional.of("test season1"));
        List<ResultDTO> result2 = resultController.getResultsByParams(Optional.empty(),
            Optional.empty(), Optional.of("test season2"));

        // then
        assertEquals(2, result1.size());
        assertEquals(2, result2.size());


        assertTrue(result1.contains(resultDTO1));
        assertTrue(result1.contains(resultDTO2));
        assertTrue(result2.contains(resultDTO3));
        assertTrue(result2.contains(resultDTO4));
    }

    @Test
    void getResultsByParams_season_team() {

        // given
        BDDMockito.given(resultService.getAllResultsBySeasonAndTeam("test season1", "test team1"))
            .willReturn(Collections.singletonList(resultDTO1));
        BDDMockito.given(resultService.getAllResultsBySeasonAndTeam("test season1", "test team2"))
            .willReturn(Collections.singletonList(resultDTO2));
        BDDMockito.given(resultService.getAllResultsBySeasonAndTeam("test season2", "test team1"))
            .willReturn(Collections.singletonList(resultDTO3));
        BDDMockito.given(resultService.getAllResultsBySeasonAndTeam("test season2", "test team2"))
            .willReturn(Collections.singletonList(resultDTO4));

        // when
        List<ResultDTO> result1 = resultController.getResultsByParams(Optional.of("test team1"),
            Optional.empty(), Optional.of("test season1"));
        List<ResultDTO> result2 = resultController.getResultsByParams(Optional.of("test team2"),
            Optional.empty(), Optional.of("test season1"));
        List<ResultDTO> result3 = resultController.getResultsByParams(Optional.of("test team1"),
            Optional.empty(), Optional.of("test season2"));
        List<ResultDTO> result4 = resultController.getResultsByParams(Optional.of("test team2"),
            Optional.empty(), Optional.of("test season2"));

        // then
        assertEquals(1, result1.size());
        assertEquals(1, result2.size());
        assertEquals(1, result1.size());
        assertEquals(1, result2.size());

        assertTrue(result1.contains(resultDTO1));
        assertTrue(result2.contains(resultDTO2));
        assertTrue(result3.contains(resultDTO3));
        assertTrue(result4.contains(resultDTO4));
    }


    @Test
    void getResultsByParams_noParamThrowsError() {

        Optional<String> teamNameParam = Optional.empty();
        Optional<Integer> gameNumberParam = Optional.empty();
        Optional<String> seasonNameParam = Optional.empty();

        // then
        assertThrows(ApiRequestException.class, () ->
            resultController.getResultsByParams(
            teamNameParam, gameNumberParam, seasonNameParam));
    }
}