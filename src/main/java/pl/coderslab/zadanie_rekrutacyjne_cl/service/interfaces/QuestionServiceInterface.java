package pl.coderslab.zadanie_rekrutacyjne_cl.service.interfaces;

import pl.coderslab.zadanie_rekrutacyjne_cl.entity.Question;

import java.util.Optional;

public interface QuestionServiceInterface {
    void save(Question question);


    Optional<Question> findRandom();

}
