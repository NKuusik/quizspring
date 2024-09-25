package com.quizstats.quizspring.mapper;

import com.quizstats.quizspring.controller.user.dto.UserDTO;
import com.quizstats.quizspring.repository.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDTO toDTO(User user);

    List<UserDTO> toDTOList(List<User> bookingList);

}
