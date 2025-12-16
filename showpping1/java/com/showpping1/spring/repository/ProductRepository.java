package com.showpping1.spring.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.showpping1.spring.domain.Category;
import com.showpping1.spring.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // JpaRepository를 상속하면 기본적인 CRUD(Save, FindById, FindAll, Delete) 기능이 자동으로 제공됩니다.
	Page<Product> findByCategory(Category category, Pageable pageable);
    // 필요하다면 여기에 특정 조건으로 조회하는 메서드를 추가할 수 있습니다.
    // List<Product> findByCategoryName(String categoryName);
	Page<Product> findByCategory_IdIn(Collection<Long> categoryIds, Pageable pageable);
	long countByCategory_Id(Long categoryId);
	List<Product> findAllBy(Pageable pageable);
	List<Product> findTop10ByOrderByViewCountDesc();
}