package com.example.demo13.services;


import com.example.demo13.model.Recipe;

import java.util.List;

public interface RecipeServices {


      void addRecipe(Recipe recipe);

      Recipe getRecipe(int id);


      Recipe editRecipe(int id, Recipe recipe) throws ClassNotFoundException;

      boolean deleteRecipe(int id);

     Object getAllRecipes();
     List<Recipe> getAllRecipesDownload();
 }
