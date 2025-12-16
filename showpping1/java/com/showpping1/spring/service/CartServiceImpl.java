package com.showpping1.spring.service;

import com.showpping1.spring.domain.CartItem;
import com.showpping1.spring.domain.ProductOption;
import com.showpping1.spring.repository.CartRepository; 
import com.showpping1.spring.repository.ProductOptionRepository; 

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service // Spring Bean으로 등록
@Transactional(readOnly = true) // 기본적으로 읽기 전용 트랜잭션 설정
public class CartServiceImpl implements CartService { // CartService 인터페이스 구현

    private final CartRepository cartRepository;
    private final ProductOptionRepository optionRepository;
    
    // 생성자 주입
    public CartServiceImpl(CartRepository cartRepository, ProductOptionRepository optionRepository) {
        this.cartRepository = cartRepository;
        this.optionRepository = optionRepository;
    }

    // ---------------------------------------------------------------------------------------
    // ⭐️ @Transactional 어노테이션은 데이터 변경(쓰기)이 발생하는 메서드에만 추가합니다.
    // ---------------------------------------------------------------------------------------
    
    @Override
    @Transactional
    public void addItem(Long userId, Long optionId, int quantity) {
        // ... (이전 답변에서 제시한 addItem 로직) ...
        ProductOption option = optionRepository.findById(optionId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품 옵션입니다."));

        if (option.getStockQuantity() < quantity) {
            throw new IllegalArgumentException("재고가 부족합니다. 현재 재고: " + option.getStockQuantity());
        }

        Optional<CartItem> existingItem = cartRepository.findByUserIdAndProductOption_Id(userId, optionId);

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            int newQuantity = item.getQuantity() + quantity;
            
            if (option.getStockQuantity() < newQuantity) {
                 throw new IllegalArgumentException("장바구니에 담긴 수량과 합산 시 재고를 초과합니다.");
            }
            item.setQuantity(newQuantity);
        } else {
            CartItem newItem = new CartItem();
            newItem.setUserId(userId);
            newItem.setProductOption(option);
            newItem.setQuantity(quantity);
            
            cartRepository.save(newItem);
        }
    }

    @Override
    public List<CartItem> findCartItems(Long userId) {
        // 읽기 전용 트랜잭션이 적용됩니다.
        return cartRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public void updateQuantity(Long cartItemId, int newQuantity) {
        // ... (이전 답변에서 제시한 updateQuantity 로직) ...
        CartItem item = cartRepository.findById(cartItemId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 장바구니 항목입니다."));

        ProductOption option = item.getProductOption();

        if (option.getStockQuantity() < newQuantity) {
            throw new IllegalArgumentException("재고가 부족하여 수량을 " + option.getStockQuantity() + "개로 변경할 수 없습니다.");
        }
        if (newQuantity < 1) {
            throw new IllegalArgumentException("수량은 1개 이상이어야 합니다.");
        }

        item.setQuantity(newQuantity);
    }

    @Override
    @Transactional
    public void removeItem(Long cartItemId) {
  
    	CartItem item = cartRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 장바구니 항목입니다."));
                
            cartRepository.delete(item);
    }

	@Override
	public void clearCartByUserId(Long userId) {
		// TODO Auto-generated method stub
		cartRepository.deleteByUserId(userId);
		System.out.println(">>> [장바구니 비우기] 사용자 " + userId + "의 데이터삭제 ");
		
	}
}