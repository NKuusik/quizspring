package com.quizstats.quizspring.service.user;

import com.quizstats.quizspring.controller.user.dto.UserDTO;
import com.quizstats.quizspring.mapper.UserMapper;
import com.quizstats.quizspring.repository.user.User;
import com.quizstats.quizspring.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserMapper.INSTANCE::toDTO).toList();
    }
}