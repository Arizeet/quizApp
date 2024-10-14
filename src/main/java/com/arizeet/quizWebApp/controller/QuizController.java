package com.arizeet.quizWebApp.controller;


import com.arizeet.quizWebApp.model.Question;
import com.arizeet.quizWebApp.model.QuestionWrapper;
import com.arizeet.quizWebApp.model.Quiz;
import com.arizeet.quizWebApp.model.Response;
import com.arizeet.quizWebApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(category,numQ,title);
    }

//    @GetMapping("/get/{id}")    //return total quiz including all fields
//    public ResponseEntity<Quiz> getQuiz(@PathVariable Integer id){
//        try {
//            return quizService.getQuiz(id);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id,responses);
    }
}
