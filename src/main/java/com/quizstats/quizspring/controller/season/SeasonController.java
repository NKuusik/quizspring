package com.quizstats.quizspring.controller.season;

import com.quizstats.quizspring.controller.season.dto.SeasonDTO;
import com.quizstats.quizspring.service.season.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/season")
public class SeasonController {

    @Autowired
    private SeasonService seasonService;

    @GetMapping
    List<SeasonDTO> getAllSeasons() {
        return seasonService.getAllSeasons();
    }

    @GetMapping("/{seasonName}")
    SeasonDTO getSeasonByName(@PathVariable String seasonName) {
        return seasonService.getSeasonByName(seasonName);
    }
}
