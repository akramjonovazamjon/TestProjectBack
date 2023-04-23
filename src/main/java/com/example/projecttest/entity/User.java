package com.example.projecttest.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public UserDetails asDetailedUser() {
        var authority = new SimpleGrantedAuthority(role.name());
        return new org.springframework.security.core.userdetails.User(username, password, true, true, true, true, List.of(authority));
    }

    public static User of(String name, String username, String password) {
        return User.builder()
                .name(name)
                .username(username)
                .password(password)
                .role(UserRole.USER)
                .build();
    }
}
