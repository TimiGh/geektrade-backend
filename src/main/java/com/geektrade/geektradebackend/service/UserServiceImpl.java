package com.geektrade.geektradebackend.service;

import com.geektrade.geektradebackend.entity.UserEntity;
import com.geektrade.geektradebackend.dto.User;
import com.geektrade.geektradebackend.dto.UserSignupDto;
import com.geektrade.geektradebackend.exception.UserAlreadyExistsException;
import com.geektrade.geektradebackend.exception.UserNotFoundForIdException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.geektrade.geektradebackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserServiceImpl {

    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;


    public UserEntity findUser(Long id) {
        Optional<UserEntity> userOptional = userRepository.findById(id);
        userOptional.ifPresent(user -> System.out.println(user.getId()));
        UserEntity userEntity = userRepository.findFirstById(id);
        if (userEntity != null)
            throw new UserNotFoundForIdException(id);
        return userEntity;
    }

    @Transactional
    public User save(UserSignupDto dto) {
        UserEntity entity = userRepository.findUserByUsername(dto.getEmail());
        if (entity != null)
            throw new UserAlreadyExistsException(dto.getEmail());
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(dto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));

        return userRepository.save(userEntity).toPojo();
    }
}
