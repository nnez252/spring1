package com.example.demo13.controllers;


import com.example.demo13.model.Ingredients;
import com.example.demo13.model.Recipe;
import com.example.demo13.services.RecipeServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
 @RequestMapping("/recipe")
 public class RecipeController {
     private RecipeServices recipeServices;

     public RecipeController(RecipeServices recipeServices) {
        this.recipeServices = recipeServices;
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
     }


