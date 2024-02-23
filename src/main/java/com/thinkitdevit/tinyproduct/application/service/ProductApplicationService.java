package com.thinkitdevit.tinyproduct.application.service;

import com.thinkitdevit.tinyproduct.application.dto.ProductDto;
import com.thinkitdevit.tinyproduct.application.mapper.ProductMapper;
import com.thinkitdevit.tinyproduct.common.exception.DataNotFoundException;
import com.thinkitdevit.tinyproduct.domain.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductApplicationService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductApplicationService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    /**
     * Find a product by its id
     * @param id the id of the product
     * @return the product if found
     */
    public ProductDto findProductById(long id) {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> DataNotFoundException.createWith("Product not found with id: " + id));
    }

    /**
     * Find all products
     * @param page the page number
     * @param size the size of the page
     * @return a list of products
     */
    public Page<ProductDto> findPaginatedProducts(int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return productRepository.findAll(pageable)
                .map(productMapper::toDto);
    }

}
