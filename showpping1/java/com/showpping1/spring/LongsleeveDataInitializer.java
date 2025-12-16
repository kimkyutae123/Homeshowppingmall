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
import com.showpping1.spring.service.ProductService;

@Component
@Transactional 
public class LongsleeveDataInitializer implements ApplicationRunner {

    private final ProductService productService;
    private final CategoryService categoryService;

    public LongsleeveDataInitializer(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(">>> [DB 초기화 - 긴소매 티셔츠] 데이터 삽입 시작...");
        createLongsleeveDataIfNotExist();
        System.out.println(">>> [DB 초기화 - 긴소매 티셔츠] 완료.");
    }

    private void createLongsleeveDataIfNotExist() {
        
        Category topCategory = categoryService.findCategoryByName("상의");
        if (topCategory == null) {
            System.out.println(">>> [DB 초기화 - 긴소매] 상위 '상의' 카테고리가 없어 생성합니다.");
            topCategory = new Category();
            topCategory.setName("상의");
            topCategory.setParentId(null);
            categoryService.saveCategory(topCategory);
        }
        
        // 2. 긴소매 티셔츠 카테고리를 찾거나 생성합니다.
        Category longsleeveCategory = categoryService.findCategoryByName("긴소매 티셔츠");
        if (longsleeveCategory == null) {
            System.out.println(">>> [DB 초기화 - 긴소매] '긴소매 티셔츠' 카테고리가 없어 새로 생성합니다.");
            longsleeveCategory = new Category();
            longsleeveCategory.setName("긴소매 티셔츠");
            longsleeveCategory.setParentId(topCategory.getId());
            categoryService.saveCategory(longsleeveCategory);
        }
        
        // ⭐️⭐️⭐️ 수정된 중복 삽입 방지 로직: 자기 카테고리만 확인 ⭐️⭐️⭐️
        // 긴소매 카테고리에 상품이 하나라도 있으면 건너뜁니다.
        if (productService.countProductsByCategory(longsleeveCategory.getId()) > 0) { // ⭐️ 수정: 카테고리 ID 사용
            System.out.println(">>> [DB 초기화 - 긴소매 티셔츠] 해당 카테고리 상품이 이미 존재하여 초기화 작업을 건너뜁니다.");
            return; 
        }

        // ⭐️⭐️⭐️ 3. 옵션 세트 정의 (16개) ⭐️⭐️⭐️
        List<ProductOption> allOptions1 = createOptions(150);
        List<ProductOption> allOptions2 = createOptions(150);
        List<ProductOption> allOptions3 = createOptions(150);
        List<ProductOption> allOptions4 = createOptions(150);
        List<ProductOption> allOptions5 = createOptions(150);
        List<ProductOption> allOptions6 = createOptions(150);
        List<ProductOption> allOptions7 = createOptions(150);
        List<ProductOption> allOptions8 = createOptions(150);
        List<ProductOption> allOptions9 = createOptions(150);
        List<ProductOption> allOptions10 = createOptions(150);
        List<ProductOption> allOptions11 = createOptions(150);
        List<ProductOption> allOptions12 = createOptions(150);
        List<ProductOption> allOptions13 = createOptions(150);
        List<ProductOption> allOptions14 = createOptions(150);
        List<ProductOption> allOptions15 = createOptions(150);
        List<ProductOption> allOptions16 = createOptions(150);

        // ⭐️⭐️⭐️ 4. 상품 1~16 정의 ⭐️⭐️⭐️

        // --- 상품 1: 베이직 오버핏 긴팔티 ---
        Product longsleeve1 = new Product();
        longsleeve1.setName("STITCH RIBBON T CHARCOAL");
        longsleeve1.setPrice(52000);
        longsleeve1.setImageUrl("/image/longsleeve_1.png");
        longsleeve1.setCategory(longsleeveCategory);

        // --- 상품 2: 스트라이프 긴팔티 ---
        Product longsleeve2 = new Product();
        longsleeve2.setName("소프트 컬러 스냅 배색 레이어드 슬림핏 슬리브리스 티셔츠 [차콜]");
        longsleeve2.setPrice(28900);
        longsleeve2.setImageUrl("/image/longsleeve_2.png");
        longsleeve2.setCategory(longsleeveCategory);

        // --- 상품 3: 레이어드 롱슬리브 ---
        Product longsleeve3 = new Product();
        longsleeve3.setName("넥포인트 버튼 레이어드 티셔츠_블랙");
        longsleeve3.setPrice(24200);
        longsleeve3.setImageUrl("/image/longsleeve_3.png");
        longsleeve3.setCategory(longsleeveCategory);

        // --- 상품 4: 그래픽 프린팅 티셔츠 ---
        Product longsleeve4 = new Product();
        longsleeve4.setName("[DIVEIN X 패플서] RIBBED LONG SLEEVE (MELANGE GRAY)");
        longsleeve4.setPrice(62100);
        longsleeve4.setImageUrl("/image/longsleeve_4.png");
        longsleeve4.setCategory(longsleeveCategory);

        // --- 상품 5: 와플 니트 긴팔티 ---
        Product longsleeve5 = new Product();
        longsleeve5.setName("하이넥 헨리 롱슬리브 [그레이]");
        longsleeve5.setPrice(49600);
        longsleeve5.setImageUrl("/image/longsleeve_5.png");
        longsleeve5.setCategory(longsleeveCategory);

        // --- 상품 6: 헨리넥 긴팔티 ---
        Product longsleeve6 = new Product();
        longsleeve6.setName("WASHED BORDER L/S T-SHIRT [CASHEW NUT]");
        longsleeve6.setPrice(64800);
        longsleeve6.setImageUrl("/image/longsleeve_6.png");
        longsleeve6.setCategory(longsleeveCategory);

        // --- 상품 7: 루즈핏 포켓 긴팔티 ---
        Product longsleeve7 = new Product();
        longsleeve7.setName("베이직 헨리넥 와플 롱슬리브_블랙");
        longsleeve7.setPrice(25900);
        longsleeve7.setImageUrl("/image/longsleeve_7.png");
        longsleeve7.setCategory(longsleeveCategory);

        // --- 상품 8: 코튼 블렌드 긴팔티 ---
        Product longsleeve8 = new Product();
        longsleeve8.setName("원 포인트 롱슬리브 티셔츠(M) WHITE");
        longsleeve8.setPrice(155000);
        longsleeve8.setImageUrl("/image/longsleeve_8.png");
        longsleeve8.setCategory(longsleeveCategory);

        // --- 상품 9: 컬러 블록 긴팔티 ---
        Product longsleeve9 = new Product();
        longsleeve9.setName("BIG LOGO WOVEN MTM_32YC5F22");
        longsleeve9.setPrice(103200);
        longsleeve9.setImageUrl("/image/longsleeve_9.png");
        longsleeve9.setCategory(longsleeveCategory);

        // --- 상품 10: 워싱 처리 긴팔티 ---
        Product longsleeve10 = new Product();
        longsleeve10.setName("ABC 카모 콜리지 롱슬리브 티셔츠(M) BLACK/GREEN");
        longsleeve10.setPrice(155000);
        longsleeve10.setImageUrl("/image/longsleeve_10.png");
        longsleeve10.setCategory(longsleeveCategory);

        // --- 상품 11: 롱 슬리브 폴로 티셔츠 ---
        Product longsleeve11 = new Product();
        longsleeve11.setName("044 레글런 롱 슬리브 티셔츠");
        longsleeve11.setPrice(32780);
        longsleeve11.setImageUrl("/image/longsleeve_11.png");
        longsleeve11.setCategory(longsleeveCategory);

        // --- 상품 12: 하프넥 긴팔티 ---
        Product longsleeve12 = new Product();
        longsleeve12.setName("피그먼트 펑처드 유엔 롱슬리브 티 차콜");
        longsleeve12.setPrice(38400);
        longsleeve12.setImageUrl("/image/longsleeve_12.png");
        longsleeve12.setCategory(longsleeveCategory);

        // --- 상품 13: 덤블 워싱 긴팔티 ---
        Product longsleeve13 = new Product();
        longsleeve13.setName("부디즘 레이어드 피그먼트 그래픽 롱 슬리브 버건디");
        longsleeve13.setPrice(39800);
        longsleeve13.setImageUrl("/image/longsleeve_13.png");
        longsleeve13.setCategory(longsleeveCategory);

        // --- 상품 14: 드롭숄더 긴팔티 ---
        Product longsleeve14 = new Product();
        longsleeve14.setName("[라이즈PICK]와플 헨리 넥 티셔츠 브라운");
        longsleeve14.setPrice(80100);
        longsleeve14.setImageUrl("/image/longsleeve_14.png");
        longsleeve14.setCategory(longsleeveCategory);

        // --- 상품 15: 니트 짜임 긴팔티 ---
        Product longsleeve15 = new Product();
        longsleeve15.setName("골지 절개 긴소매 티셔츠 블랙");
        longsleeve15.setPrice(23900);
        longsleeve15.setImageUrl("/image/longsleeve_15.png");
        longsleeve15.setCategory(longsleeveCategory);

        // --- 상품 16: 면스판 긴팔티 ---
        Product longsleeve16 = new Product();
        longsleeve16.setName("아일렛 롱슬리브 스트라이프 그레이");
        longsleeve16.setPrice(66600);
        longsleeve16.setImageUrl("/image/longsleeve_16.png");
        longsleeve16.setCategory(longsleeveCategory);
        
        // ⭐️⭐️⭐️ 5. 상품과 옵션 연결 및 저장 ⭐️⭐️⭐️
        System.out.println(">>> 긴소매 티셔츠 상품 16개 저장 시작...");

        productService.saveProduct(longsleeve1, allOptions1);
        productService.saveProduct(longsleeve2, allOptions2);
        productService.saveProduct(longsleeve3, allOptions3);
        productService.saveProduct(longsleeve4, allOptions4);
        productService.saveProduct(longsleeve5, allOptions5);
        productService.saveProduct(longsleeve6, allOptions6);
        productService.saveProduct(longsleeve7, allOptions7);
        productService.saveProduct(longsleeve8, allOptions8);
        productService.saveProduct(longsleeve9, allOptions9);
        productService.saveProduct(longsleeve10, allOptions10);
        productService.saveProduct(longsleeve11, allOptions11);
        productService.saveProduct(longsleeve12, allOptions12);
        productService.saveProduct(longsleeve13, allOptions13);
        productService.saveProduct(longsleeve14, allOptions14);
        productService.saveProduct(longsleeve15, allOptions15);
        productService.saveProduct(longsleeve16, allOptions16);

        System.out.println(">>> 긴소매 티셔츠 상품 16개 저장 완료.");
    }
    // 헬퍼 메서드: 옵션 세트를 생성 (코드 중복 방지)
    private List<ProductOption> createOptions(int stockQuantity) {
        ProductOption optionS = new ProductOption();
        optionS.setSizeName("S");
        optionS.setStockQuantity(stockQuantity);

        ProductOption optionM = new ProductOption();
        optionM.setSizeName("M");
        optionM.setStockQuantity(stockQuantity);

        ProductOption optionL = new ProductOption();
        optionL.setSizeName("L");
        optionL.setStockQuantity(stockQuantity);
        
        return Arrays.asList(optionS, optionM, optionL);
    }
}