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
public class KnitDataInitializer implements ApplicationRunner {

    private final ProductService productService;
    private final CategoryService categoryService;

    public KnitDataInitializer(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(">>> [DB 초기화 - 니트] 데이터 삽입 시작...");
        createKnitDataIfNotExist();
        System.out.println(">>> [DB 초기화 - 니트] 완료.");
    }

    private void createKnitDataIfNotExist() {
        
        Category topCategory = categoryService.findCategoryByName("상의");
        if (topCategory == null) {
            System.out.println(">>> [DB 초기화 - 니트] 상위 '상의' 카테고리가 없어 생성합니다.");
            topCategory = new Category();
            topCategory.setName("상의");
            topCategory.setParentId(null);
            categoryService.saveCategory(topCategory);
        }
        
        // 2. 니트/스웨터 카테고리를 찾거나 생성합니다.
        Category knitCategory = categoryService.findCategoryByName("니트/스웨터");
        if (knitCategory == null) {
            System.out.println(">>> [DB 초기화 - 니트] '니트/스웨터' 카테고리를 생성합니다.");
            knitCategory = new Category();
            knitCategory.setName("니트/스웨터");
            knitCategory.setParentId(topCategory.getId());
            categoryService.saveCategory(knitCategory);
        }
        
        // ⭐️⭐️⭐️ 수정된 중복 삽입 방지 로직: 자기 카테고리만 확인 ⭐️⭐️⭐️
        // 니트 카테고리에 상품이 하나라도 있으면 건너뜁니다.
        if (productService.countProductsByCategory(knitCategory.getId()) > 0) { 
            System.out.println(">>> [DB 초기화 - 니트] 해당 카테고리 상품이 이미 존재하여 초기화 작업을 건너뜁니다.");
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
        
        // --- 상품 1: 미드넥 하프집업 ---
        Product knit1 = new Product();
        knit1.setName("미드넥 하프집업 니트 [딥브라운]");
        knit1.setPrice(68000);
        knit1.setImageUrl("/image/knitshirt_1.png");
        knit1.setCategory(knitCategory);

        // --- 상품 2: 코튼 크루넥 ---
        Product knit2 = new Product();
        knit2.setName("인텔리 코튼 크루넥 니트 그레이시 그린");
        knit2.setPrice(59250);
        knit2.setImageUrl("/image/knitshirt_2.png");
        knit2.setCategory(knitCategory);

        // --- 상품 3: 소프트 울 브이넥 ---
        Product knit3 = new Product();
        knit3.setName("소프트 울 브이넥 래글런니트 - 차콜");
        knit3.setPrice(58400);
        knit3.setImageUrl("/image/knitshirt_3.png");
        knit3.setCategory(knitCategory);

        // --- 상품 4: 노르딕 하프 집업 ---
        Product knit4 = new Product();
        knit4.setName("로즈 노르딕 니트 하프 집업 ( 스카이블루 )");
        knit4.setPrice(148000);
        knit4.setImageUrl("/image/knitshirt_4.png");
        knit4.setCategory(knitCategory);

        // --- 상품 5: 릴렉스 크롭 브이넥 ---
        Product knit5 = new Product();
        knit5.setName("릴렉스 크롭 브이넥 니트 [마젠타]");
        knit5.setPrice(69000);
        knit5.setImageUrl("/image/knitshirt_5.png");
        knit5.setCategory(knitCategory);

        // --- 상품 6: 디스토션 라운드 니트 ---
        Product knit6 = new Product();
        knit6.setName("DISTORTION ROUND KNIT [BLACK]");
        knit6.setPrice(69500);
        knit6.setImageUrl("/image/knitshirt_6.png");
        knit6.setCategory(knitCategory);

        // --- 상품 7: 하버 스트라이프 니트 ---
        Product knit7 = new Product();
        knit7.setName("HARBOR STRIPE KNIT [RUSTY CHARCOAL]");
        knit7.setPrice(131000);
        knit7.setImageUrl("/image/knitshirt_7.png");
        knit7.setCategory(knitCategory);

        // --- 상품 8: 램스울 집업 카디건 ---
        Product knit8 = new Product();
        knit8.setName("램스울 집업 카디건");
        knit8.setPrice(97200);
        knit8.setImageUrl("/image/knitshirt_8.png");
        knit8.setCategory(knitCategory);

        // --- 상품 9: 리파인드 투웨이 니트 집업 ---
        Product knit9 = new Product();
        knit9.setName("리파인드 투웨이 니트 집업 [베이지]");
        knit9.setPrice(24000);
        knit9.setImageUrl("/image/knitshirt_9.png");
        knit9.setCategory(knitCategory);

        // --- 상품 10: 클라우드 슈퍼파인 V넥 ---
        Product knit10 = new Product();
        knit10.setName("[SUPERIOR] CLOUD SUPERFINE V-NECK KNIT [SAND BEIGE]");
        knit10.setPrice(89600);
        knit10.setImageUrl("/image/knitshirt_10.png");
        knit10.setCategory(knitCategory);

        // --- 상품 11: 화란 세미오버 니트 ---
        Product knit11 = new Product();
        knit11.setName("화란 세미오버 니트 러스티 그린");
        knit11.setPrice(40460);
        knit11.setImageUrl("/image/knitshirt_11.png"); 
        knit11.setCategory(knitCategory);

        // --- 상품 12: 밀키 앙고라 가디건 ---
        Product knit12 = new Product();
        knit12.setName("밀키 앙고라 가디건_브라운");
        knit12.setPrice(154000);
        knit12.setImageUrl("/image/knitshirt_12.png"); 
        knit12.setCategory(knitCategory);

        // --- 상품 13: 와일드 로고 라운드 오버핏 니트 ---
        Product knit13 = new Product();
        knit13.setName("와일드 로고 라운드 오버핏 니트_브라운");
        knit13.setPrice(42000);
        knit13.setImageUrl("/image/knitshirt_13.png"); 
        knit13.setCategory(knitCategory);

        // --- 상품 14: 새들 하찌 에센셜 나그랑 니트 ---
        Product knit14 = new Product();
        knit14.setName("새들 하찌 에센셜 나그랑 니트 [카키]");
        knit14.setPrice(37500);
        knit14.setImageUrl("/image/knitshirt_14.png"); 
        knit14.setCategory(knitCategory);

        // --- 상품 15: 노먼 브러쉬 노르딕 니트 ---
        Product knit15 = new Product();
        knit15.setName("노먼 브러쉬 노르딕 니트_Beige");
        knit15.setPrice(45900);
        knit15.setImageUrl("/image/knitshirt_15.png"); 
        knit15.setCategory(knitCategory);

        // --- 상품 16: 소프트 울 클래식 카라넥 니트 ---
        Product knit16 = new Product();
        knit16.setName("소프트 울 클래식 카라넥 니트 (DUSTY MINT)");
        knit16.setPrice(29900);
        knit16.setImageUrl("/image/knitshirt_16.png"); 
        knit16.setCategory(knitCategory);
        
        // ⭐️⭐️⭐️ 5. 상품과 옵션 연결 및 저장 ⭐️⭐️⭐️
        System.out.println(">>> 니트 상품 16개 저장 시작...");

        productService.saveProduct(knit1, allOptions1);
        productService.saveProduct(knit2, allOptions2);
        productService.saveProduct(knit3, allOptions3);
        productService.saveProduct(knit4, allOptions4);
        productService.saveProduct(knit5, allOptions5);
        productService.saveProduct(knit6, allOptions6);
        productService.saveProduct(knit7, allOptions7);
        productService.saveProduct(knit8, allOptions8);
        productService.saveProduct(knit9, allOptions9);
        productService.saveProduct(knit10, allOptions10);
        productService.saveProduct(knit11, allOptions11);
        productService.saveProduct(knit12, allOptions12);
        productService.saveProduct(knit13, allOptions13);
        productService.saveProduct(knit14, allOptions14);
        productService.saveProduct(knit15, allOptions15);
        productService.saveProduct(knit16, allOptions16);

        System.out.println(">>> 니트 상품 16개 저장 완료.");
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