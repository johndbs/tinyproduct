package com.thinkitdevit.tinyproduct.application.mapper;

import com.thinkitdevit.tinyproduct.application.dto.ProductDto;
import com.thinkitdevit.tinyproduct.ui.dto.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(com.thinkitdevit.tinyproduct.domain.model.Product product);

    com.thinkitdevit.tinyproduct.domain.model.Product toModel(ProductDto productDto);

    List<ProductDto> toDtoList(List<com.thinkitdevit.tinyproduct.domain.model.Product> products);

    List<com.thinkitdevit.tinyproduct.domain.model.Product> toModelList(List<ProductDto> productDtos);

    @Mapping(source="category.id", target="categoryId")
    @Mapping(source="category.name", target="categoryName")
    ProductResponse toResponse(ProductDto productDto);

    @Mapping(source="category.id", target="categoryId")
    @Mapping(source="category.name", target="categoryName")
    List<ProductResponse> toResponseList(List<ProductDto> productDtos);

}
