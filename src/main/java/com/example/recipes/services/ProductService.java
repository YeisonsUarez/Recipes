package com.example.recipes.services;

import com.example.recipes.commos.Constants;
import com.example.recipes.entities.Product;
import com.example.recipes.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository repository;

    public ResponseEntity add(Product product) {

        if (!repository.existsByName(product.getName())) {
            repository.insert(product);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format(Constants.DUPLICATE_DATA, product.getClass().getName()));
        }
    }

    public ResponseEntity findById(String id) {
        if (repository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.FOUND).body(repository.findById(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format(Constants.NOT_FOUND, Product.class.getName(), id));
        }
    }

    public ResponseEntity update(Product product) {
        if (repository.existsById(product.getId())) {
            return ResponseEntity.ok(repository.save(product));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format(Constants.DUPLICATE_DATA, product.getClass().getName()));
        }
    }

    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }
}
