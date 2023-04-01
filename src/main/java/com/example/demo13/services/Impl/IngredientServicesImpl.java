package com.example.demo13.services.Impl;


import com.example.demo13.model.Ingredients;
import com.example.demo13.services.IngredientServices;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
 public class IngredientServicesImpl implements IngredientServices {
     private static Map<Integer, Ingredients> ingredients = new LinkedHashMap<>();
     @Override
      public void addIngredient(Ingredients ingredient) {
        ingredients.put(ingredient.getId(), ingredient);
    }

     @Override
     public Ingredients getIngredient(int id) {
        return ingredients.get(id);
    }
    @Override
    public Ingredients editIngredient(int id, Ingredients ingredient) throws ClassNotFoundException {
        Ingredients ingredients1 = ingredients.get(id);
        if (ingredients1 == null) {
            throw new ClassNotFoundException("айди рецета не найдено" + id);
        }
        ingredients1.setName("dsad");
       ingredients1.setId(2);
       ingredients1.setVolume(32);
       ingredients1.setUnitOfMeasurement("см");
        return ingredients1;
    }
    @Override
    public boolean deleteIngredient(int id) {
        ingredients.remove(id);
        return false;
    }
}
