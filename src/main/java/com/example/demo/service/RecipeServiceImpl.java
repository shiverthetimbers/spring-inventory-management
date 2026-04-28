package com.example.demo.service;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.domain.Recipe;
import com.example.demo.domain.RecipeLine;
import com.example.demo.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final ProductService productService;
    private final RecipeRepository recipeRepository;
    private final PartService partService;
    private final RecipeLineService recipeLineService;

    public RecipeServiceImpl(ProductService productService, RecipeRepository recipeRepository, PartService partService, RecipeLineService recipeLineService) {
        this.productService = productService;
        this.recipeRepository = recipeRepository;
        this.partService = partService;
        this.recipeLineService = recipeLineService;
    }

    @Override
    public Recipe findRecipeByProductId(long productId) {

        Product product = productService.findById(productId);
        Optional<Recipe> optionalRecipe = Optional.ofNullable(product.getRecipe());

        Recipe recipe;
        if (optionalRecipe.isPresent()) {
            recipe = optionalRecipe.get();
        } else {
            recipe = new Recipe();
            product.setRecipe(recipe);
        }

        return recipe;
    }

    @Override
    public List<RecipeLine> getRecipeLines(long productId) {

        Product product = productService.findById(productId);

        return new ArrayList<>(product.getRecipe().getRecipeLines());
    }

    @Override
    public void addRecipeLine(long productId, long partId, int quantity) {

        Recipe recipe = findRecipeByProductId(productId);
        Part part = partService.findById(partId);
        RecipeLine newLine = new RecipeLine(part, quantity);

        recipe.addRecipeLine(newLine);
        productService.save(productService.findById(productId));
    }

    @Override
    public void removeRecipeLine(long productId, long recipeLineId) {

        Recipe recipe = findRecipeByProductId(productId);
        RecipeLine oldLine = recipeLineService.findById(recipeLineId);

        recipe.removeRecipeLine(oldLine);
        productService.save(productService.findById(productId));
    }

    @Override
    public void incrementPartCount(long productId, long recipeLineId) {

        RecipeLine recipeLine = recipeLineService.findById(recipeLineId);

        if (recipeLine.getPartCount() < 10) {
            recipeLine.setPartCount(recipeLine.getPartCount() + 1);
        }

        productService.save(productService.findById(productId));
    }

    @Override
    public void decrementPartCount(long productId, long recipeLineId) {

        RecipeLine recipeLine = recipeLineService.findById(recipeLineId);

        if (recipeLine.getPartCount() > 1) {
            recipeLine.setPartCount(recipeLine.getPartCount() - 1);
        } else {
            removeRecipeLine(productId, recipeLineId);
        }

        productService.save(productService.findById(productId));
    }

    @Override
    public void save(Recipe recipe) {
        recipeRepository.save(recipe);
    }
}
