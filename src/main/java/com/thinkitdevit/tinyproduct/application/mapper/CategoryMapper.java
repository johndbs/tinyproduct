package com.thinkitdevit.tinyproduct.application.mapper;


import com.thinkitdevit.tinyproduct.application.dto.CategoryDto;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(com.thinkitdevit.tinyproduct.domain.model.Category category);

    com.thinkitdevit.tinyproduct.domain.model.Category toModel(CategoryDto categoryDto);

    List<CategoryDto> toDtoList(List<com.thinkitdevit.tinyproduct.domain.model.Category> categories);

    List<com.thinkitdevit.tinyproduct.domain.model.Category> toModelList(List<CategoryDto> categoryDtos);

}
