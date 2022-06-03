package com.letscode.productsapi.controller;

import com.letscode.productsapi.domain.ProductEntity;
import com.letscode.productsapi.domain.exchange.ProductCreateRequest;
import com.letscode.productsapi.domain.exchange.ProductStockRequest;
import com.letscode.productsapi.domain.exchange.ProductUpdateRequest;
import com.letscode.productsapi.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductEntity> createUser(@RequestBody ProductCreateRequest request) {
        ProductEntity productToPersist = new ProductEntity();
        productToPersist.setName(request.getName());
        productToPersist.setBrand(request.getBrand());
        productToPersist.setStock(request.getStock());
        productToPersist.setPrice(request.getPrice());

        ProductEntity productPersisted = service.save(productToPersist);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(productPersisted.getId()).toUri();

        return ResponseEntity.created(uri).body(productPersisted);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductEntity> findById(@PathVariable String id) {
        ProductEntity product = service.findById(id);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping()
    public ResponseEntity<List<ProductEntity>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductEntity> update(@PathVariable String id, @RequestBody ProductUpdateRequest request) {
        ProductEntity productToUpdate = new ProductEntity();
        productToUpdate.setName(request.getName());
        productToUpdate.setBrand(request.getBrand());
        productToUpdate.setPrice(request.getPrice());

        return ResponseEntity.ok().body(service.update(id, productToUpdate));
    }

    @PutMapping(value = "/{id}/stock")
    public ResponseEntity<ProductEntity> updateStock(@PathVariable String id, @RequestBody ProductStockRequest request) {
        return ResponseEntity.ok().body(service.updateStock(id, request.getStock()));
    }
}
