package pl.coderslab.zadanie_rekrutacyjne_cl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.zadanie_rekrutacyjne_cl.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
