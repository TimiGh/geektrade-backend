package com.geektrade.geektradebackend.entity;

import com.geektrade.geektradebackend.dto.User;
import com.geektrade.geektradebackend.dto.UserProfileUpdateDto;
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
    private String name;

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

    @Column(nullable = false)
    private String role;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at = LocalDateTime.now();

    @OneToOne
    private UserImageEntity userImage;

    public User toPojo() {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setCounty(county);
        user.setPhone(phone);
        user.setStreet(street);
        user.setCity(city);
        user.setRole(role);
        user.setCreatedAt(created_at);
        if (userImage != null) {
            user.setProfileImageKey(userImage.getId());
        }
        return user;
    }

    public UserEntity update(UserProfileUpdateDto dto) {
        name = dto.getName();
        county = dto.getCounty();
        city = dto.getCity();
        phone = dto.getPhone();
        street = dto.getStreet();
        return this;
    }
}
