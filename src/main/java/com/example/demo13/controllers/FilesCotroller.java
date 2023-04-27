package com.example.demo13.controllers;

import com.example.demo13.services.FilesServices;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo13.model.Ingredients;

import java.io.*;

 @RestController
 @RequestMapping("/file")
  public class FilesCotroller {
     private FilesServices filesServices;

     public FilesCotroller(FilesServices filesServices) {
        this.filesServices = filesServices;
    }
     @GetMapping("/export")
     public ResponseEntity<InputStreamResource> dowloadDataFile() throws FileNotFoundException {
         File file = filesServices.getDataFile();

         if (file.exists()) {
             InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
             return ResponseEntity.ok()
                     .contentType(MediaType.APPLICATION_JSON)
                     .contentLength(file.length())
                     .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"IgredientsLog.json\"")
                     .body(resource);
         } else {
             return ResponseEntity.noContent().build();
          }

     }

     @PostMapping(value = "/import",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
     public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile file) {
         filesServices.cleanDataFile();
         File dataFile = filesServices.getDataFile();
         try (FileOutputStream fos = new FileOutputStream(dataFile)){

             IOUtils.copy(file.getInputStream(), fos);
              return ResponseEntity.ok().build();
         } catch (IOException e) {
             e.printStackTrace();
         }
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
     }
 }
