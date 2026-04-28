package com.example.demo.repositories;


import com.example.demo.domain.RecipeLine;
import org.springframework.data.repository.CrudRepository;

public interface RecipeLineRepository extends CrudRepository<RecipeLine, Long> {
}
