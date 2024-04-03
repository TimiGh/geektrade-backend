package com.geektrade.geektradebackend.entity;

import com.geektrade.geektradebackend.dto.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<ListingEntity> listings;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String county;

    @Column
    private String city;

    @Column
    private String street;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at = LocalDateTime.now();

//    @Column(name = "photo_id", nullable = false)
    @OneToOne
    private UserImageEntity userImage;

    public User toPojo() {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setCounty(county);
        user.setPhone(phone);
        user.setStreet(street);
        user.setCity(city);
        return user;
    }


}
