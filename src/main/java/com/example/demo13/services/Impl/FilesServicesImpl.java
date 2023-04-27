package com.example.demo13.services.Impl;

import com.example.demo13.services.FilesServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

 @Service
 public class FilesServicesImpl implements FilesServices {
     @Value("${path.to.data.file}")
     private String ingredientsFilePath;
     @Value("${name.of.data.file}")
     private String recipesFilePath;

     @Override
     public boolean saveToFile(String json) {
         return false;
     }

     @Override
     public String readFromFile() {
         return null;
     }

     @Override
     public File getDataFile() {
         return null;
     }

     @Override
     public boolean cleanDataFile() {
         return false;
     }

     @Override
     public boolean saveIngredientsToFile(String json) {
         try {
             cleanIngredientsFile();
             Files.writeString(Path.of(ingredientsFilePath), json);
             return true;
         } catch (IOException e) {
             return false;
         }
     }

     @Override
     public boolean saveRecipesToFile(String json) {
         try {
             cleanRecipesFile();
             Files.writeString(Path.of(recipesFilePath), json);
             return true;
         } catch (IOException e) {
             return false;
         }
     }

     @Override
     public String readIngredientsFromFile() {
         if (!Files.exists(Path.of(ingredientsFilePath))) {
             return null;
         }
         try {
             return Files.readString(Path.of(ingredientsFilePath));
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }

     @Override
     public String readRecipesFromFile() {
         if (!Files.exists(Path.of(recipesFilePath))) {
             return null;
         }
         try {
             return Files.readString(Path.of(recipesFilePath));
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }

     @Override
     public File getIngredientsFile() {
         return new File(ingredientsFilePath);
     }

     @Override
     public File getRecipesFile() {
         return new File(recipesFilePath);
     }

     @Override
     public boolean cleanIngredientsFile() {
         try {
             Path path = Path.of(ingredientsFilePath);
             Files.deleteIfExists(path);
             Files.createFile(path);
             return true;
         } catch (IOException e) {
             e.printStackTrace();
             return false;
         }
     }

     @Override
     public boolean cleanRecipesFile() {
         try {
             Path path = Path.of(recipesFilePath);
             Files.deleteIfExists(path);
             Files.createFile(path);
             return true;
         } catch (IOException e) {
             e.printStackTrace();
             return false;
         }
     }
 }