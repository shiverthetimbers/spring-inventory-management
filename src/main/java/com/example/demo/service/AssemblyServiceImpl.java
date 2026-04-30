package com.example.demo.service;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.domain.Recipe;
import com.example.demo.domain.RecipeLine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssemblyServiceImpl implements AssemblyService{

    final RecipeService recipeService;
    final ProductService productService;
    private final PartService partService;

    public AssemblyServiceImpl(RecipeService recipeService, ProductService productService, PartService partService) {
        this.recipeService = recipeService;
        this.productService = productService;
        this.partService = partService;
    }

    @Override
    public int canAssemble(long productId, int quantity) {

        if (quantity < 1) {
            return 0;
        }

        List<RecipeLine> recipeLines = recipeService.getRecipeLines(productId);
        if (recipeLines.isEmpty()) {
            return 1;
        }

        for (RecipeLine each : recipeLines) {

            Part currPart = each.getPart();
            int allowableQuantity = currPart.getInv() - currPart.getMinInv();
            int requiredQuantity = each.getPartCount() * quantity;
            if (allowableQuantity < requiredQuantity) {

                return 2;
            }
        }

        return 3;
    }

    @Override
    public int canDisassemble(long productId, int quantity) {

        if (quantity < 1) {
            return 0;
        }

        Product product = productService.findById(productId);
        if (product.getInv() < quantity) {
            return 1;
        }

        List<RecipeLine> recipeLines = recipeService.getRecipeLines(productId);
        for (RecipeLine each : recipeLines) {

            Part currPart = each.getPart();
            int allowableQuantity = currPart.getMaxInv() - currPart.getInv();
            int requiredQuantity = each.getPartCount() * quantity;
            if (allowableQuantity < requiredQuantity) {

                return 2;
            }
        }

        return 3;
    }

    @Override
    public void assembleNumberOfProducts(long productId, int quantity) {

        if (canAssemble(productId, quantity) != 3) {
            throw new IllegalStateException("Cannot assemble requested quantity.");
        }

        Product product = productService.findById(productId);
        Recipe recipe = recipeService.findRecipeByProductId(productId);

        product.setInv(product.getInv() + quantity);

        for (RecipeLine each : recipe.getRecipeLines()) {

            Part currPart = each.getPart();
            currPart.setInv(currPart.getInv() - (each.getPartCount() * quantity));
            partService.save(currPart);
        }

        productService.save(product);
    }

    @Override
    public void disassembleNumberOfProducts(long productId, int quantity) {

        if (canDisassemble(productId, quantity) != 3) {
            throw new IllegalStateException("Cannot disassemble requested quantity.");
        }

        Product product = productService.findById(productId);
        Recipe recipe = recipeService.findRecipeByProductId(productId);

        product.setInv(product.getInv() - quantity);

        for (RecipeLine each : recipe.getRecipeLines()) {

            Part currPart = each.getPart();
            currPart.setInv(currPart.getInv() + (each.getPartCount() * quantity));
            partService.save(currPart);
        }

        productService.save(product);
    }
}
