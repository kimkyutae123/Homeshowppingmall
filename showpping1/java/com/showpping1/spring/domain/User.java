package com.showpping1.spring.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_USER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id; // 사용자 번호 (PK)

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username; // 사용자 로그인 ID (고유해야 함)

    @Column(name = "password", nullable = false, length = 100)
    private String password; // 비밀번호 (보안을 위해 암호화 저장 필요)

    @Column(name = "name", nullable = false, length = 50)
    private String name; // 실제 사용자 이름

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    // 이메일이나 주소 등 추가 정보는 필요에 따라 추가
}