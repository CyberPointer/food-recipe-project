package com.project.recipe.controllers;

import com.project.recipe.domain.*;
import com.project.recipe.repositories.CategoryRepository;
import com.project.recipe.repositories.RecipeRepository;
import com.project.recipe.repositories.UnitOfMeasureRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Component
public class BootStrapData implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public BootStrapData(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @SneakyThrows
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("#BooStrapData- commandLineRunner");
        loadData();
    }


    private  void loadData() throws Exception {


     /*   2 ripe avocados
        1/4 teaspoon of salt, more to taste
        1 tablespoon fresh lime juice or lemon juice
        2 tablespoons to 1/4 cup of minced red onion or thinly sliced green onion
        1-2 serrano chiles, stems and seeds removed, minced
        2 tablespoons cilantro (leaves and tender stems), finely chopped
        A dash of freshly grated black pepper
        1/2 ripe tomato, seeds and pulp removed, chopped
        Red radishes or jicama, to garnish
        Tortilla chips, to serve*/

        Optional<UnitOfMeasure> optionalTeaspoonUnitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");

                    if (!optionalTeaspoonUnitOfMeasure.isPresent()) throw new Exception("Expected UOM not found");

        Optional<UnitOfMeasure> optionalTablespoonUnitOfMeasure = unitOfMeasureRepository.findByDescription("Tablespoon");

                    if (!optionalTablespoonUnitOfMeasure.isPresent()) throw new Exception("Expected UOM not found");

         Optional<UnitOfMeasure> optionalCupUnitOfMeasure     = unitOfMeasureRepository.findByDescription("Cup");

                    if (optionalCupUnitOfMeasure.isEmpty()) throw new Exception("Expected UOM not found");

        Optional<UnitOfMeasure> optionalPinchUnitOfMeasure     = unitOfMeasureRepository.findByDescription("Pinch");

                    if (optionalPinchUnitOfMeasure.isEmpty()) throw new Exception("Expected UOM not found");

        Optional<UnitOfMeasure> optionalOunceUnitOfMeasure     = unitOfMeasureRepository.findByDescription("Ounce");

                    if (optionalOunceUnitOfMeasure.isEmpty()) throw new Exception("Expected UOM not found");

        Optional<UnitOfMeasure> optionalAdashOfUnitOfMeasure     = unitOfMeasureRepository.findByDescription("A dash of");

                    if (optionalAdashOfUnitOfMeasure.isEmpty()) throw new Exception("Expected UOM not found");

        Optional<UnitOfMeasure> optionalRipeUnitOfMeasure = unitOfMeasureRepository.findByDescription("Ripe");

                    if (!optionalRipeUnitOfMeasure.isPresent()) throw new Exception("Expected UOM not found");

        Optional<UnitOfMeasure> optionalUniteUnitOfMeasure = unitOfMeasureRepository.findByDescription("Unit");

                    if (!optionalUniteUnitOfMeasure.isPresent()) throw new Exception("Expected UOM not found");

        Optional<UnitOfMeasure> optionalCloveUnitOfMeasure = unitOfMeasureRepository.findByDescription("Clove");

        if (!optionalCloveUnitOfMeasure.isPresent()) throw new Exception("Expected UOM not found");




        Optional<Category> optionalMexicanCategory = categoryRepository.findByDescription("Mexican");
                    if (optionalMexicanCategory.isEmpty()) throw new Exception("Expected category not found");

        Optional<Category> optionalAmericanCategory = categoryRepository.findByDescription("American");
                    if (optionalAmericanCategory.isEmpty()) throw new Exception("Expected category not found");


        Recipe guacamole = new Recipe();
        guacamole.getCategories().add(optionalMexicanCategory.get());
        guacamole.setDescription("Guacamole");
        guacamole.setCoockTime(10 );
        guacamole.setPrepTime(0);
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setDirection(" Guacamole is best eaten right after it's made. Like apples, avocados start to oxidize and turn brown once they've been cut");
        guacamole.setServings(2);
        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipeNotes("All you really need to make guacamole is ripe avocados and salt. " +
                "After that, a little lime or lemon juice—a splash of acidity—will help to balance the richness of the avocado. " +
               "Then if you want, add chopped cilantro, chiles, onion, and/or tomato ");

        guacamole.setNotes(guacamoleNotes);
        guacamoleNotes.setRecipe(guacamole);

        Ingredient avocado1 = new Ingredient();
        avocado1.setAmount(new BigDecimal("2"));
        avocado1.setDescription("avocado");
        avocado1.setUnitOfMesure(optionalRipeUnitOfMeasure.get());
        avocado1.setRecipe(guacamole);
        guacamole.getIngredients().add(avocado1);


        Ingredient freshLime = new Ingredient();
        freshLime.setAmount(new BigDecimal("1"));
        freshLime.setDescription("or lemon juice");
        freshLime.setUnitOfMesure(unitOfMeasureRepository.findByDescription("Tablespoon").get());
        freshLime.setRecipe(guacamole);
        guacamole.getIngredients().add(freshLime);

     //   recipeRepository.save(guacamole);


        Ingredient salt = new Ingredient();
        salt.setAmount(new BigDecimal("0.25"));
        salt.setDescription("salt more to taste");
        salt.setUnitOfMesure(unitOfMeasureRepository.findByDescription("Teaspoon").get());
        salt.setRecipe(guacamole);
        guacamole.getIngredients().add(salt);


        Ingredient mincedRedOnion = new Ingredient();
        mincedRedOnion.setAmount(new BigDecimal("1"));
        mincedRedOnion.setDescription(" mincedRedOnion to 1/4 cup of minced red onion or thinly sliced green onion");
        mincedRedOnion.setUnitOfMesure(unitOfMeasureRepository.findByDescription("Tablespoon").get());
        mincedRedOnion.setRecipe(guacamole);
        guacamole.getIngredients().add(mincedRedOnion);

        Ingredient serranoChiles = new Ingredient();
        serranoChiles.setAmount(new BigDecimal("1"));
        serranoChiles.setDescription(" serranoChiles stems and seeds removed, minced");
        serranoChiles.setUnitOfMesure(unitOfMeasureRepository.findByDescription("Tablespoon").get());
        serranoChiles.setRecipe(guacamole);
        guacamole.getIngredients().add(serranoChiles);


        Ingredient cilantro = new Ingredient();
        cilantro.setAmount(new BigDecimal("2"));
        cilantro.setDescription("cilantro (leaves and tender stems), finely chopped");
        cilantro.setUnitOfMesure(unitOfMeasureRepository.findByDescription("Tablespoon").get());
        cilantro.setRecipe(guacamole);
        guacamole.getIngredients().add(cilantro);


        Ingredient blackPepper = new Ingredient();
        blackPepper.setAmount(new BigDecimal("1"));
        blackPepper.setDescription("blackPepper freshly grated black pepper");
        blackPepper.setUnitOfMesure(unitOfMeasureRepository.findByDescription("A dash of").get());
        blackPepper.setRecipe(guacamole);
        guacamole.getIngredients().add(blackPepper);


        Ingredient tomato = new Ingredient();
        tomato.setAmount(new BigDecimal("1"));
        tomato.setDescription("ripe tomato, seeds and pulp removed, chopped");
        tomato.setUnitOfMesure(unitOfMeasureRepository.findByDescription("Ripe").get());
        tomato.setRecipe(guacamole);
        guacamole.getIngredients().add(tomato);

        Ingredient  redRadishes = new Ingredient();
        serranoChiles.setAmount(new BigDecimal("1"));
        serranoChiles.setDescription(" Red radishes or jicama, to garnish");
        serranoChiles.setUnitOfMesure(unitOfMeasureRepository.findByDescription("Unit").get());
        serranoChiles.setRecipe(guacamole);
        guacamole.getIngredients().add(serranoChiles);

        Ingredient  tortillachips = new Ingredient();
        tortillachips.setAmount(new BigDecimal("1"));
        tortillachips.setDescription(" Tortilla chips, to serve");
        tortillachips.setUnitOfMesure(unitOfMeasureRepository.findByDescription("Unit").get());
        tortillachips.setRecipe(guacamole);
        guacamole.getIngredients().add(tortillachips);

        recipeRepository.save(guacamole);

        Recipe spicyGrilledChickenTacos = new Recipe();
        spicyGrilledChickenTacos.getCategories().add(optionalMexicanCategory.get());
        spicyGrilledChickenTacos.setDescription("Spicy Grilled Chicken Tacos");
        spicyGrilledChickenTacos.setCoockTime(15);
        spicyGrilledChickenTacos.setPrepTime(20);
        spicyGrilledChickenTacos.setDifficulty(Difficulty.MODERATE);
        spicyGrilledChickenTacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        spicyGrilledChickenTacos.setDirection("Today's tacos are more purposeful – a deliberate meal instead of a secretive midnight snack! ");
        spicyGrilledChickenTacos.setServings(2);
        Notes spicyGrilledChickenTacosNotes = new Notes();
        spicyGrilledChickenTacosNotes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online.");

        spicyGrilledChickenTacos.setNotes(spicyGrilledChickenTacosNotes);
        spicyGrilledChickenTacosNotes.setRecipe(spicyGrilledChickenTacos);

//        2 tablespoons ancho chili powder
//        1 teaspoon dried oregano
//        1 teaspoon dried cumin
//        1 teaspoon sugar
//        1/2 teaspoon salt
//        1 clove garlic, finely chopped
//        1 tablespoon finely grated orange zest
//        3 tablespoons fresh-squeezed orange juice
//        2 tablespoons olive oil
//        4 to 6 skinless, boneless chicken thighs (1 1/4 pounds)



        Ingredient chiliPowder = new Ingredient();
        chiliPowder.setAmount(new BigDecimal("2"));
        chiliPowder.setDescription("chili Powder");
        chiliPowder.setUnitOfMesure(optionalTablespoonUnitOfMeasure.get());
        chiliPowder.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.getIngredients().add(chiliPowder);

        Ingredient oregano = new Ingredient();
        oregano.setAmount(new BigDecimal("1"));
        oregano.setDescription("dried oregano");
        oregano.setUnitOfMesure(optionalTeaspoonUnitOfMeasure.get());
        oregano.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.getIngredients().add(oregano);

        Ingredient cumin = new Ingredient();
        cumin.setAmount(new BigDecimal("1"));
        cumin.setDescription("dried cumin");
        cumin.setUnitOfMesure(optionalTeaspoonUnitOfMeasure.get());
        cumin.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.getIngredients().add(cumin);

        Ingredient sugar = new Ingredient();
        sugar.setAmount(new BigDecimal("1"));
        sugar.setDescription("sugar");
        sugar.setUnitOfMesure(optionalTeaspoonUnitOfMeasure.get());
        sugar.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.getIngredients().add(sugar);

        Ingredient saltChicken = new Ingredient();
        saltChicken.setAmount(new BigDecimal("0.5"));
        saltChicken.setDescription("salt");
        saltChicken.setUnitOfMesure(optionalTeaspoonUnitOfMeasure.get());
        saltChicken.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.getIngredients().add(saltChicken);

        Ingredient garlic = new Ingredient();
        garlic.setAmount(new BigDecimal("1"));
        garlic.setDescription("garlic finely chopped");
        garlic.setUnitOfMesure(optionalTeaspoonUnitOfMeasure.get());
        garlic.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.getIngredients().add(garlic);

        recipeRepository.save(spicyGrilledChickenTacos);


    }


}
