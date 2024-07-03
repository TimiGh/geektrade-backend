package com.geektrade.geektradebackend.repository;

import com.geektrade.geektradebackend.dto.User;
import com.geektrade.geektradebackend.entity.UserEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT * FROM users u WHERE u.username = ?1", nativeQuery = true)
    Optional<UserEntity> findUserByUsername(String username);

    UserEntity findByEmail(String email);

    Optional<UserEntity> findById(Long aLong);

    UserEntity findByCity(String city);

    UserEntity findFirstById(Long id);

}
