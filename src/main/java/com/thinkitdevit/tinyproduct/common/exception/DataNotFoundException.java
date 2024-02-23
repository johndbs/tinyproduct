package com.thinkitdevit.tinyproduct.common.exception;


public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message) {
        super(message);
    }

    public static DataNotFoundException createWith(String message) {
        return new DataNotFoundException(message);
    }

}
