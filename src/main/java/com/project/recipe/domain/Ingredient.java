package com.project.recipe.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;

    @ManyToOne
    Recipe recipe;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMesure unitOfMesure;

    public Ingredient(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public UnitOfMesure getUnitOfMesure() {
        return unitOfMesure;
    }

    public void setUnitOfMesure(UnitOfMesure unitOfMesure) {
        this.unitOfMesure = unitOfMesure;
    }
}
