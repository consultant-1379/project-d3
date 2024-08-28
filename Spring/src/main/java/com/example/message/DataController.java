package com.example.message;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {

//    @Autowired
//    private DataServiceImpl dataSI;

    @Autowired
    private JenkinsRepository repo;

//    @Autowired
//    private TestRepository jRepo;
//
//    @GetMapping("/all")
//    public ResponseEntity<List<TestData>> viewHomePage() {
//        return ResponseEntity.ok(jRepo.findAll());
//    }

    @GetMapping(value ="/jenkin", produces={"application/json"})
    public ResponseEntity<List<JenkinsData>> viewJenkinData() {
        return ResponseEntity.ok(repo.findAll());
    }


}
