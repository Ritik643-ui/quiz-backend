package com.ritik.quiz.api;

import java.util.List;


public record QuestionResponse(
        int id,
        String prompt,
        List<String> choices
) {
}
