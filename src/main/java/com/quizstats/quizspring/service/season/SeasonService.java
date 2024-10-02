package com.quizstats.quizspring.service.season;

import com.quizstats.quizspring.controller.season.dto.SeasonDTO;
import com.quizstats.quizspring.mapper.SeasonMapper;
import com.quizstats.quizspring.repository.season.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeasonService {

    @Autowired
    private SeasonRepository seasonRepository;

    public List<SeasonDTO> getAllSeasons() {
        return seasonRepository.findAll().stream().map(SeasonMapper.INSTANCE::toDTO).toList();
    }


    public SeasonDTO getSeasonByName(String name) {
        return seasonRepository.findByName(name).map(SeasonMapper.INSTANCE::toDTO)
            .orElseThrow(() -> new RuntimeException("Season not found with name: "+ name));
    }
}
