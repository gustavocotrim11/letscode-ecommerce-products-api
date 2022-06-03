package com.letscode.productsapi.service;

import com.letscode.productsapi.domain.ProductEntity;
import com.letscode.productsapi.repository.ProductRepository;
import com.letscode.productsapi.service.exception.ProductNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    public ProductEntity save(ProductEntity product) {
        return repository.save(product);
    }

    public ProductEntity findById(String id) {
            Optional<ProductEntity> product = repository.findById(Integer.valueOf(id));
            return product.orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<ProductEntity> findAll() {
        return repository.findAll();
    }

    public void delete(String id) {
        try {
            repository.deleteById(Integer.valueOf(id));
        } catch (EmptyResultDataAccessException e) {
            throw new ProductNotFoundException(id);
        }
    }

    public ProductEntity update(String id, ProductEntity productToUpdate) {
        try {
            ProductEntity product = repository.getReferenceById(Integer.valueOf(id));
            product.setName(productToUpdate.getName());
            product.setBrand(productToUpdate.getBrand());
            product.setPrice(productToUpdate.getPrice());

            return repository.save(product);
        } catch (EntityNotFoundException e) {
            throw new ProductNotFoundException(id);
        }
    }

    public ProductEntity updateStock(String id, Integer stock) {
        try {
            ProductEntity product = repository.getReferenceById(Integer.valueOf(id));
            product.setStock(stock);

            return repository.save(product);
        } catch (EntityNotFoundException e) {
            throw new ProductNotFoundException(id);
        }
    }
}
