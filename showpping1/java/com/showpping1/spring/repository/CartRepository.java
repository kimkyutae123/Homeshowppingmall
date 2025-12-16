package com.showpping1.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.showpping1.spring.domain.CartItem;

// JpaRepository를 상속받아 기본적인 CRUD 기능을 자동으로 제공받습니다.
public interface CartRepository extends JpaRepository<CartItem, Long> {

    /**
     * 특정 사용자의 장바구니 항목 전체를 조회합니다.
     * (CartController의 GET /cart 및 CartService의 findCartItems에서 사용)
     * * @param userId 사용자 ID
     * @return 해당 사용자의 장바구니 항목 목록
     */
    List<CartItem> findByUserId(Long userId);

    /**
     * 특정 사용자가 이미 특정 옵션 ID를 장바구니에 담았는지 확인합니다.
     * (CartService의 addItem 로직에서 중복 확인 및 업데이트를 위해 사용)
     * * @param userId 사용자 ID
     * @param productOptionId 상품 옵션 ID
     * @return 해당 조건을 만족하는 CartItem (Optional)
     */
    Optional<CartItem> findByUserIdAndProductOption_Id(Long userId, Long productOptionId);
    
    @Modifying
    @Query("DELETE FROM CartItem c WHERE c.userId = :userId")
    	void deleteByUserId(@Param("userId") Long userId);
}