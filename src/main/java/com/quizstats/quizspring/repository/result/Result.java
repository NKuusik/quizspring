package com.quizstats.quizspring.repository.result;

import com.quizstats.quizspring.repository.game.Game;
import com.quizstats.quizspring.repository.team.Team;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Integer id;

    @Column(name = "result")
    private Double resultPoints;

    @ManyToOne
    @JoinColumn(name="team_key", referencedColumnName = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "game_key", referencedColumnName = "game_id")
    private Game game;

}
