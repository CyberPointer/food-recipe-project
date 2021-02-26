package com.project.recipe.services;

import com.project.recipe.domain.Recipe;
import com.project.recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

class RecipeServiceImplTest {
    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipeByIdTest() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setId(1l);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        Mockito.when(recipeRepository.findById(ArgumentMatchers.anyLong())).thenReturn(recipeOptional);

        Optional<Recipe> returnedRecipe = recipeRepository.findById(1l);

        Assertions.assertNotNull(returnedRecipe.get());
        Mockito.verify(recipeRepository).findById(ArgumentMatchers.anyLong());
        Mockito.verify(recipeRepository,Mockito.never()).findAll();



    }

    @Test
    void getRecipes() {
        Recipe recipe = new Recipe();
        HashSet<Recipe> recipeData = new HashSet<>();
        recipeData.add(recipe);

        Mockito.lenient().when(recipeRepository.findAll()).thenReturn(recipeData);

        Set<Recipe> recipes = recipeService.getRecipes();
        Assertions.assertEquals(1,recipes.size());
        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
    }
}