package com.ritik.quiz.exception;

public class InvalidChoiceIndexException extends RuntimeException {

    /**
     * Creates an exception for a selected choice outside the valid range.
     *
     * @param selectedChoiceIndex the invalid zero-based choice index
     * @param choiceCount the number of choices available for the question
     */
    public InvalidChoiceIndexException(
            int selectedChoiceIndex,
            int choiceCount
    ) {
        super(
                "Invalid choice index: " + selectedChoiceIndex
                        + ". Expected a value from 0 to "
                        + (choiceCount - 1) + "."
        );
    }


}