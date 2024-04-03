package com.geektrade.geektradebackend.controller;

import com.geektrade.geektradebackend.dto.User;
import com.geektrade.geektradebackend.dto.UserSignupDto;
import com.geektrade.geektradebackend.repository.UserRepository;
import com.geektrade.geektradebackend.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping(path = "/signup")
    public User signup(@Validated @RequestBody UserSignupDto dto) { return userService.save(dto);}
}
