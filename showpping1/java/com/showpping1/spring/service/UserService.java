package com.showpping1.spring.service;

import com.showpping1.spring.domain.User;
import com.showpping1.spring.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional(readOnly = true) // 조회 시 성능 최적화
public class UserService {

    private final UserRepository userRepository;
    // 실제 구현 시 BCryptPasswordEncoder 등을 주입받아야 함
    // private final PasswordEncoder passwordEncoder; 

    public UserService(UserRepository userRepository /*, PasswordEncoder passwordEncoder*/) {
        this.userRepository = userRepository;
        // this.passwordEncoder = passwordEncoder;
    }

    /**
     * 회원가입을 처리하고 새로운 사용자를 저장합니다.
     */
    @Transactional
    public User registerUser(User user) {
        // 1. 중복 사용자 ID 확인
        validateDuplicateUser(user.getUsername());
        
        // 2. 비밀번호 암호화 (지금은 임시로 평문 저장)
        // user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 3. 저장
        return userRepository.save(user);
    }

    private void validateDuplicateUser(String username) {
        userRepository.findByUsername(username)
            .ifPresent(u -> {
                throw new IllegalStateException("이미 존재하는 사용자 ID입니다: " + username);
            });
    }

    /**
     * 로그인 인증을 시도합니다.
     * (실제 Spring Security를 사용하면 이 로직은 필요 없습니다.)
     */
    public Optional<User> authenticate(String username, String rawPassword) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // ⭐️ 비밀번호 비교 (실제로는 암호화된 비밀번호를 비교해야 함)
            if (user.getPassword().equals(rawPassword)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
    
    // 사용자 ID로 User 객체 조회
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}