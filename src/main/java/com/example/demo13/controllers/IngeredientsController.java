package com.example.demo13.controllers;


import com.example.demo13.model.Ingredients;
import com.example.demo13.services.IngredientServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
 @RequestMapping("/ingredients")
 public class IngeredientsController {

     private IngredientServices ingredientServices;

     public IngeredientsController(IngredientServices ingredientServices) {
         this.ingredientServices = ingredientServices;
     }

     @PostMapping
     public void addIngredient(@RequestBody Ingredients ingredient) {
        ingredientServices.addIngredient(ingredient);
    }

     @GetMapping("/{id}")
     @Operation(
             summary = "Id ингредиента"
     )
     @ApiResponses(
             value = {
                     @ApiResponse(
                             responseCode = "200",
                             description = "ID были найдены",
                             content = {
                                     @Content(
                                             mediaType = "application/json",
                                             array = @ArraySchema(schema = @Schema(implementation = Ingredients.class))
                                     )
                             }



                     )
             }
     )
     public Ingredients getIngredient(@PathVariable int id) {
        return ingredientServices.getIngredient(id);
    }
     @PutMapping("/{id}")
     public ResponseEntity<Ingredients> editIngredients(@PathVariable int id, @RequestBody Ingredients ingredients) throws ClassNotFoundException {
         Ingredients ingredients2 = ingredientServices.editIngredient(id, ingredients);
         if (ingredients2 == null) {
             ResponseEntity.notFound().build();
         }
         return ResponseEntity.ok(ingredients2);
     }
     @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteRIngredient(@PathVariable int id) {
         if (ingredientServices.deleteIngredient(id)){
             return ResponseEntity.ok().build();
         }
         return ResponseEntity.notFound().build();
     }
     @GetMapping("/all")
     public List<Ingredients> getAllIngredients() {
         return new ArrayList<>((Integer) ingredientServices.getAllIngredients());
     }
    }