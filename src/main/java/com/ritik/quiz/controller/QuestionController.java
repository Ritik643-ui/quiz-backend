package com.ritik.quiz.controller;

import com.ritik.quiz.api.AnswerRequest;
import com.ritik.quiz.api.AnswerResponse;
import com.ritik.quiz.api.QuestionResponse;
import com.ritik.quiz.api.RestartResponse;
import com.ritik.quiz.domain.AnswerResult;
import com.ritik.quiz.service.QuestionService;
import org.springframework.web.bind.annotation.*;


@RestController
public class QuestionController {
    private final QuestionService questionService;

    /**
     * Creates the controller with the service that performs quiz operations.
     *
     * @param questionService service used to retrieve questions and check answers
     */
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }


    /**
     * Handles requests for a random question that does not expose its correct answer.
     *
     * @return a client-safe random question
     */
    @GetMapping("/api/questions/random")
    public QuestionResponse question() {
       return questionService.getRandomQuestion();
    }

    /**
     * Checks a submitted answer and returns whether it was correct with the current score.
     *
     * @param answerRequest submitted question ID and selected choice index
     * @return the answer result formatted for the HTTP API
     */
    @PostMapping("/api/answers")
    public AnswerResponse answer(@RequestBody AnswerRequest answerRequest){

        AnswerResult answerResult=questionService.checkAnswer(answerRequest.questionId(),answerRequest.selectedChoiceIndex());

        return new AnswerResponse(answerResult.correct(),answerResult.score());


    }

    /**
     * Starts a fresh quiz by resetting the current in-memory score.
     *
     * @return a response containing the reset score
     */

    @PostMapping("/api/quiz/restart")
    public RestartResponse restart(){
        int result=questionService.restartQuiz();
        return new RestartResponse(result);


    }
}
