package com.quizstats.quizspring.mapper;

import com.quizstats.quizspring.controller.result.dto.ResultDTO;
import com.quizstats.quizspring.repository.result.Result;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ResultMapper {
    ResultMapper INSTANCE = Mappers.getMapper(ResultMapper.class);

    ResultDTO toDTO(Result result);

    List<ResultDTO> toDTOList(List<Result> resultList);
}
