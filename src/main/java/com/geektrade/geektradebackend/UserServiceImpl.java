package com.geektrade.geektradebackend;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;

    public void findUser(Long id) {
        Optional<UserEntity> userOptional = userRepository.findById(id);
        userOptional.ifPresent(user -> System.out.println(user.getId()));
        UserEntity userEntity = userRepository.findFirstById(id);
        if (userEntity != null)
            return;
    }
}
