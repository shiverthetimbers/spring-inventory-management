package com.example.demo.controllers;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.domain.Recipe;
import com.example.demo.domain.RecipeLine;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
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

    @GetMapping("/addProductDetails")
    public String addProductDetails(Model model) {

        Product product = new Product();
        Recipe recipe = product.getRecipe();

        List<RecipeLine> recipeLines = recipe.getRecipeLines();

        model.addAttribute("product", product);
        model.addAttribute("recipeLines", recipeLines);

        return "productDetailsForm";
    }

    @PostMapping("/addProductDetails")
    public String submitForm(@Valid @ModelAttribute("product") Product product, Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "productDetailsForm";
        }

            return "confirmationAddProduct";
    }

    @GetMapping("/updateProductDetails")
    public String updateProductDetails(@RequestParam("productID") long id, Model model) {

        Product product = productService.findById(id);
        List<RecipeLine> recipeLines = product.getRecipe().getRecipeLines();

        model.addAttribute("product", product);
        model.addAttribute("recipeLines", recipeLines);

        return "productDetailsForm";
    }
//
//    @GetMapping("/deleteproduct")
//    public String deleteProduct(@RequestParam("productID") long id) {
//
//        Product product = productService.findById(id);
//
//        for(Part part:product2.getParts()){
//            part.getProducts().remove(product2);
//            partService.save(part);
//        }
//        product2.getParts().removeAll(product2.getParts());
//        productService.save(product2);
//        productService.deleteById(id);
//
//        return "confirmationDeleteProduct";
//    }
//
//    @GetMapping("/buyProduct")
//    public String buyProduct(@RequestParam("productID") long id) {
//        boolean success = productService.buyProduct(id);
//        return success ? "confirmationBuyProduct" : "failureBuyProduct";
//    }
}
