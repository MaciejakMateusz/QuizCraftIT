package pl.coderslab.quizcraft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.quizcraft.entity.Answer;
import pl.coderslab.quizcraft.entity.Question;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByQuestion(Question question);

    List<Answer> findAllByQuestionAndCorrect(Question question, boolean correct);
}
