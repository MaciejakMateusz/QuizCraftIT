package pl.coderslab.zadanie_rekrutacyjne_cl.service.interfaces;

import pl.coderslab.zadanie_rekrutacyjne_cl.entity.Answer;
import pl.coderslab.zadanie_rekrutacyjne_cl.entity.Question;

import java.util.List;

public interface AnswerServiceInterface {
    void save(Answer answer);

    List<Answer> findAllByQuestion(Question question);
}
