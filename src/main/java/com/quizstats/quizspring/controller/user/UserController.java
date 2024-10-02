package com.quizstats.quizspring.controller.user;

import com.quizstats.quizspring.controller.user.dto.UserDTO;
import com.quizstats.quizspring.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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