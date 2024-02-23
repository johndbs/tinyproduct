package com.thinkitdevit.tinyproduct.infrastructure.config;

import com.thinkitdevit.tinyproduct.ui.rest.controller.ProductResource;
import com.thinkitdevit.tinyproduct.ui.rest.exception.ApiExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ContextConfiguration(classes = RestConfig.class)
class RestConfigTest {

    @Autowired
    private RestConfig restConfig;

    @Test
    public void restConfig_shouldBeConfiguredCorrectly() {
        assertNotNull(restConfig, "RestConfig should not be null");
        assertTrue(restConfig.isRegistered(ProductResource.class), "RestConfig should have ProductResource registered");
        assertTrue(restConfig.isRegistered(ApiExceptionHandler.class), "RestConfig should have ApiExceptionHandler registered");
    }

}