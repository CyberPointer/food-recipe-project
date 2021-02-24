package com.project.recipe.controllers;

import com.project.recipe.domain.Recipe;
import com.project.recipe.repositories.RecipeRepository;
import com.project.recipe.services.RecipeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

class IndexControllerTest {
    @Mock
    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    Model model;



    IndexController indexController;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        indexController = new IndexController(recipeService);

    }

    @Test
    void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));

    }

    @Test
   void getIndexPage() {

       Set<Recipe> recipes = new HashSet<>();
       Recipe recipe1 = new Recipe();
       recipe1.setId(1l);

       recipes.add(recipe1);
       recipes.add(new Recipe());

       Mockito.when(recipeService.getRecipes()).thenReturn(recipes);

       ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

       String returnPage =  indexController.getIndexPage(model);
       Assertions.assertEquals("index",returnPage );

       Mockito.verify(recipeService, Mockito.times(1)).getRecipes();
       Mockito.verify(model).addAttribute(Mockito.eq("recipes"), argumentCaptor.capture());

       Set<Recipe> setInIndexController = argumentCaptor.getValue();
       Assertions.assertEquals(2, setInIndexController.size());

    }
}