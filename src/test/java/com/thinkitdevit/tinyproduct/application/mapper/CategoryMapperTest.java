package com.thinkitdevit.tinyproduct.application.mapper;

import com.thinkitdevit.tinyproduct.application.dto.CategoryDto;
import com.thinkitdevit.tinyproduct.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
public class CategoryMapperTest {

    private CategoryMapper categoryMapper;

    @BeforeEach
    public void setUp() {
        categoryMapper = Mappers.getMapper(CategoryMapper.class);
    }

    @Test
    public void shouldMapCategoryToCategoryDto() {
        // GIVEN
        Category category = new Category();
        category.setId(1);
        category.setName("Electronics");

        // WHEN
        CategoryDto categoryDto = categoryMapper.toDto(category);

        // THEN
        assertNotNull(categoryDto);
        assertEquals(category.getId(), categoryDto.getId());
        assertEquals(category.getName(), categoryDto.getName());
    }

    @Test
    public void shouldMapCategoryDtoToCategory() {
        // GIVEN
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(2);
        categoryDto.setName("Books");

        // WHEN
        Category category = categoryMapper.toModel(categoryDto);

        // THEN
        assertNotNull(category);
        assertEquals(categoryDto.getId(), category.getId());
        assertEquals(categoryDto.getName(), category.getName());
    }

    @Test
    public void shouldMapCategoryListToCategoryDtoList() {
        // GIVEN
        List<Category> categories = Arrays.asList(
                new Category(1, "Electronics"),
                new Category(2, "Books")
        );

        // WHEN
        List<CategoryDto> categoryDtos = categoryMapper.toDtoList(categories);

        // THEN
        assertNotNull(categoryDtos);
        assertEquals(categories.size(), categoryDtos.size());
        assertEquals(categories.get(0).getName(), categoryDtos.get(0).getName());
        assertEquals(categories.get(1).getName(), categoryDtos.get(1).getName());
    }

    @Test
    public void shouldMapCategoryDtoListToCategoryList() {
        // GIVEN
        List<CategoryDto> categoryDtos = Arrays.asList(
                new CategoryDto(1, "Electronics"),
                new CategoryDto(2, "Books")
        );

        // WHEN
        List<Category> categories = categoryMapper.toModelList(categoryDtos);

        // THEN
        assertNotNull(categories);
        assertEquals(categoryDtos.size(), categories.size());
        assertEquals(categoryDtos.get(0).getName(), categories.get(0).getName());
        assertEquals(categoryDtos.get(1).getName(), categories.get(1).getName());
    }
}
