package pl.coderslab.quizcraft.service.interfaces;

import pl.coderslab.quizcraft.entity.Question;

import java.util.Optional;

public interface QuestionServiceInterface {
    void save(Question question);

    Optional<Question> findById(long id);

    Optional<Question> findRandom();

}
