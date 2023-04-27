package com.example.demo13.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

 @RestController
 public class FirstController {
 @GetMapping("/")
     public String Start() {
        return "Приложение запузено";
    }
     @GetMapping("/info")
     public String page() {
         return "Название проекта: budgetapp " +
                 "Имя ученика: Егор " +
                 "дата создания: 14.03.2023 " +
                 "Данный проект будет про рецепты ";


     }
 }

