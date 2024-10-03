package com.quizstats.quizspring.controller.game;

import com.quizstats.quizspring.controller.game.dto.GameDTO;
import com.quizstats.quizspring.controller.season.dto.SeasonDTO;
import com.quizstats.quizspring.service.game.GameService;
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
class GameControllerTest {

    SeasonDTO seasonDTO1 = new SeasonDTO("test season1");
    SeasonDTO seasonDTO2 = new SeasonDTO("test season2");

    GameDTO gameDTO1 = GameDTO.builder()
        .gameNumber(1).season(seasonDTO1).build();

    GameDTO gameDTO2 = GameDTO.builder()
        .gameNumber(2).season(seasonDTO2).build();

    @Mock
    private GameService gameService;

    @InjectMocks
    private GameController gameController;

    @Test
    void getAllGames_noSeasonName() {

        // given
        BDDMockito.given(gameService.getAllGames()).willReturn(Arrays.asList(gameDTO1, gameDTO2));

        // when
        List<GameDTO> result = gameController.getAllGames(null);

        // then
        assertEquals(2, result.size());
        assertTrue(result.contains(gameDTO1));
        assertTrue(result.contains(gameDTO2));
    }

        @Test
    void getAllGames_seasonNameProvided() {

        // given
        BDDMockito.given(gameService.getAllGamesBySeason("test season1")).willReturn(Collections.singletonList(gameDTO1));
        BDDMockito.given(gameService.getAllGamesBySeason("test season2")).willReturn(Collections.singletonList(gameDTO2));

        // when
        List<GameDTO> result1 = gameController.getAllGames("test season1");
        List<GameDTO> result2 = gameController.getAllGames("test season2");

        // then
        assertEquals(1, result1.size());
        assertEquals(1, result2.size());
        assertTrue(result1.contains(gameDTO1));
        assertTrue(result2.contains(gameDTO2));
    }
}