package com.example.demo13.services.Impl;


import com.example.demo13.model.Ingredients;
import com.example.demo13.model.Recipe;
import com.example.demo13.services.FilesServices;
import com.example.demo13.services.RecipeServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

  @Service
  public class RecipeServicesImpl implements RecipeServices {

      private static Map<Integer, Recipe> recipes = new LinkedHashMap<>();
      private static int id = 0;
      final private FilesServices filesServices;

      public RecipeServicesImpl(FilesServices filesServices) {
         this.filesServices = filesServices;
     }


      @Override
      public void addRecipe(Recipe recipe) {
        recipes.put(id++, recipe);
          saveToFile();
    }

      @Override
      public Recipe getRecipe(int id) {
        return recipes.get(id);
    }
      @PostConstruct
      private void init() {
         readFromFile();
     }

 @Override
      public Recipe editRecipe(int id, Recipe recipe) throws ClassNotFoundException {
          Recipe recipe1 = recipes.get(id);
          if (recipe1 == null) {
              throw new ClassNotFoundException("айди рецета не найдено" + id);
          }
          recipe1.setName(recipe.getName());
          recipe1.setTimeOfCooking(recipe.getTimeOfCooking());
          recipes.put(id, recipe1);
     saveToFile();
     return recipe1;
 }

      @Override
      public boolean deleteRecipe(int id) {
          recipes.remove(id);
          saveToFile();
          return true;
      }

      @Override
      public Object getAllRecipes() {
          return null;
      }

      private void saveToFile() {
          try {
              String json = new ObjectMapper().writeValueAsString(recipes);
              filesServices.saveToFile(json);
          } catch (JsonProcessingException e) {
              throw new RuntimeException(e);
          }
     }
      private void readFromFile() {
          String json = filesServices.readFromFile();
          try {
              recipes = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Recipe>>() {
              });
          } catch (JsonProcessingException e) {
              throw new RuntimeException(e);
          }
      }
      @Override
      public List<Recipe> getAllRecipesDownload() {
          return new ArrayList<>(recipes.values());
      }
  }
