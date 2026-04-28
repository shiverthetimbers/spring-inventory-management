package com.example.demo.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "RecipeLines")
public class RecipeLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Part part;

    @Min(value = 0, message = "Part count value must be positive")
    @Max(value = 10, message = "Part count max is 10")
    private int partCount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Recipe recipe;

    public RecipeLine() {
    }

    public RecipeLine(Part part, int partCount) {
        this.part = part;
        this.partCount = partCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public int getPartCount() {
        return partCount;
    }

    public void setPartCount(int partCount) {
        this.partCount = partCount;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
