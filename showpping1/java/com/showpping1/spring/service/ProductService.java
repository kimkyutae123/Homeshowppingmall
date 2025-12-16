package com.showpping1.spring.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // 트랜잭션 관리를 위한 import

import com.showpping1.spring.domain.Category;
import com.showpping1.spring.domain.Product;
import com.showpping1.spring.domain.ProductOption;
import com.showpping1.spring.repository.CategoryRepository;
import com.showpping1.spring.repository.ProductOptionRepository;
import com.showpping1.spring.repository.ProductRepository;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Service // ⭐️ 이 클래스가 서비스 계층임을 스프링에게 알립니다.
 // final 필드에 대한 생성자 주입을 자동으로 처리 (Lombok 기능)
@Transactional(readOnly = true) // 기본적인 모든 메서드에 읽기 전용 트랜잭션을 적용
public class ProductService {
	
    // ⭐️ 레파지토리를 주입받아 사용합니다.
    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final CategoryRepository categoryRepository;
    // private final CategoryRepository categoryRepository; // 카테고리 로직 구현 시 사용
    public ProductService(ProductRepository productRepository, ProductOptionRepository productOptionRepository,CategoryRepository categoryRepository) {
	    this.productRepository = productRepository;
	    this.productOptionRepository = productOptionRepository;
	    this.categoryRepository = categoryRepository;
	}
    /**
     * 새로운 상품을 등록하고 해당 상품의 옵션들을 저장합니다.
     * (상품 등록/수정 시에는 쓰기 트랜잭션이 필요합니다.)
     */
    @Transactional // 쓰기 작업이므로 트랜잭션 별도 지정
 // ProductService.java 파일에 추가
    
    public Long saveProduct(Product product, List<ProductOption> options) { // ⭐️ 인수를 두 개 받도록 추가
        // 1. 상품 정보 저장 (ID가 생성됨)
        productRepository.save(product);

        // 2. 상품 옵션 정보 저장
        if (options != null && !options.isEmpty()) {
            for (ProductOption option : options) {
                // 옵션에 Product 객체 연결 (양방향 관계)
                option.setProduct(product); 
                productOptionRepository.save(option);
            }
        }
        
        return product.getId(); 
    }

    /**
     * 모든 상품 목록을 조회합니다. (메인 페이지 랭킹, 전체 상품 리스트 등에 사용)
     */
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
    
    /**
     * 특정 ID를 가진 상품을 조회합니다. (상세 페이지 등에 사용)
     */
    public Product findProductById(Long id) {
        // ID가 없으면 예외를 발생시키거나, Optional을 반환합니다.
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품 ID입니다: " + id));
    }
    @Transactional(readOnly = true)
    public Page<Product> findProducts(String categoryName, Pageable pageable) {
        
        // 1. 카테고리 이름으로 Category 객체를 찾습니다. (예: "상의" 카테고리 객체)
        Category category = categoryRepository.findByNameIgnoreCase(categoryName); 

        if (category == null) {
            return Page.empty(pageable); 
        }
        
        // 2. ⭐️ 상의 ID(category.getId())를 부모 ID로 가진 모든 자식 ID를 조회합니다.
        List<Long> subCategoryIds = categoryRepository.findIdByParentId(category.getId());
        
        // 3. 필터링할 ID 목록을 최종 구성 (부모 ID + 자식 ID)
        Collection<Long> filterCategoryIds = new ArrayList<>();
        
        // 상의 카테고리 ID (예: 1번) 추가
        filterCategoryIds.add(category.getId());
        
        // 모든 하위 카테고리 ID (니트, 맨투맨 등의 ID) 추가
        filterCategoryIds.addAll(subCategoryIds); 

        // 4. 통합된 ID 목록으로 상품을 조회
        return productRepository.findByCategory_IdIn(filterCategoryIds, pageable);
    }
    public long countAllProducts() {
        // ⭐️ 수정: ProductRepository의 count() 메서드를 호출하여 실제 개수를 반환합니다.
        return productRepository.count(); 
    }
    public long countProductsByCategory(Long categoryId) {
        // ⭐️ 추가: ProductRepository의 countByCategory_Id 메서드를 호출합니다.
        return productRepository.countByCategory_Id(categoryId);
    }
    @Transactional // ⭐️ 쓰기 작업이므로 트랜잭션 지정
    public void incrementViewCount(Long productId) {
        // 1. 상품을 찾습니다. (이때 ProductService의 findProductById를 재사용 가능)
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품 ID입니다: " + productId));
        
        // 2. 조회수를 1 증가시킵니다.
        // 현재 조회수가 null인 경우를 대비해 0으로 초기화 후 증가시킵니다.
        int currentCount = product.getViewCount() != null ? product.getViewCount() : 0;
        product.setViewCount(currentCount + 1);
        
        // 3. 트랜잭션 종료 시 변경 사항이 DB에 반영됩니다. (save 호출 생략 가능하지만 명시적으로 호출할 수도 있음)
        // productRepository.save(product); 
    }
    @Transactional(readOnly = true)
    public List<Product> findLatestProducts(int limit) {
        
        // 1. Pageable 객체 생성: 0페이지부터, limit 개수만큼, regDate 기준 내림차순 정렬
        Sort sort = Sort.by(Sort.Direction.DESC, "regDate");
        Pageable pageable = PageRequest.of(0, limit, sort);
        
        // 2. Repository를 통해 최신 상품 리스트 조회
        // findAllBy(Pageable)을 호출하여 정렬 및 개수 제한을 적용
        return productRepository.findAllBy(pageable);
    }
    @Transactional(readOnly = true)
    public List<Product> findTop10ByViewCount() {
        
    	return productRepository.findTop10ByOrderByViewCountDesc();
        
    }
    
    
}