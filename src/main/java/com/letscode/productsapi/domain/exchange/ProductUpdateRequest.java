package com.letscode.productsapi.domain.exchange;

import java.math.BigInteger;

public class ProductUpdateRequest {

    private String name;
    private String brand;
    private BigInteger price;

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public BigInteger getPrice() {
        return price;
    }
}
