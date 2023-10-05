package pl.coderslab.zadanie_rekrutacyjne_cl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.zadanie_rekrutacyjne_cl.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
