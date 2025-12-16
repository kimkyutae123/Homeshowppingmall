package com.showpping1.spring.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_PRODUCT") // ⭐️ DB 테이블 이름을 T_PRODUCT로 지정
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id; // 상품번호 (PK)

//    // ⭐️ Category 테이블의 ID를 참조하는 외래 키 (FK)
//    // 실제 JPA에서는 객체 간의 관계로 매핑되지만, 일단 ID만 필드로 정의합니다.
//    @Column(name = "category_id")
//    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY) // 여러 상품(Many)이 하나의 카테고리(One)에 속함
    @JoinColumn(name = "category_id") // DB의 외래 키 컬럼 이름
    private Category category; // Category 객체로 변경
    
    @Column(name = "name", nullable = false, length = 100)
    private String name; // 상품 이름

    @Column(name = "selling_price", nullable = false)
    private Integer price; // 가격 (정수형)

    @Column(name = "image_url", length = 255)
    private String imageUrl; // 대표 이미지 URL

    @Column(name = "is_sold_out")
    private Boolean isSoldOut = false; // 품절 여부 (기본값: false)

    @Column(name = "view_count")
    private Integer viewCount = 0; // 조회수 (기본값: 0)

    @Column(name = "reg_date", updatable = false)
    private LocalDateTime regDate = LocalDateTime.now(); // 등록일자 (최초 등록 시 자동 생성)
    // ⭐️ [OneToMany] 옵션 관계 설정
    // 상품 하나(One)는 여러 옵션(Many)을 가짐
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOption> options = new ArrayList<>();
    // 참고: 재고 수량은 ProductOption 엔티티에서 관리하는 것이 효율적입니다.
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Boolean getIsSoldOut() {
		return isSoldOut;
	}
	public void setIsSoldOut(Boolean isSoldOut) {
		this.isSoldOut = isSoldOut;
	}
	public Integer getViewCount() {
		return viewCount;
	}
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}
	public LocalDateTime getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}
	public List<ProductOption> getOptions() {
		return options;
	}
	public void setOptions(List<ProductOption> options) {
		this.options = options;
	}

}