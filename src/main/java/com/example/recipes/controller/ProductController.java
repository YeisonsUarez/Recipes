package com.example.recipes.controller;

import com.example.recipes.entities.Product;
import com.example.recipes.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/Product")
@AllArgsConstructor
public class ProductController {


    private final ProductService service;

    @PostMapping()
    public ResponseEntity add(@RequestBody Product object) {
        return service.add(object);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("/all")
    public ResponseEntity all() {
        return service.getAll();
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody Product object) {
        return service.update(object);
    }


}
