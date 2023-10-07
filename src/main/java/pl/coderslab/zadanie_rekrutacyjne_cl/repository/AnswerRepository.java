package pl.coderslab.zadanie_rekrutacyjne_cl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.zadanie_rekrutacyjne_cl.entity.Answer;
import pl.coderslab.zadanie_rekrutacyjne_cl.entity.Question;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByQuestion(Question question);
}
