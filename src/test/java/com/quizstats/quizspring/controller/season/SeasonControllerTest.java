package com.quizstats.quizspring.controller.season;

import com.quizstats.quizspring.controller.season.dto.SeasonDTO;
import com.quizstats.quizspring.service.season.SeasonService;
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
class SeasonControllerTest {

    SeasonDTO seasonDTO1 = new SeasonDTO("test season1");
    SeasonDTO seasonDTO2 = new SeasonDTO("test season2");

    @Mock
    private SeasonService seasonService;

    @InjectMocks
    private SeasonController seasonController;

    @Test
    void getAllSeasons() {

        // given
        BDDMockito.given(seasonService.getAllSeasons()).willReturn(Arrays.asList(seasonDTO1, seasonDTO2));

        // when
        List<SeasonDTO> result = seasonController.getAllSeasons();

        // then
        assertEquals(2, result.size());
        assertTrue(result.contains(seasonDTO1));
        assertTrue(result.contains(seasonDTO2));

    }

    @Test
    void getSeasonByName() {

        // given
        BDDMockito.given(seasonService.getSeasonByName("test season1")).willReturn(seasonDTO1);

        // when
        SeasonDTO result = seasonController.getSeasonByName("test season1");

        // then
        assertEquals(seasonDTO1, result);
    }
}