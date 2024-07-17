package com.dana.quiz_service.controller;


import com.dana.quiz_service.model.QuestionWrapper;
import com.dana.quiz_service.model.QuizDTO;
import com.dana.quiz_service.model.Response;
import com.dana.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizservice;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO) {
        return quizservice.createQuiz(quizDTO.getCategoryName(),quizDTO.getNumQuestions(),quizDTO.getTitle());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity <List<QuestionWrapper>> getQuizQuestions(@PathVariable int id) {
        return quizservice.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> responses) {
        return quizservice.calculateResult(id,responses);
    }
}
