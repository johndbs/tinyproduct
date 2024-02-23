package com.thinkitdevit.tinyproduct.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorResponse {

    private  Integer code;
    private  String message;

}
