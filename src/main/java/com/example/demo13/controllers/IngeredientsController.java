package com.example.demo13.controllers;


import com.example.demo13.model.Ingredients;
import com.example.demo13.services.IngredientServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/id")
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
}
