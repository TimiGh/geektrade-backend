package com.geektrade.geektradebackend.repository;

import com.geektrade.geektradebackend.entity.UserEntity;
import com.geektrade.geektradebackend.entity.UserImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserImageRepository extends JpaRepository<UserImageEntity, Long> {

Optional<UserImageEntity> findByUser(UserEntity user);
}
