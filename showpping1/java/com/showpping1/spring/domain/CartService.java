package com.showpping1.spring.domain;

import com.showpping1.spring.domain.CartItem;
import java.util.List;

public interface CartService {

    /** 장바구니에 상품 옵션을 추가하거나 수량을 증가시킵니다. */
    void addItem(Long userId, Long optionId, int quantity);

    /** 특정 사용자의 장바구니 목록을 조회합니다. */
    List<CartItem> findCartItems(Long userId);
    
    /** 장바구니 항목의 수량을 변경합니다. (CartItem ID 사용) */
    void updateQuantity(Long cartItemId, int newQuantity);

    /** 장바구니 항목을 삭제합니다. (CartItem ID 사용) */
    void removeItem(Long cartItemId);
}