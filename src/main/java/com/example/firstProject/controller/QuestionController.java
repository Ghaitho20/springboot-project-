package com.example.firstProject.controller;


import com.example.firstProject.model.Question;
import com.example.firstProject.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;


    @GetMapping("allQuestion")
    public ResponseEntity<List<Question>> getallQuestion() {
        return questionService.getAllQuestions();
    }


    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>>findByCategory(@PathVariable String category) {
        return questionService.findByCategory(category);
    }

    @PostMapping("add")/*this parameter(JSON QuestionObject)  will be injected into the data base */
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return (questionService.addQuestion(question));

    }







}
