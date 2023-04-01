package com.example.demo13.services.Impl;


import com.example.demo13.model.Recipe;
import com.example.demo13.services.RecipeServices;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

 @Service
 public class RecipeServicesImpl implements RecipeServices {

     private static Map<Integer, Recipe> recipes = new LinkedHashMap<>();
     private static int id = 0;



     @Override
     public void addRecipe(Recipe recipe) {
        recipes.put(id++, recipe);
    }

     @Override
     public Recipe getRecipe(int id) {
        return recipes.get(id);
    }

@Override
     public Recipe editRecipe(int id, Recipe recipe) throws ClassNotFoundException {
         Recipe recipe1 = recipes.get(id);
         if (recipe1 == null) {
             throw new ClassNotFoundException("айди рецета не найдено" + id);
         }
         recipe1.setName("dsad");
         recipe1.setTimeOfCooking(321);
         recipes.put(id, recipe1);
    return recipe1;
}

     @Override
     public boolean deleteRecipe(int id) {
         recipes.remove(id);
         return false;
     }

 }
