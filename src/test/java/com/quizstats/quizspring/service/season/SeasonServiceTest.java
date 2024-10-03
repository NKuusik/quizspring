package com.quizstats.quizspring.service.season;

import com.quizstats.quizspring.controller.season.dto.SeasonDTO;
import com.quizstats.quizspring.mapper.SeasonMapper;
import com.quizstats.quizspring.repository.season.Season;
import com.quizstats.quizspring.repository.season.SeasonRepository;
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
class SeasonServiceTest {

    Season season1 = new Season(1, "test season1");
    Season season2 = new Season(2, "test season2");

    SeasonDTO seasonDTO1 = SeasonMapper.INSTANCE.toDTO(season1);
    SeasonDTO seasonDTO2 = SeasonMapper.INSTANCE.toDTO(season2);

    @Mock
    SeasonRepository seasonRepository;

    @InjectMocks
    SeasonService seasonService;

    @Test
    void getAllSeasons() {
         // given
        BDDMockito.given(seasonRepository.findAll()).willReturn(Arrays.asList(season1, season2));

        // when
        List<SeasonDTO> result = seasonService.getAllSeasons();

        // then
        assertEquals(2, result.size());
        assertTrue(result.contains(seasonDTO1));
        assertTrue(result.contains(seasonDTO2));
    }

    @Test
    void getSeasonByName() {

        // given
        BDDMockito.given(seasonRepository.findByName("test season1")).willReturn(Optional.of(season1));
        BDDMockito.given(seasonRepository.findByName("test season2")).willReturn(Optional.of(season2));

        // when
        SeasonDTO result1 = seasonService.getSeasonByName("test season1");
        SeasonDTO result2 = seasonService.getSeasonByName("test season2");

        // then
        assertEquals(seasonDTO1, result1);
        assertEquals(seasonDTO2, result2);
    }
}