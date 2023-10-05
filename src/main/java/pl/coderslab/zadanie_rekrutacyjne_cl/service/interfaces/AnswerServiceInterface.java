package pl.coderslab.zadanie_rekrutacyjne_cl.service.interfaces;

import pl.coderslab.zadanie_rekrutacyjne_cl.entity.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerServiceInterface {
    void save(Answer answer);
    Optional<Answer> findById(Long id);
    List<Answer> findAll();
}
