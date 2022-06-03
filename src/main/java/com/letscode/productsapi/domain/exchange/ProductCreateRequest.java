package com.letscode.productsapi.domain.exchange;

import java.math.BigInteger;

public class ProductCreateRequest {

    private String name;
    private String brand;
    private Integer stock;
    private BigInteger price;

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public Integer getStock() {
        return stock;
    }

    public BigInteger getPrice() {
        return price;
    }
}
