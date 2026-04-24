package com.example.demo.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 *
 *
 *
 *
 */
@Entity
@Table(name="Products")
//@ValidProductPrice
//@ValidEnufParts
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    private String name;

    @OneToOne(mappedBy = "product", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Recipe recipe;

    @Min(value = 0, message = "Price value must be positive")
    private double price;

    @Min(value = 0, message = "Inventory value must be positive")
    private int inv;

    public Product() {
    }

    public Product(String name, double price, int inv) {
        this.name = name;
        this.price = price;
        this.inv = inv;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
        recipe.setProduct(this);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInv() {
        return inv;
    }

    public void setInv(int inv) {
        this.inv = inv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id == product.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
