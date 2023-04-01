package com.example.demo13.services;


import com.example.demo13.model.Ingredients;

public interface IngredientServices {


     void addIngredient(Ingredients ingredient);

     Ingredients getIngredient(int id);


     Ingredients editIngredient(int id, Ingredients ingredient) throws ClassNotFoundException;

     boolean deleteIngredient(int id);

 }
