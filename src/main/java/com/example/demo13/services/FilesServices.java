package com.example.demo13.services;

import java.io.File;

 public interface FilesServices {

    boolean saveToFile(String json);

     String readFromFile();

     File getDataFile();

     boolean cleanDataFile();
 }
