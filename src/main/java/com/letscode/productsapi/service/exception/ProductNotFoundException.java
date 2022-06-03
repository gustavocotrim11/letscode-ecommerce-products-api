package com.letscode.productsapi.service.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String id) {
        super ("Product not found. Id " + id);
    }
}
