package com.showpping1.spring.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne; // 추가된 import
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_PRODUCT_OPTION") // ⭐️ DB 테이블 이름: T_PRODUCT_OPTION
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private Long id; // 옵션 번호 (PK)

    @ManyToOne(fetch = FetchType.LAZY) // 여러 옵션(Many)이 하나의 상품(One)에 속함
    @JoinColumn(name = "product_id", nullable = false) // DB의 외래 키 컬럼 이름
    private Product product; // Product 객체로 변경

    @Column(name = "size_name", nullable = false, length = 20)
    private String sizeName; // 사이즈 이름 (예: S, L, 270)

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity; // 재고 수량

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
}