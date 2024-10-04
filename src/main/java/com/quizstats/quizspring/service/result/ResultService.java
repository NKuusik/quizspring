package com.quizstats.quizspring.service.result;

import com.quizstats.quizspring.controller.result.dto.ResultDTO;
import com.quizstats.quizspring.mapper.ResultMapper;
import com.quizstats.quizspring.repository.result.ResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    private final ResultRepository resultRepository;

    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public List<ResultDTO> getAllResultsByTeam(String teamName) {
        return ResultMapper.INSTANCE.toDTOList(resultRepository.findByTeam_Name(teamName));
    }

    public List<ResultDTO> getAllResultsByGame(Integer gameNumber) {
        return ResultMapper.INSTANCE.toDTOList(resultRepository.findByGame_GameNumber(gameNumber));
    }

    public List<ResultDTO> getAllResultsByTeamAndGame(String teamName, Integer gameNumber) {
        return ResultMapper.INSTANCE.toDTOList(resultRepository.findByTeam_NameAndGame_GameNumber(teamName, gameNumber));
    }

    public List<ResultDTO> getAllResultsBySeason(String seasonName) {
        return ResultMapper.INSTANCE.toDTOList(resultRepository.findByGame_Season_Name(seasonName));
    }
}
