package com.quizstats.quizspring.controller.result;

import com.quizstats.quizspring.controller.result.dto.ResultDTO;
import com.quizstats.quizspring.service.result.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
        @RequestParam(name= "team", required = false) String teamName,
        @RequestParam(name= "game", required = false) Integer gameNumber) {
        if (teamName != null) {
            return getAllResultsByTeam(teamName);
        } else if (gameNumber != null) {
            return getAllResultsByGame(gameNumber);
        } else {
            // Todo: throw error instead.
            return new ArrayList<>();
        }
    }

    private List<ResultDTO> getAllResultsByTeam(String teamName) {
        return resultService.getAllResultsByTeam(teamName);
    }

        private List<ResultDTO> getAllResultsByGame(Integer gameNumber) {
        return resultService.getAllResultsByGame(gameNumber);
    }
}
