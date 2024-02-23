package com.thinkitdevit.tinyproduct.ui.rest.controller;

import com.thinkitdevit.tinyproduct.application.dto.ProductDto;
import com.thinkitdevit.tinyproduct.application.service.ProductApplicationService;
import com.thinkitdevit.tinyproduct.application.mapper.ProductMapper;
import com.thinkitdevit.tinyproduct.domain.model.Product;
import com.thinkitdevit.tinyproduct.ui.dto.ProductResponse;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductResourceTest {

    @Mock
    private ProductApplicationService productApplicationService;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductResource productResource;

    @Test
    public void getProductById_whenProductExists() {
        // GIVEN
        long productId = 1L;
        ProductDto productDto = mock(ProductDto.class);
        ProductResponse productResponse = mock(ProductResponse.class);

        when(productApplicationService.findProductById(productId)).thenReturn(productDto);
        when(productMapper.toResponse(productDto)).thenReturn(productResponse);

        // WHEN
        Response response = productResource.getProductById(productId);

        // THEN
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus(), "The status of the response should be 200 OK");
        verify(productApplicationService).findProductById(productId);
        verify(productMapper).toResponse(productDto);
    }

    @Test
    public void getProductsPaginated() {
        // GIVEN
        int page = 0;
        int size = 10;
        Page<ProductDto> productDtoPage = new PageImpl<>(Collections.singletonList(mock(ProductDto.class)), PageRequest.of(page, size), 1);
        when(productApplicationService.findPaginatedProducts(page, size)).thenReturn(productDtoPage);

        // WHEN
        Response response = productResource.getProductsPaginated(page, size);

        // THEN
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus(), "The status of the response should be 200 OK");
        verify(productApplicationService).findPaginatedProducts(page, size);
    }
}
