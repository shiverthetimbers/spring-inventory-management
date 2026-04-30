package com.example.demo.controllers;

import com.example.demo.domain.Product;
import com.example.demo.domain.RecipeLine;
import com.example.demo.service.AssemblyService;
import com.example.demo.service.ProductService;
import com.example.demo.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AssemblyController {

    private final RecipeService recipeService;
    private final ProductService productService;
    private final AssemblyService assemblyService;

    public AssemblyController(RecipeService recipeService, ProductService productService, AssemblyService assemblyService) {
        this.recipeService = recipeService;
        this.productService = productService;
        this.assemblyService = assemblyService;
    }

    @GetMapping("/products/{productID}/assembly")
    public String loadAssemblyForm(@PathVariable("productID") long productId, Model model) {

        Product product = productService.findById(productId);
        List<RecipeLine> recipeLines = recipeService.getRecipeLines(productId);
        int canAssemble = assemblyService.canAssemble(productId, 1);
        int canDisassemble = assemblyService.canDisassemble(productId, 1);



        model.addAttribute("product", product);
        model.addAttribute("recipeLines", recipeLines);
        model.addAttribute("canAssemble", canAssemble);
        model.addAttribute("canDisassemble", canDisassemble);


        return "assemblyForm";
    }

    @PostMapping("/products/{productID}/assembly/assemble")
    public String assembleProductFromRecipe(@PathVariable("productID") long productId, @RequestParam("quantity") int quantity, RedirectAttributes redirectAttributes) {

        Product product = productService.findById(productId);
        int canAssemble = assemblyService.canAssemble(productId, quantity);

        return switch (canAssemble) {
            case 0 -> {

                redirectAttributes.addFlashAttribute("errorMessage", "Requested quantity must be greater than 0");

                yield "redirect:/products/{productID}/assembly";
            }
            case 1 -> {

                redirectAttributes.addFlashAttribute("errorMessage", product.getName() + " does not have any associated parts for assembly");

                yield "redirect:/products/{productID}/assembly";
            }
            case 2 -> {

                redirectAttributes.addFlashAttribute("errorMessage", "Assembling " + quantity + " of " + product.getName() + " would exceed part inventory or inventory minimums");

                yield "redirect:/products/{productID}/assembly";
            }
            case 3 -> {

                redirectAttributes.addFlashAttribute("successMessage", quantity + " of " + product.getName() + " have been assembled from part inventory");

                assemblyService.assembleNumberOfProducts(productId, quantity);

                yield "redirect:/products/{productID}/assembly";
            }
            default -> "errorGeneral";
        };
    }

    @PostMapping("/products/{productID}/assembly/disassemble")
    public String disassembleProductFromRecipe(@PathVariable("productID") long productId, @RequestParam("quantity") int quantity, RedirectAttributes redirectAttributes) {

        Product product = productService.findById(productId);
        int canDisassemble = assemblyService.canDisassemble(productId, quantity);

        return switch (canDisassemble) {
            case 0 -> {

                redirectAttributes.addFlashAttribute("errorMessage", "Requested quantity must be greater than 0");

                yield "redirect:/products/{productID}/assembly";
            }
            case 1 -> {

                redirectAttributes.addFlashAttribute("errorMessage", "There are not enough of " + product.getName() + " to disassemble " + quantity + " products");

                yield "redirect:/products/{productID}/assembly";
            }
            case 2 -> {

                redirectAttributes.addFlashAttribute("errorMessage", "Disassembling " + quantity + " " + product.getName() + " would exceed part inventory maximums");

                yield "redirect:/products/{productID}/assembly";
            }
            case 3 -> {

                redirectAttributes.addFlashAttribute("successMessage", quantity + " of " + product.getName() + " have been disassembled and associated parts returned to inventory");

                assemblyService.disassembleNumberOfProducts(productId, quantity);

                yield "redirect:/products/{productID}/assembly";
            }
            default -> "errorGeneral";
        };
    }
}
