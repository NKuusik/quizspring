package com.quizstats.quizspring.controller.result;

import com.quizstats.quizspring.controller.result.dto.ResultDTO;
import com.quizstats.quizspring.exception.ApiRequestException;
import com.quizstats.quizspring.service.result.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * Controller method for obtaining a list of ResultDTO-s based on request parameters.
     * @param teamNameOptional - name of the team to whom the result belongs to
     * @param gameNumberOptional - number of the game where the result took place
     * @param seasonNameOptional - name of the season where the result took place
     *
     * If several request parameters are used, the following order of priorities is used:
     *  1. season and team
     *  2. season
     *  3. team and game
     *  4. team
     *  5. game
     *
     *  Note that there is no separate query for the three parameters (season, team and game) together.
     *
     * If no request parameter is provided, ApiRequestException is thrown and HTTP request 422 returned.
     * This is done to avoid overly large queries that have no practical use.
     *
     * @return a list of ResultDTOs based on the request parameters.
     */
    @GetMapping
    List<ResultDTO> getResultsByParams(
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
