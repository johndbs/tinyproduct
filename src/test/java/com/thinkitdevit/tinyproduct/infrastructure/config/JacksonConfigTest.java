package com.thinkitdevit.tinyproduct.infrastructure.config;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = JacksonConfig.class)
public class JacksonConfigTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void objectMapperBean_shouldBeConfiguredCorrectly() {
        ObjectMapper objectMapper = context.getBean(ObjectMapper.class);
        assertNotNull(objectMapper, "ObjectMapper bean should not be null");

        assertFalse(objectMapper.isEnabled(SerializationFeature.FAIL_ON_EMPTY_BEANS),
                "ObjectMapper should have SerializationFeature.FAIL_ON_EMPTY_BEANS disabled");
    }
}