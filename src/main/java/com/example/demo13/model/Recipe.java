package com.example.demo13.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

  @Data
  @AllArgsConstructor
  public class Recipe {
      private String name;
      private int timeOfCooking;
      private ArrayList<Ingredients> ingredients = new ArrayList<>();
      private ArrayList<String> steps = new ArrayList<>();


}
