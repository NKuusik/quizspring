package com.quizstats.quizspring.service.result;

import com.quizstats.quizspring.controller.result.dto.ResultDTO;
import com.quizstats.quizspring.mapper.ResultMapper;
import com.quizstats.quizspring.repository.game.Game;
import com.quizstats.quizspring.repository.result.Result;
import com.quizstats.quizspring.repository.result.ResultRepository;
import com.quizstats.quizspring.repository.season.Season;
import com.quizstats.quizspring.repository.team.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ResultServiceTest {

    Team team1 = new Team(1, "test team1");
    Team team2 = new Team(2, "test team2");

    Season season1 = new Season(1, "test season1");
    Season season2 = new Season(2, "test season2");

    Game game1 = Game.builder().gameNumber(1).id(1).season(season1).build();
    Game game2 = Game.builder().gameNumber(2).id(2).season(season2).build();

    Result result1 = Result.builder()
        .id(1).resultPoints(5.0).team(team1).game(game1).build();

    Result result2 = Result.builder()
        .id(1).resultPoints(10.0).team(team2).game(game1).build();

    Result result3 = Result.builder()
        .id(1).resultPoints(7.0).team(team1).game(game2).build();

    Result result4 = Result.builder()
        .id(1).resultPoints(1.0).team(team1).game(game2).build();

    ResultDTO resultDTO1 = ResultMapper.INSTANCE.toDTO(result1);
    ResultDTO resultDTO2 = ResultMapper.INSTANCE.toDTO(result2);
    ResultDTO resultDTO3 = ResultMapper.INSTANCE.toDTO(result3);
    ResultDTO resultDTO4 = ResultMapper.INSTANCE.toDTO(result4);

    @Mock
    private ResultRepository resultRepository;

    @InjectMocks
    private ResultService resultService;

    @Test
    void getAllResultsByTeam() {

        // given
        BDDMockito.given(resultRepository.findByTeam_Name("test team1")).willReturn(Arrays.asList(result1, result3));
        BDDMockito.given(resultRepository.findByTeam_Name("test team2")).willReturn(Arrays.asList(result2, result4));

        // when
        List<ResultDTO> testResult1 = resultService.getAllResultsByTeam("test team1");
        List<ResultDTO> testResult2 =  resultService.getAllResultsByTeam("test team2");

        // then
        assertEquals(2, testResult1.size());
        assertEquals(2, testResult2.size());
        assertTrue(testResult1.contains(resultDTO1));
        assertTrue(testResult1.contains(resultDTO3));
        assertTrue(testResult2.contains(resultDTO2));
        assertTrue(testResult2.contains(resultDTO4));
    }

    @Test
    void getAllResultsByGame() {

        // given
        BDDMockito.given(resultRepository.findByGame_GameNumber(1)).willReturn(Arrays.asList(result1, result2));
        BDDMockito.given(resultRepository.findByGame_GameNumber(2)).willReturn(Arrays.asList(result3, result4));

        // when
        List<ResultDTO> testResult1 = resultService.getAllResultsByGame(1);
        List<ResultDTO> testResult2 = resultService.getAllResultsByGame(2);

        // then
        assertEquals(2, testResult1.size());
        assertEquals(2, testResult2.size());
        assertTrue(testResult1.contains(resultDTO1));
        assertTrue(testResult1.contains(resultDTO2));
        assertTrue(testResult2.contains(resultDTO3));
        assertTrue(testResult2.contains(resultDTO4));
    }

    @Test
    void getAllResultsByTeamAndGame() {

        // given
        BDDMockito.given(resultRepository.findByTeam_NameAndGame_GameNumber("test team1", 1))
            .willReturn(Collections.singletonList(result1));
        BDDMockito.given(resultRepository.findByTeam_NameAndGame_GameNumber("test team2", 1))
            .willReturn(Collections.singletonList(result2));
        BDDMockito.given(resultRepository.findByTeam_NameAndGame_GameNumber("test team1", 2))
            .willReturn(Collections.singletonList(result3));
        BDDMockito.given(resultRepository.findByTeam_NameAndGame_GameNumber("test team2", 2))
            .willReturn(Collections.singletonList(result4));

        // when
        List<ResultDTO> testResult1 = resultService.getAllResultsByTeamAndGame("test team1", 1);
        List<ResultDTO> testResult2 = resultService.getAllResultsByTeamAndGame("test team2", 1);
        List<ResultDTO> testResult3 = resultService.getAllResultsByTeamAndGame("test team1", 2);
        List<ResultDTO> testResult4 = resultService.getAllResultsByTeamAndGame("test team2", 2);

        // then
        assertEquals(1, testResult1.size());
        assertEquals(1, testResult2.size());
        assertEquals(1, testResult3.size());
        assertEquals(1, testResult4.size());

        assertTrue(testResult1.contains(resultDTO1));
        assertTrue(testResult2.contains(resultDTO2));
        assertTrue(testResult3.contains(resultDTO3));
        assertTrue(testResult4.contains(resultDTO4));
    }

    @Test
    void getAllResultsBySeason() {

        // given
        BDDMockito.given(resultRepository.findByGame_Season_Name("test season1"))
            .willReturn(Arrays.asList(result1, result2));
        BDDMockito.given(resultRepository.findByGame_Season_Name("test season2"))
            .willReturn(Arrays.asList(result3, result4));

        // when
        List<ResultDTO> testResult1 = resultService.getAllResultsBySeason("test season1");
        List<ResultDTO> testResult2 = resultService.getAllResultsBySeason("test season2");

        // then
        assertEquals(2, testResult1.size());
        assertEquals(2, testResult2.size());


        assertTrue(testResult1.contains(resultDTO1));
        assertTrue(testResult1.contains(resultDTO2));
        assertTrue(testResult2.contains(resultDTO3));
        assertTrue(testResult2.contains(resultDTO4));
    }

    @Test
    void getAllResultsBySeasonAndTeam() {

        // given
        BDDMockito.given(resultRepository.findByGame_Season_NameAndTeam_Name("test season1", "test team1"))
            .willReturn(Collections.singletonList(result1));
        BDDMockito.given(resultRepository.findByGame_Season_NameAndTeam_Name("test season1", "test team2"))
            .willReturn(Collections.singletonList(result2));
        BDDMockito.given(resultRepository.findByGame_Season_NameAndTeam_Name("test season2", "test team1"))
            .willReturn(Collections.singletonList(result3));
        BDDMockito.given(resultRepository.findByGame_Season_NameAndTeam_Name("test season2", "test team2"))
            .willReturn(Collections.singletonList(result4));

        // when
        List<ResultDTO> testResult1 = resultService
            .getAllResultsBySeasonAndTeam("test season1", "test team1");
        List<ResultDTO> testResult2 = resultService
            .getAllResultsBySeasonAndTeam("test season1", "test team2");
        List<ResultDTO> testResult3 = resultService
            .getAllResultsBySeasonAndTeam("test season2", "test team1");
        List<ResultDTO> testResult4 = resultService
            .getAllResultsBySeasonAndTeam("test season2", "test team2");

        // then
        assertEquals(1, testResult1.size());
        assertEquals(1, testResult2.size());
        assertEquals(1, testResult3.size());
        assertEquals(1, testResult4.size());

        assertTrue(testResult1.contains(resultDTO1));
        assertTrue(testResult2.contains(resultDTO2));
        assertTrue(testResult3.contains(resultDTO3));
        assertTrue(testResult4.contains(resultDTO4));
    }
}