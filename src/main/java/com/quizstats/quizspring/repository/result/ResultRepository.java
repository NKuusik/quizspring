package com.quizstats.quizspring.repository.result;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {
    List<Result> findByTeam_Name(String teamName);
    List<Result> findByGame_GameNumber(Integer gameNumber);
}
