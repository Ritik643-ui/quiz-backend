package com.ritik.quiz.api;

public record AnswerRequest (int questionId,
        int selectedChoiceIndex){ }
