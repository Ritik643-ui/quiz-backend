package com.ritik.quiz.exception;

/**
 * Indicates that no stored question matches a requested question ID.
 */
public class QuestionNotFoundException extends RuntimeException {

    /**
     * Creates an exception for a question ID that could not be found.
     *
     * @param questionId the missing question ID
     */
    public QuestionNotFoundException(int questionId) {
        super("Question not found: " + questionId);
    }
}