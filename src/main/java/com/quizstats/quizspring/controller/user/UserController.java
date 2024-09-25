package com.quizstats.quizspring.controller.user;

import com.quizstats.quizspring.controller.user.dto.UserDTO;
import com.quizstats.quizspring.repository.user.User;
import com.quizstats.quizspring.repository.user.UserRepository;
import com.quizstats.quizspring.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    List<UserDTO> findAllUsers() {
        return userService.getAllUsers();
    }

}