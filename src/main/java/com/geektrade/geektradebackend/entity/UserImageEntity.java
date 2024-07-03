package com.geektrade.geektradebackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Getter
@Setter
@Entity
@Table(name = "user_image")
public class UserImageEntity {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private UserEntity user;

    @Column(columnDefinition = "text")
    private String content;

//    public UserImageEntity create(MultipartFile file, UserEntity userEntity) throws IOException {
//
//        content =  ;
//        user = userEntity;
//
//        return this;
//    }
}
