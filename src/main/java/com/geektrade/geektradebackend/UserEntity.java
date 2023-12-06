package com.geektrade.geektradebackend;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(mappedBy = "user")
    private List<PostEntity> posts;
}
