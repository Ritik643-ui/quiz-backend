package com.ritik.quiz.exception;


import org.springframework.http.HttpStatus;
import com.ritik.quiz.api.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Converts a missing-question exception into a 404 JSON response.
     *
     * @param questionNotFoundException exception containing the missing-question message
     * @return the error response sent to the client
     */
    @ExceptionHandler(QuestionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleQuestionNotFound(QuestionNotFoundException questionNotFoundException)
    {
    return new ErrorResponse(questionNotFoundException.getMessage());

    }

    /**
     * Converts an invalid choice-index exception into a 400 JSON response.
     *
     * @param invalidChoiceIndexException exception containing the invalid-index details
     * @return the error response sent to the client
     */

    @ExceptionHandler(InvalidChoiceIndexException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidChoiceIndex(InvalidChoiceIndexException invalidChoiceIndexException)
    {
        return new ErrorResponse(invalidChoiceIndexException.getMessage());
    }
}
