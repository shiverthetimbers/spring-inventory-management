package com.example.demo.controllers;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.domain.RecipeLine;
import com.example.demo.service.PartService;
import com.example.demo.service.ProductService;
import com.example.demo.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RecipeController {

    private final RecipeService recipeService;
    private final ProductService productService;
    private final PartService partService;

    public RecipeController(RecipeService recipeService, ProductService productService, PartService partService) {
        this.recipeService = recipeService;
        this.productService = productService;
        this.partService = partService;
    }

    @GetMapping("/products/{productID}/recipe/edit")
    public String editRecipe(@PathVariable("productID") long productId, Model model) {

        Product product = productService.findById(productId);
        List<RecipeLine> recipeLines = recipeService.getRecipeLines(productId);
        List<Long> linePartIds = new ArrayList<>();
        List<Part> availParts = new ArrayList<>();

        for (RecipeLine line : recipeLines) {
            linePartIds.add(line.getPart().getId());
        }

        for (Part each : partService.findAll()) {
            if (!linePartIds.contains(each.getId())) {
                availParts.add(each);
            }
        }

        model.addAttribute("product", product);
        model.addAttribute("recipeLines", recipeLines);
        model.addAttribute("availParts", availParts);

        return "recipeForm";
    }

    @PostMapping("/products/{productID}/recipe/lines/add")
    public String addLineItemForRecipe(@PathVariable("productID") long productId, @RequestParam("partID") long partId) {

        recipeService.addRecipeLine(productId, partId, 1);

        return "redirect:/products/{productID}/recipe/edit";
    }

    @PostMapping("/products/{productID}/recipe/lines/remove")
    public String removeLineItemFromRecipe(@PathVariable("productID") long productId, @RequestParam("lineID") long lineId) {

        recipeService.removeRecipeLine(productId, lineId);


        return "redirect:/products/{productID}/recipe/edit";
    }

    @PostMapping("/products/{productID}/recipe/lines/{lineID}/increment")
    public String incrementLinePartCount(@PathVariable("productID") long productId, @PathVariable("lineID") long lineId) {

        recipeService.incrementPartCount(productId, lineId);

        return "redirect:/products/{productID}/recipe/edit";
    }

    @PostMapping("/products/{productID}/recipe/lines/{lineID}/decrement")
    public String decrementLinePartCount(@PathVariable("productID") long productId, @PathVariable("lineID") long lineId) {

        recipeService.decrementPartCount(productId, lineId);

        return "redirect:/products/{productID}/recipe/edit";
    }
}
