package com.project.recipe.services;

import com.project.recipe.commands.RecipeCommand;
import com.project.recipe.domain.Recipe;


import java.util.Set;


public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    RecipeCommand findCommandById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(Long idToDelete);
}