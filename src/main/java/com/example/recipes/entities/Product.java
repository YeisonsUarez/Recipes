package com.example.recipes.entities;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Document
public class Product {

    @Id
    @Field
    public String id = String.valueOf(UUID.randomUUID());

    @NonNull
    public String name;

    @NonNull
    public String brand;

    @NonNull
    public BigDecimal price;

    public Double discountPercentage;

    public Boolean onSale = false;


}
