package com.showpping1.spring;

import com.showpping1.spring.domain.Category;
import com.showpping1.spring.domain.Product;
import com.showpping1.spring.domain.ProductOption;
import com.showpping1.spring.service.CategoryService;
import com.showpping1.spring.service.ProductService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@Transactional 
public class ShoeDataInitializer implements ApplicationRunner {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ShoeDataInitializer(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(">>> [DB 초기화 2] 신발 데이터 삽입 시작...");
        createShoeDataIfNotExist();
        System.out.println(">>> [DB 초기화 2] 신발 데이터 완료.");
    }

    private void createShoeDataIfNotExist() {
        Category existingShoeCategory = categoryService.findCategoryByName("신발");
        
        if (existingShoeCategory != null) {
            System.out.println(">>> [DB 초기화] '신발' 카테고리가 이미 존재하므로 건너뜁니다.");
            return;
        }

        // --- '신발' 카테고리 생성 (ID 8 할당 가정) ---
        Category shoeCategory = new Category();
        shoeCategory.setName("신발");
        shoeCategory.setParentId(null);
        categoryService.saveCategory(shoeCategory); 
        Long parentId = shoeCategory.getId(); // 하위 카테고리의 Parent ID로 사용

        // --- 하위 카테고리 생성 (ID 9 ~ 14 순차 할당 가정) ---
        
        // 9. 스니커즈
        Category sneakersCategory = createSubCategory("스니커즈", parentId);
        
        // 10. 패딩/퍼신발
        Category winterShoesCategory = createSubCategory("패딩/퍼신발", parentId);
        
        // 11. 부츠/워커
        Category bootsCategory = createSubCategory("부츠/워커", parentId);
        
        // 12. 구두
        Category dressShoesCategory = createSubCategory("구두", parentId);
        
        // 13. 샌들/슬리퍼
        Category sandalsCategory = createSubCategory("샌들/슬리퍼", parentId);
        
        // 14. 스포츠화
        Category sportsShoesCategory = createSubCategory("스포츠화", parentId);
        
        
        // --- 상품 데이터 생성 및 연결 ---
        
        // 스니커즈 (ID 9 연결)
        saveShoeProduct("화이트 데일리 스니커즈", 85000, "/images/sneakshoe_1.jpg", sneakersCategory, "260", 100);

        // 패딩/퍼신발 (ID 10 연결)
        saveShoeProduct("방한 패딩 부츠", 72000, "/images/paddingshoe_1.jpg", winterShoesCategory, "240", 80);
        
        // 부츠/워커 (ID 11 연결)
        saveShoeProduct("레더 첼시 부츠", 120000, "/images/laddershoe_1.jpg", bootsCategory, "245", 50);

        // 구두 (ID 12 연결)
        saveShoeProduct("클래식 더비 슈즈", 150000, "/images/classicshoe_1.jpg", dressShoesCategory, "270", 40);

        // 샌들/슬리퍼 (ID 13 연결)
        saveShoeProduct("버클 스트랩 샌들", 45000, "/images/sandershoe_1.jpg", sandalsCategory, "250", 150);
        
        // 스포츠화 (ID 14 연결)
        saveShoeProduct("러닝화 블랙", 99000, "/images/sportsshoe_.jpg", sportsShoesCategory, "280", 120);
    }
    
    // 코드 중복을 줄이기 위한 헬퍼 메서드
    private Category createSubCategory(String name, Long parentId) {
        Category category = new Category();
        category.setName(name);
        category.setParentId(parentId);
        categoryService.saveCategory(category);
        return category;
    }
    
    // 코드 중복을 줄이기 위한 헬퍼 메서드
    private void saveShoeProduct(String name, int price, String imageUrl, Category category, String size, int stock) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setImageUrl(imageUrl);
        product.setCategory(category);
        
        ProductOption option = new ProductOption();
        option.setSizeName(size);
        option.setStockQuantity(stock);
        
        productService.saveProduct(product, Collections.singletonList(option));
    }
}