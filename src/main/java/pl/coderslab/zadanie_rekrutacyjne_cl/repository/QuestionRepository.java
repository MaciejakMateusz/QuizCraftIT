package pl.coderslab.zadanie_rekrutacyjne_cl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.zadanie_rekrutacyjne_cl.entity.Question;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT q FROM Question q ORDER BY RAND() LIMIT 1")
    Optional<Question> findRandomQuestion();
}
