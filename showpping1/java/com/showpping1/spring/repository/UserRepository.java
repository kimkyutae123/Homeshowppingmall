package com.showpping1.spring.repository;

import com.showpping1.spring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 사용자 로그인 ID(username)로 User 엔티티를 조회합니다.
     * 로그인 및 중복 확인 시 사용됩니다.
     */
    Optional<User> findByUsername(String username);
}