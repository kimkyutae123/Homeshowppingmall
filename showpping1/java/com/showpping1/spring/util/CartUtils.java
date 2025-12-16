package com.showpping1.spring.util;

import com.showpping1.spring.domain.CartItem;
import java.util.List;
import java.util.Collection; // Collection으로 변경 (더 유연함)

public class CartUtils {

    /**
     * 총 상품 금액 (Subtotal)을 계산합니다.
     * 인수를 Collection<?>으로 변경하여 SPEL 타입 변환 오류를 방지합니다.
     */
    public static long calculateSubtotal(Collection<?> items) {
        if (items == null || items.isEmpty()) {
            return 0;
        }
        
        long subtotal = 0;
        for (Object obj : items) {
            // 전달된 객체가 CartItem임을 가정하고 안전하게 형 변환합니다.
            if (obj instanceof CartItem item) {
                // item.getProductOption()은 null일 수 있으므로 안전하게 접근해야 함
                if (item.getProductOption() != null && item.getProductOption().getProduct() != null) {
                    int price = item.getProductOption().getProduct().getPrice();
                    int quantity = item.getQuantity();
                    subtotal += (long) price * quantity;
                }
            }
        }
        return subtotal;
    }

    /**
     * 배송비를 계산합니다. (Collection<?>으로 시그니처 변경)
     */
    public static int getShippingFee(Collection<?> items) {
        long subtotal = calculateSubtotal(items);
        return subtotal >= 50000 ? 0 : 3000;
    }

    /**
     * 최종 주문 금액을 계산합니다. (Collection<?>으로 시그니처 변경)
     */
    public static long calculateFinalTotal(Collection<?> items) {
        long subtotal = calculateSubtotal(items);
        int shippingFee = getShippingFee(items);
        return subtotal + shippingFee;
    }
}