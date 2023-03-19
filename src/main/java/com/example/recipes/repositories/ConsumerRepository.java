package com.example.recipes.repositories;

import com.example.recipes.entities.Consumer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ConsumerRepository extends MongoRepository<Consumer, String> {

    Optional<Consumer> findByEmail(String email);

}
