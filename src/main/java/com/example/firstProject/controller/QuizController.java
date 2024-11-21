package com.example.firstProject.controller;

import com.example.firstProject.model.QuestionWrapper;
import com.example.firstProject.model.Quiz;
import com.example.firstProject.model.Response;
import com.example.firstProject.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    
    @Autowired
    QuizService quizService;

    @PostMapping ("create")/*parameters for using it to create the content of json file */
    public ResponseEntity<String> getQuiz (@RequestParam String category, @RequestParam int numQ,@RequestParam String title) {
        return quizService.createQuiz(category,numQ,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>>getQuizQuestions (@PathVariable int id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> responses) {
        return quizService.calculateResult(id,responses);
    }


}
