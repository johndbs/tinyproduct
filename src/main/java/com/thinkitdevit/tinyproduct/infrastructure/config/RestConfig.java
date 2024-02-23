package com.thinkitdevit.tinyproduct.infrastructure.config;

import com.thinkitdevit.tinyproduct.ui.rest.controller.ProductResource;
import com.thinkitdevit.tinyproduct.ui.rest.exception.ApiExceptionHandler;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfig extends ResourceConfig  {

    public RestConfig() {
        // Register the resources
        register(ProductResource.class);
        // Register the exception handlers
        register(ApiExceptionHandler.class);
    }

}
