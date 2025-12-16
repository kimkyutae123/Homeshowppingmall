package com.showpping1.spring.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_CART_ITEM") // ⭐️ DB 테이블 이름: T_CART_ITEM
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 아이디값이 자동으로 하나씩 올라가게 만들어주는 어노케이션 
    @Column(name = "cart_item_id")
    private Long id; // 장바구니 항목 ID (PK)

    // ⭐️ [FK] 장바구니 항목은 하나의 옵션에 연결됨 (N:1)
    // 장바구니에는 상품 자체가 아니라 '옵션'이 담겨야 가격/재고 추적이 용이함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id", nullable = false)
    private ProductOption productOption; 

    // ⭐️ [FK] 사용자 ID (User 엔티티가 있다고 가정)
    // 실제로는 User 엔티티와 ManyToOne 매핑을 해야 하지만, 임시로 ID만 사용
    @Column(name = "user_id", nullable = false)
    private Long userId; 
    
    // 장바구니에 담긴 수량
    @Column(name = "quantity", nullable = false)
    private int quantity; 
    
    // --- (Lombok이 없다면 Getter/Setter 필요) ---

    // 편의 메서드: 수량 증가/감소
    public void addQuantity(int count) {
        this.quantity += count;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductOption getProductOption() {
		return productOption;
	}

	public void setProductOption(ProductOption productOption) {
		this.productOption = productOption;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


}