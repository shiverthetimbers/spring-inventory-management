package com.example.demo.controllers;

import com.example.demo.domain.Product;
import com.example.demo.domain.Recipe;
import com.example.demo.domain.RecipeLine;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *
 *
 *
 *
 */
@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/addProduct")
    public String addProductDetails(Model model) {

        Product product = new Product();

        model.addAttribute("product", product);

        return "productForm";
    }

    @PostMapping("/saveProduct")
    public String submitForm(@Valid @ModelAttribute("product") Product product, Errors errors) {

        if (errors.hasErrors()) {
            return "productForm";
        }

        if (product.getRecipe() == null) {
            product.setRecipe(new Recipe());
        }
        productService.save(product);

        return "confirmationSaveProduct";
    }

    @GetMapping("/updateProduct")
    public String updateProduct(@RequestParam("productID") long id, Model model) {

        Product product = productService.findById(id);
        List<RecipeLine> recipeLines = product.getRecipe().getRecipeLines();

        model.addAttribute("product", product);
        model.addAttribute("recipeLines", recipeLines);

        return "productForm";
    }

    @GetMapping("/deleteproduct")
    public String deleteProduct(@RequestParam("productID") long id) {

        productService.deleteById(id);

        return "confirmationDeleteProduct";
    }

    @GetMapping("/products/{productID}/shipping")
    public String createShipment(@PathVariable("productID") long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);

        return "errorWIP";
    }
}
