package com.geektrade.geektradebackend.controller;

import com.geektrade.geektradebackend.dto.UpdatePasswordDto;
import com.geektrade.geektradebackend.dto.User;
import com.geektrade.geektradebackend.dto.UserProfileUpdateDto;
import com.geektrade.geektradebackend.dto.UserSignupDto;
import com.geektrade.geektradebackend.entity.UserImageEntity;
import com.geektrade.geektradebackend.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping(path = "/signup")
    public User signup(@Validated @RequestBody UserSignupDto dto) {
        return userService.save(dto);
    }

    @PostMapping(path = "/login")
    public ResponseEntity login(@Validated @RequestBody UserSignupDto dto) {
        userService.checkUserExists(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/profile")
    @PreAuthorize("hasRole('ROLE_USER')")
    public User get() {
        return userService.getLoggedUser();
    }


    @PostMapping(path = "/profile-image")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Long setUserProfileImage(@Validated @RequestBody MultipartFile file) throws IOException {
        return userService.setProfileImage(file);
    }

    @PatchMapping(path = "/profile-info")
    @PreAuthorize("hasRole('ROLE_USER')")
    public User updateUserProfile(@Validated @RequestBody UserProfileUpdateDto dto) {
        return userService.update(dto);
    }

    @PatchMapping(path="/password")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity updatePassword(@Validated @RequestBody UpdatePasswordDto dto) {
        return userService.updatePassword(dto);
    }

    @GetMapping(path="/profile-image/{imageId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity getUserProfileImage(@PathVariable Long imageId) {
        String content = userService.getUserImageById(imageId);
        byte[] decode = Base64.getMimeDecoder().decode(content);
        return ResponseEntity
                .ok()
                .contentLength(decode.length)
                .contentType(MediaType.parseMediaType(new Tika().detect(decode)))
                .body(decode);
    }
}
