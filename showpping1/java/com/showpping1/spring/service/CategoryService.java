package com.showpping1.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.showpping1.spring.domain.Category;
import com.showpping1.spring.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 기본적으로 조회용 트랜잭션 적용
public class CategoryService {

    // CategoryRepository 주입
    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    // ⭐️⭐️⭐️ ----------------------------- ⭐️⭐️⭐️
    /**
     * 새로운 카테고리를 등록합니다.
     */
    @Transactional // 쓰기 작업이므로 별도 트랜잭션 적용
    public Long saveCategory(Category category) {
        // [비즈니스 로직]: 카테고리 이름 중복 확인 로직 등을 여기에 추가할 수 있습니다.
        
        categoryRepository.save(category);
        return category.getId();
    }

    /**
     * 모든 카테고리 목록을 조회합니다.
     */
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * 특정 ID를 가진 카테고리를 조회합니다.
     */
    public Optional<Category> findCategoryById(Long id) {
        return categoryRepository.findById(id);
    }
    
    /**
     * 카테고리 이름으로 카테고리를 조회합니다.
     */
    public Category findCategoryByName(String name) {
        // CategoryRepository에 findByName 메서드가 정의되어 있어야 합니다.
        return categoryRepository.findByName(name);
    }
    
    // [추가 구현 필요]: 카테고리 수정/삭제 로직, 계층 구조를 고려한 조회 로직 등
}