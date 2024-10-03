package com.quizstats.quizspring.controller.game.dto;

import com.quizstats.quizspring.controller.season.dto.SeasonDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GameDTO {
    private Integer gameNumber;
    private SeasonDTO season;
}
