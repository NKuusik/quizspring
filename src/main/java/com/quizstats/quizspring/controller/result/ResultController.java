package com.quizstats.quizspring.controller.result;

import com.quizstats.quizspring.controller.result.dto.ResultDTO;
import com.quizstats.quizspring.exception.ApiRequestException;
import com.quizstats.quizspring.service.result.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/result")
public class ResultController {

    private final ResultService resultService;

    @Autowired
    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping
    List<ResultDTO> getAllResults(
        @RequestParam(name= "team") Optional<String> teamNameOptional,
        @RequestParam(name= "game") Optional<Integer> gameNumberOptional,
        @RequestParam(name="season") Optional<String> seasonNameOptional) {
        if (seasonNameOptional.isPresent() && teamNameOptional.isPresent()) {
            return getAllResultsBySeasonAndTeam(seasonNameOptional.get(), teamNameOptional.get());
        } else if (seasonNameOptional.isPresent()) {
            return getAllResultsBySeason(seasonNameOptional.get());
        } else if (teamNameOptional.isPresent() && gameNumberOptional.isPresent()) {
            return getAllResultsByTeamAndGame(teamNameOptional.get(), gameNumberOptional.get());
        } else if (teamNameOptional.isPresent()) {
            return getAllResultsByTeam(teamNameOptional.get());
        } else if (gameNumberOptional.isPresent()) {
            return getAllResultsByGame(gameNumberOptional.get());
        } else {

            throw new ApiRequestException("Request needs to have one of the following parameters: team, game, season");
        }
    }


    private List<ResultDTO> getAllResultsBySeasonAndTeam(String seasonName, String teamName) {
        return resultService.getAllResultsBySeasonAndTeam(seasonName, teamName);
    }

    private List<ResultDTO> getAllResultsBySeason(String seasonName) {
        return resultService.getAllResultsBySeason(seasonName);
    }

    private List<ResultDTO> getAllResultsByTeamAndGame(String teamName, Integer gameNumber) {
        return resultService.getAllResultsByTeamAndGame(teamName, gameNumber);
    }

    private List<ResultDTO> getAllResultsByTeam(String teamName) {
        return resultService.getAllResultsByTeam(teamName);
    }

    private List<ResultDTO> getAllResultsByGame(Integer gameNumber) {
        return resultService.getAllResultsByGame(gameNumber);
    }
}
