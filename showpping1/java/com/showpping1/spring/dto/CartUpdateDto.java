package com.showpping1.spring.dto; 

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

//Data Transfer Object의 약자로, 데이터를 전달하기 위한 객체
//DTO는 로직을 가지지 않는 순수한 데이터 객체(getter & setter 만 가진 클래스).
//여러 레이어(Layer)간 데이터를 주고 받을 때 사용할 수 있고 주로 View와 Controller 사이에서 활용.
//DTO는 getter / setter 메소드를 포함한다. 하지만, 이외의 다른 비즈니스 로직은 포함하지 않는다.
//DTO는 어떻게 구현하느냐에 따라 가변 객체로 활용할 수도 있고 불변 객체로 활용할 수도 있다.
//DTO는 데이터 전달 만을 위한 객체라는 게 핵심 정의이다. 그렇기 때문에 완전히 데이터 전달 용도로만 사용하는게 목적이라면, getter/setter만 필요하지, 이외의 비즈니스 로직은 굳이 있을 필요가 없다.

public class CartUpdateDto {
    private Long cartItemId; // 업데이트할 장바구니 항목의 ID (CartItem.id)
    private int quantity;    // 변경할 새로운 수량
	public Long getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(Long cartItemId) {
		this.cartItemId = cartItemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}