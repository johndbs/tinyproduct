package com.thinkitdevit.tinyproduct.application.service;

import com.thinkitdevit.tinyproduct.application.dto.ProductDto;
import com.thinkitdevit.tinyproduct.application.mapper.ProductMapper;
import com.thinkitdevit.tinyproduct.common.exception.DataNotFoundException;
import com.thinkitdevit.tinyproduct.domain.model.Product;
import com.thinkitdevit.tinyproduct.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
class ProductApplicationServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductApplicationService productApplicationService;

    @Test
    void findProductById_whenProductExists() {
        long productId = 1L;
        Product product = new Product();
        ProductDto productDto = new ProductDto();

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productMapper.toDto(product)).thenReturn(productDto);

        ProductDto result = productApplicationService.findProductById(productId);

        assertNotNull(result);
        verify(productRepository).findById(productId);
        verify(productMapper).toDto(product);
    }

    @Test
    void findProductById_whenProductDoesNotExist_shouldThrowDataNotFoundException() {
        long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> productApplicationService.findProductById(productId));

        verify(productRepository).findById(productId);
        verifyNoInteractions(productMapper);
    }

    @Test
    void findPaginatedProducts() {
        int page = 0;
        int size = 10;
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Product product = new Product();
        Page<Product> productPage = new PageImpl<>(Collections.singletonList(product));
        ProductDto productDto = new ProductDto();
        Page<ProductDto> productDtoPage = new PageImpl<>(Collections.singletonList(productDto));

        when(productRepository.findAll(pageable)).thenReturn(productPage);
        when(productMapper.toDto(any(Product.class))).thenReturn(productDto);

        Page<ProductDto> result = productApplicationService.findPaginatedProducts(page, size);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.getContent().size());
        verify(productRepository).findAll(pageable);
        verify(productMapper).toDto(any(Product.class));
    }
}
