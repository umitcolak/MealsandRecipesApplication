package com.sabanciuniv.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sabanciuniv.model.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
    Category findByCategoryName(String categoryName);
    List<Category> findByCategoryIdIn(List<String> categoryIds);
    List<Category> findAll();
    boolean existsByCategoryName(String categoryName);
}
