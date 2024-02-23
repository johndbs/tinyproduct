package com.thinkitdevit.tinyproduct.application.mapper;

import com.thinkitdevit.tinyproduct.application.dto.CategoryDto;
import com.thinkitdevit.tinyproduct.application.dto.ProductDto;
import com.thinkitdevit.tinyproduct.domain.model.Category;
import com.thinkitdevit.tinyproduct.domain.model.Product;
import com.thinkitdevit.tinyproduct.ui.dto.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ProductMapperTest {

    private ProductMapper productMapper;

    @BeforeEach
    public void setUp() {
        productMapper = Mappers.getMapper(ProductMapper.class);
    }

    @Test
    public void shouldMapProductToProductDto() {
        // GIVEN
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setCategory(new Category(1, "Electronics"));

        // WHEN
        ProductDto productDto = productMapper.toDto(product);

        // THEN
        assertNotNull(productDto);
        assertEquals(product.getId(), productDto.getId());
        assertEquals(product.getName(), productDto.getName());
    }

    @Test
    public void shouldMapProductDtoToProduct() {
        // GIVEN
        ProductDto productDto = new ProductDto();
        productDto.setId(2L);
        productDto.setName("Smartphone");

        // WHEN
        Product product = productMapper.toModel(productDto);

        // THEN
        assertNotNull(product);
        assertEquals(productDto.getId(), product.getId());
        assertEquals(productDto.getName(), product.getName());
    }

    @Test
    public void shouldMapProductListToProductDtoList() {
        // GIVEN
        List<Product> products = Arrays.asList(
                new Product(1L, "Laptop", "Describe the laptop", new Category(1, "Electronics")),
                new Product(2L, "Smartphone", "Describe the smartphone", new Category(2, "Mobile Phones"))
        );

        // WHEN
        List<ProductDto> productDtos = productMapper.toDtoList(products);

        // THEN
        assertNotNull(productDtos);
        assertEquals(products.size(), productDtos.size());
        assertEquals(products.get(0).getName(), productDtos.get(0).getName());
        assertEquals(products.get(1).getName(), productDtos.get(1).getName());
    }

    @Test
    public void shouldMapProductDtoToProductResponse() {
        // GIVEN
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Laptop");

        CategoryDto categoryDto = new CategoryDto(1, "Electronics");
        productDto.setCategory(categoryDto);


        // WHEN
        ProductResponse productResponse = productMapper.toResponse(productDto);

        // THEN
        assertNotNull(productResponse);
        assertEquals(productDto.getId(), productResponse.getId());
        assertEquals(productDto.getName(), productResponse.getName());
        assertEquals(productDto.getCategory().getId(), productResponse.getCategoryId());
        assertEquals(productDto.getCategory().getName(), productResponse.getCategoryName());
    }

    @Test
    public void shouldMapProductDtoListToProductResponseList() {
        // GIVEN
        List<ProductDto> productDtos = Arrays.asList(
                new ProductDto(1L, "Laptop", "Describe the laptop ...", new CategoryDto(1, "Electronics")),
                new ProductDto(2L, "Smartphone", "Describe the laptop", new CategoryDto(2, "Mobile Phones"))
        );

        // WHEN
        List<ProductResponse> productResponses = productMapper.toResponseList(productDtos);

        // THEN
        assertNotNull(productResponses);
        assertEquals(productDtos.size(), productResponses.size());
        assertEquals(productDtos.get(0).getName(), productResponses.get(0).getName());
        assertEquals(productDtos.get(1).getName(), productResponses.get(1).getName());
    }
}
