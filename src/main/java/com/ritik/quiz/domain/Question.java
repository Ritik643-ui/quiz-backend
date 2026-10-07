package com.ritik.quiz.domain;

import java.util.List;

public record Question(
        int id,
        String prompt,
        List<String> choices,
        int correctChoiceIndex)
{ }
