package com.example.demo13.services;

import java.io.File;

 public interface FilesServices {

    boolean saveToFile(String json);

     String readFromFile();

     File getDataFile();

     boolean cleanDataFile();

  boolean saveIngredientsToFile(String json);

  boolean saveRecipesToFile(String json);

  String readIngredientsFromFile();

  String readRecipesFromFile();

  File getIngredientsFile();

  File getRecipesFile();

  boolean cleanIngredientsFile();

  boolean cleanRecipesFile();
 }
