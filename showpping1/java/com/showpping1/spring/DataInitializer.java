package com.showpping1.spring;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.showpping1.spring.domain.Category;
import com.showpping1.spring.domain.Product;
import com.showpping1.spring.domain.ProductOption;
import com.showpping1.spring.service.CategoryService;

@Component
@Transactional // DB 쓰기 작업이 포함되므로 트랜잭션 유지
public class DataInitializer implements ApplicationRunner { // 메인 카테고리 이니셜라이저 역할

    
    private final CategoryService categoryService;

    // 생성자 주입
    public DataInitializer(CategoryService categoryService) {
       
        this.categoryService = categoryService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(">>> [DB 초기화] ⭐️ 카테고리 구조 생성 시작...");
        createAllCategoriesIfNotExist(); 
        System.out.println(">>> [DB 초기화] 카테고리 구조 생성 완료.");
    }

    private void createAllCategoriesIfNotExist() {
        // 1. 최상위 '상의' 카테고리 확인 및 생성
        Category topCategory = categoryService.findCategoryByName("상의");
        if (topCategory == null) {
             topCategory = new Category();
             topCategory.setName("상의");
             topCategory.setParentId(null);
             categoryService.saveCategory(topCategory);
        }
        Long topCategoryId = topCategory.getId();
       
        // 2. ⭐️ 상의 하위 세부 카테고리 생성 (모든 상품 Initializer가 사용할 구조) ⭐️
        
        // ID 2: 니트/스웨터
        createCategoryIfNotExist("니트/스웨터", topCategoryId);
        
        // ID 3: 맨투맨/스웨트셔츠
        createCategoryIfNotExist("맨투맨/스웨트셔츠", topCategoryId);
        
        // ID 4: 긴소매 티셔츠
        createCategoryIfNotExist("긴소매 티셔츠", topCategoryId);
        
        // ID 5: 후드 티셔츠
        createCategoryIfNotExist("후드 티셔츠", topCategoryId);
        
        // ID 6: 반소매 티셔츠
        createCategoryIfNotExist("반소매 티셔츠", topCategoryId);
        
        // ID 7: 셔츠/블라우스

        
        // 3. ⭐️ 신발 최상위 카테고리 및 하위 구조도 여기서 생성 (ShoeDataInitializer와 충돌 방지) ⭐️
        Category shoeCategory = categoryService.findCategoryByName("신발");
        if (shoeCategory == null) {
             shoeCategory = new Category();
             shoeCategory.setName("신발");
             shoeCategory.setParentId(null);
             categoryService.saveCategory(shoeCategory);
        }
        
    
        
    }
    
    // ⭐️ 헬퍼 메서드: 카테고리를 찾거나 생성합니다.
    private void createCategoryIfNotExist(String name, Long parentId) {
        if (categoryService.findCategoryByName(name) == null) {
            Category newCategory = new Category();
            newCategory.setName(name);
            newCategory.setParentId(parentId);
            categoryService.saveCategory(newCategory);
        }
    }
    
 
    
}