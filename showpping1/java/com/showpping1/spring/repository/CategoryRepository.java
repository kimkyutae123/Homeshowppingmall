package com.showpping1.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // ⭐️ @Query import
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.showpping1.spring.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    // ⭐️ 1단계: 정확히 일치하는 카테고리 이름으로 조회 (현재 사용 중)
    Category findByName(String name); 

    // ⭐️ 2단계: 대소문자를 무시하고 조회 (권장)
    // findByNameIgnoreCase를 사용하면 "니트/스웨터"나 "니트/스웨터" 모두 찾을 수 있어 편리합니다.
    Category findByNameIgnoreCase(String name); // 대소문자 무시 검색
    @Query("SELECT c.id FROM Category c WHERE c.parentId = :parentId")
    List<Long> findIdByParentId(@Param("parentId") Long parentId);
    
    
    
}