package com.example.webback.web.api;


import com.example.webback.business.service.UserService;
import com.example.webback.web.api.dto.create.CreateUserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UUID register(@RequestBody CreateUserDto user) {
        return userService.register(user);
    }
}
