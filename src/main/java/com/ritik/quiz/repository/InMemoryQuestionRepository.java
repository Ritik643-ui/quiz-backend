package com.ritik.quiz.repository;

import com.ritik.quiz.domain.Question;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryQuestionRepository implements QuestionRepository {

    private final List<Question> questions = List.of(
            new Question(1,"Capital of Nepal?",List.of("Kathmandu","Delhi","New york","Cape Town"),0),
            new Question(2,"Ritik's last name",List.of("Roshan","Sharma","Nepali","Magar"),1)

            );

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Question> findAll() {
       return questions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Question> findById(int id) {
      for(Question question: questions){
          if(question.id()==id){
              return Optional.of(question);

          }
      }
      return Optional.empty();

    }
}
