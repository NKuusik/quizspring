package com.quizstats.quizspring.controller.game;

import com.quizstats.quizspring.controller.game.dto.GameDTO;
import com.quizstats.quizspring.service.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;
    
    @GetMapping
    List<GameDTO> getAllGames(
        @RequestParam(name = "season", required = false) String seasonName) {
        if (seasonName == null) {
            return gameService.getAllGames();
        } else {
            return gameService.getAllGamesBySeason(seasonName);
        }
    }
}
