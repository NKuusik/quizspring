package com.quizstats.quizspring.repository.game;

import com.quizstats.quizspring.repository.season.Season;
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
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Integer id;
    private Integer gameNumber;

    @ManyToOne
    @JoinColumn(name="season_key", referencedColumnName = "season_id")
    private Season season;
}
