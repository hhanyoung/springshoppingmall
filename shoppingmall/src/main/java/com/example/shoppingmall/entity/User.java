package com.example.shoppingmall.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")  // 테이블 이름 설정
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 기본키

    @Column(nullable = false, unique = true)
    private String username;  // 사용자 이름

    @Column(nullable = false)
    private String password;  // 비밀번호

    @Column(nullable = false, unique = true)
    private String email;  // 이메일 추가 ✅

    @Column(nullable = false) // 사용자 역할 (ROLE_USER, ROLE_ADMIN)
    private String role;

    // 기본 생성자
    public User() {} // 🔥 JPA에서 엔티티를 생성할 때 기본 생성자가 필요함!

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = "ROLE_USER";


    }

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // 🔥 여기 추가!
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}

