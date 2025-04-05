package com.example.shoppingmall.repository;

import com.example.shoppingmall.entity.User; // entity 패키지의 User 클래스를 import
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // ✅ Optional로 수정
    Optional<User> findByEmail(String email);       // ✅ 같이 바꾸는 걸 추천
}