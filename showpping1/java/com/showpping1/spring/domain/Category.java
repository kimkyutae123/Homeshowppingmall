package com.showpping1.spring.domain;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany; // 추가된 import
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_CATEGORY") // ⭐️ DB 테이블 이름: T_CATEGORY
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id; // 카테고리 번호 (PK)

    @Column(name = "category_name", nullable = false, unique = true, length = 50)
    private String name; // 카테고리 이름 (예: 상의, 신발, 니트/스웨터)

    // 상위 카테고리 ID를 참조하여 계층 구조 생성 (Self-Referencing FK)
    @Column(name = "parent_id")
    private Long parentId; // 상위 카테고리 번호 (NULL이면 최상위 카테고리)
    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
}