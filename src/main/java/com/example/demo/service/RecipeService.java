package com.example.demo.service;

import com.example.demo.domain.Recipe;
import com.example.demo.domain.RecipeLine;

import java.util.List;

/**
 *
 *
 *
 *
 */
public interface RecipeService {

    Recipe findRecipeByProductId(long productId);

    List<RecipeLine> getRecipeLines(long productId);

    void addRecipeLine(long productId, long partId, int quantity);

    void removeRecipeLine(long productId, long recipeLineId);

    void incrementPartCount(long productId, long recipeLineId);

    void decrementPartCount(long productId, long recipeLineId);

    void save (Recipe recipe);
}
