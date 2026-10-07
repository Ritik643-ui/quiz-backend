package com.ritik.quiz.repository;

import com.ritik.quiz.domain.Question;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class JdbcQuestionRepository implements QuestionRepository{

    private final JdbcTemplate jdbcTemplate;

    /**
     * Creates the PostgreSQL-backed question repository.
     *
     * @param jdbcTemplate template used to execute SQL queries
     */

    public JdbcQuestionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Retrieves the ordered choice text for a question.
     *
     * @param questionId ID of the question whose choices are requested
     * @return choices ordered by their choice index
     */

    private List<String> findChoicesByQuestionId (int questionId){

        String sql="SELECT choice_text\n" +
                "FROM choices\n" +
                "WHERE question_id = ?\n" +
                "ORDER BY choice_index";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNumber) ->
                        resultSet.getString("choice_text"),
                questionId
        );




    }
    /**
     * Retrieves all questions and their ordered choices.
     *
     * @return all stored questions ordered by ID
     */
    @Override
    public List<Question> findAll() {
        String sql = "SELECT id, prompt, correct_choice_index " +
                "FROM questions " +
                "ORDER BY id";

        return jdbcTemplate.query(
                sql,
                (resultSet, rowNumber) -> {
                    int id = resultSet.getInt("id");

                    return new Question(
                            id,
                            resultSet.getString("prompt"),
                            findChoicesByQuestionId(id),
                            resultSet.getInt("correct_choice_index")
                    );
                }
        );
    }
    @Override
    public Optional<Question> findById(int thisId) {

        String sql = " SELECT id, prompt, correct_choice_index\n" +
                "FROM questions\n" +
                "WHERE id = ? ";
        List<Question> list = jdbcTemplate.query(sql, (ResultSet resultSet, int rowNumber) -> {
                    int id = resultSet.getInt("id");
                    return new Question(id,
                            resultSet.getString("prompt"),
                            findChoicesByQuestionId(id),
                            resultSet.getInt("correct_choice_index"));

                }, thisId);
        if(list.isEmpty()){return Optional.empty();}
        else return  Optional.of(list.get(0));
    }



}
