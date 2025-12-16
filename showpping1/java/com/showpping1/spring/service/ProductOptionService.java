// ProductOptionService.java

package com.showpping1.spring.service;

import com.showpping1.spring.domain.ProductOption;
import com.showpping1.spring.repository.ProductOptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductOptionService {

	private int stockQuantity;
    private final ProductOptionRepository productOptionRepository;

    // ⭐️ 생성자 주입
    public ProductOptionService(ProductOptionRepository productOptionRepository) {
        this.productOptionRepository = productOptionRepository;
    }

    /**
     * ⭐️ [추가] 특정 상품에 연결된 모든 옵션 목록을 조회합니다.
     */
    public List<ProductOption> findOptionsByProductId(Long productId) {
        // Repository의 findByProductId 쿼리를 호출하여 결과를 반환합니다.
        return productOptionRepository.findByProductId(productId);
    }
    public void decreaseStock(Long optionId, int quntityToDecrease) {
    	ProductOption option = productOptionRepository.findById(optionId)
    			.orElseThrow(() -> new RuntimeException("해당 상품 옵션을 찾을 수 없습니다"));
    	int currentStock = option.getStockQuantity();
    	
    	if(currentStock < quntityToDecrease) {
    		throw new RuntimeException(option.getSizeName() + "사이즈의 재고가 부족합니다");
    	}
    	option.setStockQuantity(currentStock - quntityToDecrease);
    	productOptionRepository.save(option);
    }
    
}