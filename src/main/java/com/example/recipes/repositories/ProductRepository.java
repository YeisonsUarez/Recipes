package com.example.recipes.repositories;


import com.example.recipes.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findById(String id);


    Boolean existsByName(String name);
}
