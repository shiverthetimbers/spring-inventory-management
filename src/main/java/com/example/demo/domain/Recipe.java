package com.example.demo.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private Product product;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, mappedBy = "recipe", orphanRemoval = true)
    private List<RecipeLine> recipeLines = new ArrayList<>();

    public Recipe() {
    }

    public long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<RecipeLine> getRecipeLines() {
        return recipeLines;
    }

//    public void setRecipeLines(List<RecipeLine> recipeLines) {
//        for (RecipeLine oldLine : this.recipeLines) {
//            oldLine.setRecipe(null);
//        }
//        this.recipeLines = recipeLines;
//        for (RecipeLine line : recipeLines) {
//            line.setRecipe(this);
//        }
//    }

    public void addRecipeLine(RecipeLine recipeLine) {
        recipeLines.add(recipeLine);
        recipeLine.setRecipe(this);
    }

    public void removeRecipeLine(RecipeLine recipeLine) {
        recipeLines.remove(recipeLine);
        recipeLine.setRecipe(null);
    }
}
