// ProductOptionRepository.java

package com.showpping1.spring.repository;

import com.showpping1.spring.domain.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
    
    // ⭐️ [추가] Product ID(FK)를 기준으로 모든 옵션 목록을 조회합니다.
    List<ProductOption> findByProductId(Long productId);
    
}