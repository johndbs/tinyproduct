package com.thinkitdevit.tinyproduct.domain.repository;

import com.thinkitdevit.tinyproduct.domain.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for products
 */
@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    /**
     * Find a product by its id
     * @param id the id of the product
     * @return the product if found
     */
    Optional<Product> findById(long id);

}
