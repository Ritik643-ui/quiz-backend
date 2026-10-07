package com.ritik.quiz.repository;

import com.ritik.quiz.domain.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository {
    /**
     * Retrieves every stored question.
     *
     * @return all available questions
     */
    List<Question> findAll();

    /**
     * Looks up a question by its stable ID.
     *
     * @param id question ID to find
     * @return the matching question, or an empty Optional when it does not exist
     */
    Optional<Question> findById(int id);
}
