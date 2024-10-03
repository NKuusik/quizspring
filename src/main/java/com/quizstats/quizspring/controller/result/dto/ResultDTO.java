package com.quizstats.quizspring.controller.result.dto;

import com.quizstats.quizspring.controller.game.dto.GameDTO;
import com.quizstats.quizspring.controller.team.dto.TeamDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResultDTO {
    private Double resultPoints;
    private TeamDTO team;
    private GameDTO game;
}
