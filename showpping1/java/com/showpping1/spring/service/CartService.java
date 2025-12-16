package com.showpping1.spring.service;

import com.showpping1.spring.domain.CartItem;
import java.util.List;

/**
 * 장바구니 비즈니스 로직을 위한 인터페이스입니다.
 */
public interface CartService {

    /**
     * 장바구니에 상품 옵션을 추가하거나 수량을 증가시킵니다.
     * @param userId 사용자 ID
     * @param optionId 상품 옵션 ID
     * @param quantity 수량
     */
    void addItem(Long userId, Long optionId, int quantity);

    /**
     * 특정 사용자의 장바구니 목록을 조회합니다.
     * @param userId 사용자 ID
     * @return 장바구니 항목 목록
     */
    List<CartItem> findCartItems(Long userId);
    
    /**
     * 장바구니 항목의 수량을 변경합니다.
     * @param cartItemId 장바구니 항목 ID
     * @param newQuantity 새로운 수량
     */
    void updateQuantity(Long cartItemId, int newQuantity);

    /**
     * 장바구니 항목을 삭제합니다.
     * @param cartItemId 장바구니 항목 ID
     */
    void removeItem(Long cartItemId);
    void clearCartByUserId(Long userId);
}