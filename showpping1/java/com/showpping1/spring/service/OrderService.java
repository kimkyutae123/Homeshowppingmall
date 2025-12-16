package com.showpping1.spring.service;
import java.util.List; // ⭐️ 필수
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.showpping1.spring.domain.CartItem; // ⭐️ 필수
import com.showpping1.spring.domain.ProductOption;
import com.showpping1.spring.domain.CartItem;



@Service
public class OrderService {
    
    private final CartService cartService;
    private final ProductOptionService productOptionService;
    public OrderService(CartService cartService, ProductOptionService productOptionService) {
        this.cartService = cartService;
        this.productOptionService = productOptionService;
    }
    
    @Transactional
    public void processPaymentAndClearCart(Long userId, List<CartItem> cartItems) {
    	for (CartItem item : cartItems) {
    		Long optionId = item.getProductOption().getId();
    		int quantity = item.getQuantity();
    		
    		productOptionService.decreaseStock(optionId, quantity);
    	
    		System.out.println(">>> 재고 감소: Option ID " +optionId + ", 수량:" + quantity);
    	}
    	cartService.clearCartByUserId(userId);
    	
    	System.out.println(">>> [주문 성공] 재고 감소 및 장바구니 정리 완료. ");
    	
    }
    
 
}