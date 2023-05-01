package com.example.demo13.controllers;


import com.example.demo13.model.Ingredients;
import com.example.demo13.model.Recipe;
import com.example.demo13.services.IngredientServices;
import com.example.demo13.services.RecipeServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@RestController
 @RequestMapping("/recipe")
 public class RecipeController {
     private RecipeServices recipeServices;
    private IngredientServices ingredientServices;

    public RecipeController(RecipeServices recipeServices, IngredientServices ingredientServices) {
        this.recipeServices = recipeServices;
        this.ingredientServices = ingredientServices;
    }



     @PostMapping
     public void addRecipe(@RequestBody Recipe recipe) {
        recipeServices.addRecipe(recipe);
    }

     @GetMapping("/{id}")
     public Recipe getRecipe(@PathVariable int id) {
        return recipeServices.getRecipe(id);
    }

     @PutMapping("/{id}")
     public ResponseEntity<Recipe> editRecipe(@PathVariable int id,@RequestBody Recipe recipe) throws ClassNotFoundException {
 Recipe recipe2 = recipeServices.editRecipe(id, recipe);
         if (recipe2 == null) {
             ResponseEntity.notFound().build();
         }
        return ResponseEntity.ok(recipe2);
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteRecipe(@PathVariable int id) {
 if (recipeServices.deleteRecipe(id)){
     return ResponseEntity.ok().build();
     }
         return ResponseEntity.notFound().build();
 }
    @GetMapping("/all")
    public List<Ingredients> getAllRecipes() {
        return new ArrayList<>((Integer) recipeServices.getAllRecipes());
    }

    @GetMapping("/download")
    public void downloadRecipes(HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment;filename=recipes.txt");

        List<Recipe> recipes = recipeServices.getAllRecipesDownload();
        PrintWriter writer = response.getWriter();
        for (Recipe recipe : recipes) {
            writer.println(recipe.getName() + "\n");
            writer.println("Время приготовления: " + recipe.getTimeOfCooking() + ".\n");

            writer.println("Ингредиенты:\n");
            for (Ingredients ingredient : recipe.getIngredients()) {
                writer.println(ingredient.getName() + " - " + ingredient.getUnitOfMeasurement() + ".");
            }
            writer.println();
            writer.println("Инструкция приготовления:\n" + recipe.getSteps() + "\n\n");
        }
        writer.close();
    }
}




