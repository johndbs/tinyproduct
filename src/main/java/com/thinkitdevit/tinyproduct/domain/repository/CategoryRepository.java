package com.thinkitdevit.tinyproduct.domain.repository;

import com.thinkitdevit.tinyproduct.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for categories
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
}
