package com.example.demo13.services.Impl;


import com.example.demo13.model.Ingredients;
import com.example.demo13.services.FilesServices;
import com.example.demo13.services.IngredientServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Month;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
 public class IngredientServicesImpl implements IngredientServices {
    final private FilesServices filesServices;
    public IngredientServicesImpl(FilesServices filesServices) {
        this.filesServices = filesServices;
    }

    private static Map<Integer, Ingredients> ingredients = new LinkedHashMap<>();
    private static int id = 0;

    @Override
      public void addIngredient(Ingredients ingredient) {
        ingredients.put(id++, ingredient);
    }

     @Override
     public Ingredients getIngredient(int id) {
        return ingredients.get(id);
    }
    @Override
    public Ingredients editIngredient(int id, Ingredients ingredient) throws ClassNotFoundException {
        Ingredients ingredients1 = ingredients.get(id);
        saveToFile();
        if (ingredients1 == null) {
            throw new ClassNotFoundException("айди рецета не найдено" + id);
        }
        ingredients1.setName("dsad");
       ingredients1.setVolume(32);
       ingredients1.setUnitOfMeasurement("см");
        return ingredients1;
    }
    @Override
    public boolean deleteIngredient(int id) {
        ingredients.remove(id);
        return false;
    }
    @PostConstruct
    private void init() {
        readFromFile();
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            filesServices.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    private void readFromFile() {
        String json = filesServices.readFromFile();
        try {
            ingredients = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Ingredients>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
