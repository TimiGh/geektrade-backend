package com.geektrade.geektradebackend.service;

import com.geektrade.geektradebackend.dto.UpdatePasswordDto;
import com.geektrade.geektradebackend.dto.User;
import com.geektrade.geektradebackend.dto.UserProfileUpdateDto;
import com.geektrade.geektradebackend.dto.UserSignupDto;
import com.geektrade.geektradebackend.entity.UserEntity;
import com.geektrade.geektradebackend.entity.UserImageEntity;
import com.geektrade.geektradebackend.exception.CredentialsDoNotMatchException;
import com.geektrade.geektradebackend.exception.UserAlreadyExistsException;
import com.geektrade.geektradebackend.exception.UserNotFoundForIdException;
import com.geektrade.geektradebackend.repository.UserImageRepository;
import com.geektrade.geektradebackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserImageRepository userImageRepository;


    private final PasswordEncoder passwordEncoder;

    public UserEntity findByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public UserEntity findByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null)
            throw new UsernameNotFoundException(email);
        return userEntity;
    }


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
        UserEntity entity = userRepository.findUserByUsername(dto.getEmail()).orElseThrow(() -> new UsernameNotFoundException(dto.getEmail()));
        if (entity != null)
            throw new UserAlreadyExistsException(dto.getEmail());
        UserEntity userEntity = new UserEntity();
        userEntity.setName(dto.getEmail());
        userEntity.setEmail(dto.getEmail());
        userEntity.setRole("USER");
        userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));

        return userRepository.save(userEntity).toPojo();
    }

    public UserEntity getUserEntity() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public void checkUserExists(UserSignupDto dto) {
        UserEntity userEntity = findByUsername(dto.getEmail());
        if (!passwordEncoder.matches(dto.getPassword(), userEntity.getPassword()))
            throw new CredentialsDoNotMatchException(dto.getEmail());
    }

    public User get(String username) {
        return findByUsername(username).toPojo();
    }

    @Transactional
    public Long setProfileImage(MultipartFile file) throws IOException {
        UserImageEntity imgEntity = userImageRepository.findByUser(findByUsername(getLoggedUserName())).orElse(new UserImageEntity());
        UserEntity user = userRepository.findUserByUsername(getLoggedUserName()).orElseThrow(() -> new UsernameNotFoundException("asd"));;

        imgEntity.setUser(user);
        imgEntity.setContent(Base64.getMimeEncoder().encodeToString(file.getBytes()));

        user.setUserImage(imgEntity);
        userRepository.save(user);

        return userImageRepository.save(imgEntity).getId();
    }

    public User update(UserProfileUpdateDto dto) {
        UserEntity userEntity = findByEmail(dto.getEmail());
        UserEntity updatedUser = userEntity.update(dto);
        return userRepository.save(updatedUser).toPojo();
    }

    public String getLoggedUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public User getLoggedUser() {
        return get(getLoggedUserName());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = findByUsername(username);
        return new org.springframework.security.core.userdetails.User(username,
                entity.getPassword(),
                mapRolesToAuthorities("ROLE_USER")
                );
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(String role) {
        return Stream.of(role).map(roleEnum -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
    }

    @Transactional
    public ResponseEntity updatePassword(UpdatePasswordDto dto) {
        UserEntity entity = userRepository.findUserByUsername(getLoggedUserName()).orElseThrow(() -> new UsernameNotFoundException(getLoggedUserName()));
        String currentPassword = entity.getPassword();

        if (!passwordEncoder.matches(dto.getOldPassword(), currentPassword)){
            throw new CredentialsDoNotMatchException(dto.getOldPassword());
        }

        entity.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(entity);
        return ResponseEntity.ok().build();
    }

    public String getUserImageById(Long imageId) {
        UserImageEntity image = userImageRepository.findById(imageId).orElseThrow(() -> new UsernameNotFoundException("asd"));
        return image.getContent();
    }
}
