package com.ritik.quiz.service;

import com.ritik.quiz.api.QuestionResponse;
import com.ritik.quiz.domain.AnswerResult;
import com.ritik.quiz.domain.Question;
import com.ritik.quiz.exception.InvalidChoiceIndexException;
import com.ritik.quiz.exception.QuestionNotFoundException;
import com.ritik.quiz.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final ScoreService scoreService;


    /**
     * Creates the service with question storage and score tracking dependencies.
     *
     * @param questionRepository repository used to retrieve stored questions
     * @param scoreService service used to maintain the in-memory score
     */
    public QuestionService(QuestionRepository questionRepository, ScoreService scoreService) {
        this.questionRepository = questionRepository;
        this.scoreService = scoreService;
    }

    /**
     * Selects a random stored question and removes the correct answer before returning it.
     *
     * @return a client-safe representation of the selected question
     */
    public QuestionResponse getRandomQuestion(){
        List<Question> questions = questionRepository.findAll();
        int randomIndex = ThreadLocalRandom.current().nextInt(questions.size());

        Question randomQuestion=questions.get(randomIndex);

        return new QuestionResponse(randomQuestion.id(),randomQuestion.prompt()
                ,randomQuestion.choices());

        
    }

    /**
     * Checks a selected choice against the stored correct answer and updates the score when correct.
     *
     * @param questionId ID of the question being answered
     * @param selectedChoiceIndex zero-based index selected by the player
     * @return whether the answer was correct and the resulting score
     * @throws QuestionNotFoundException when no question exists for {@code questionId}
     * @throws InvalidChoiceIndexException when the selected index is outside the question's choice range
     */

    public AnswerResult checkAnswer(int questionId, int selectedChoiceIndex) {
        Optional<Question> questionOptional =
                questionRepository.findById(questionId);
        if (questionOptional.isEmpty()) {
            throw new QuestionNotFoundException(questionId);
        }
        Question question = questionOptional.get();

        if (selectedChoiceIndex < 0
                || selectedChoiceIndex >= question.choices().size()) {
            throw new InvalidChoiceIndexException(
                    selectedChoiceIndex,
                    question.choices().size()
            );
        }

        boolean correct =
                selectedChoiceIndex == question.correctChoiceIndex();
        int currentScore;
        if (correct) {
            currentScore = scoreService.incrementScore();
        } else {
            currentScore = scoreService.getScore();
        }

        return new AnswerResult(correct, currentScore);

}

    /**
     * Resets the current in-memory quiz score to zero.
     *
     * @return the reset score, always zero
     */
    public int restartQuiz(){
        return scoreService.resetScore();
    }
}
