package com.example.demo.domain;

import com.example.demo.validators.ValidInventory;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 *
 *
 */
@Entity
@ValidInventory
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="partType",discriminatorType = DiscriminatorType.INTEGER)
@Table(name="Parts")
public abstract class Part implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    @Min(value = 0, message = "Price value must be positive")
    private double price;

    @Min(value = 0, message = "Inventory value must be positive")
    private int inv;

    @Min(value = 0, message = "Minimum value must be positive")
    private int minInv;

    @Min(value = 0, message = "Maximum value must be positive")
    private int maxInv;

    @OneToMany(mappedBy = "part")
    private List<RecipeLine> assocRecipeLines = new ArrayList<>();

    public Part() {
    }

    public Part(String name, double price, int inv, int minInv, int maxInv) {
        this.name = name;
        this.price = price;
        this.inv = inv;
        this.minInv = minInv;
        this.maxInv = maxInv;
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

    public int getMinInv() {
        return minInv;
    }

    public void setMinInv(int minInv) {
        this.minInv = minInv;
    }

    public int getMaxInv() {
        return maxInv;
    }

    public void setMaxInv(int maxInv) {
        this.maxInv = maxInv;
    }

    public List<RecipeLine> getAssocRecipeLines() {
        return assocRecipeLines;
    }

    public void addAssocRecipeLine(RecipeLine recipeLine) {
        assocRecipeLines.add(recipeLine);
        recipeLine.setPart(this);
    }

    public void removeAssocRecipeLine(RecipeLine recipeLine) {
        assocRecipeLines.remove(recipeLine);
        recipeLine.setPart(null);
    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Part part = (Part) o;
//
//        return id == part.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return Long.hashCode(id);
//    }
}
