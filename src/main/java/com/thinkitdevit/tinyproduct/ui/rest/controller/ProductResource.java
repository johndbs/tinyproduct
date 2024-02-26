package com.thinkitdevit.tinyproduct.ui.rest.controller;

import com.thinkitdevit.tinyproduct.application.mapper.ProductMapper;
import com.thinkitdevit.tinyproduct.application.service.ProductApplicationService;
import com.thinkitdevit.tinyproduct.ui.dto.ProductResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;


@Slf4j
@Controller
@Path("/products")
public class ProductResource {

    private final ProductApplicationService productApplicationService;

    private final ProductMapper productMapper;

    public ProductResource( ProductApplicationService productApplicationService, ProductMapper productMapper) {
        this.productApplicationService = productApplicationService;
        this.productMapper = productMapper;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") long id) {
        log.info("Fetching product with id: {}", id);
        ProductResponse productResponse =  productMapper.toResponse(productApplicationService.findProductById(id));
        return Response.ok().entity(productResponse).build();
    }



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsPaginated(@QueryParam("page") Integer page, @QueryParam("size") Integer size) {

        int defaultPage = 0;
        int defaultSize = 10;

        int effectivePage = page == null ? defaultPage : page;
        int effectiveSize = size == null ? defaultSize : size;

        return Response.ok().entity(productApplicationService.findPaginatedProducts(effectivePage, effectiveSize)).build();
    }

}
