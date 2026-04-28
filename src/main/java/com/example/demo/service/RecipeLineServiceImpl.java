package com.example.demo.service;

import com.example.demo.domain.RecipeLine;
import com.example.demo.repositories.RecipeLineRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeLineServiceImpl implements RecipeLineService {

    private final RecipeLineRepository recipeLineRepository;

    public RecipeLineServiceImpl(RecipeLineRepository recipeLineRepository) {
        this.recipeLineRepository = recipeLineRepository;
    }

    @Override
    public RecipeLine findById(long recipeLineId) {

        Optional<RecipeLine> optionalRecipeLine = recipeLineRepository.findById(recipeLineId);

        RecipeLine recipeLine;
        if (optionalRecipeLine.isPresent()) {
            recipeLine = optionalRecipeLine.get();
        } else {
            throw new RuntimeException("Could not find RecipeLine with id - " + recipeLineId);
        }

        return recipeLine;
    }
}
