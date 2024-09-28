package com.csv_example.pojo_to_csv.controller;

import org.springframework.web.bind.annotation.RestController;

import com.csv_example.pojo_to_csv.models.UserModel;
import com.csv_example.pojo_to_csv.service.PojoCsvParserService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class PojoCsvParserController {

    @Autowired
    PojoCsvParserService service;

    @GetMapping("/writecsv")
    public ResponseEntity<String> writeCsv(@RequestParam String path, @RequestBody List<UserModel> users) {
        try {
            Path pathTowrite = Paths.get(path);
            String results = service.writeCsvFromBean(pathTowrite, users);
            return new ResponseEntity<String>(
                    results,
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(
                    "CSV writer failed",
                    HttpStatus.OK);
        }

    }

}
