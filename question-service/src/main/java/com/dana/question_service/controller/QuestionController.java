package com.dana.question_service.controller;

import com.dana.question_service.model.Question;
import com.dana.question_service.model.QuestionWrapper;
import com.dana.question_service.model.Response;
import com.dana.question_service.service.QuestionService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    QuestionService questionservice;

    @Autowired
    Environment environment;

    @GetMapping("/allquestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        System.out.println(environment.getProperty("local.server.port"));
        return questionservice.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionservice.getQuestionsByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionservice.addQuestion(question);
    }

    //generate
    //getQuestions (questionid)
    //getscore

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,@RequestParam Integer numQuestions){
        return questionservice.getQuestionsForQuiz(categoryName,numQuestions);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionids){
        return questionservice.getQuestionsFromId(questionids);
    }

    @PostMapping("getscore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionservice.getScore(responses);
    }



}
