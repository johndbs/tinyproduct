package com.thinkitdevit.tinyproduct.ui.dto;

import lombok.Data;

@Data
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private Integer categoryId;
    private String categoryName;

}
