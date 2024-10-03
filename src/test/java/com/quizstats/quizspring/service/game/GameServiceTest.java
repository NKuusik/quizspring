package com.quizstats.quizspring.service.game;

import com.quizstats.quizspring.controller.game.dto.GameDTO;
import com.quizstats.quizspring.mapper.GameMapper;
import com.quizstats.quizspring.repository.game.Game;
import com.quizstats.quizspring.repository.game.GameRepository;
import com.quizstats.quizspring.repository.season.Season;
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
class GameServiceTest {

    Season season1 = new Season(1, "test season1");
    Season season2 = new Season(2, "test season2");
    Game game1 = Game.builder().gameNumber(1).id(1).season(season1).build();
    Game game2 = Game.builder().gameNumber(2).id(2).season(season2).build();

    GameDTO gameDTO1 = GameMapper.INSTANCE.toDTO(game1);
    GameDTO gameDTO2 = GameMapper.INSTANCE.toDTO(game2);

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;

    @Test
    void getAllGames() {

        // given
        BDDMockito.given(gameRepository.findAll()).willReturn(Arrays.asList(game1, game2));

        // when
        List<GameDTO> result = gameService.getAllGames();

        // then
        assertEquals(2, result.size());
        assertTrue(result.contains(gameDTO1));
        assertTrue(result.contains(gameDTO2));

    }

    @Test
    void getAllGamesBySeason() {

        // given
        BDDMockito.given(gameRepository.findBySeason_Name("test season1")).willReturn(Collections.singletonList(game1));
        BDDMockito.given(gameRepository.findBySeason_Name("test season2")).willReturn(Collections.singletonList(game2));

        // when
        List<GameDTO> result1 = gameService.getAllGamesBySeason("test season1");
        List<GameDTO> result2 = gameService.getAllGamesBySeason("test season2");

        // then
        assertEquals(1, result1.size());
        assertEquals(1, result2.size());
        assertTrue(result1.contains(gameDTO1));
        assertTrue(result2.contains(gameDTO2));
    }
}