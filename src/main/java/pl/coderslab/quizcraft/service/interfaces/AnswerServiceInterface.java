package pl.coderslab.quizcraft.service.interfaces;

import pl.coderslab.quizcraft.entity.Answer;
import pl.coderslab.quizcraft.entity.Question;

import java.util.List;

public interface AnswerServiceInterface {
    void save(Answer answer);

    List<Answer> findAllByQuestionAndCorrectTrue(Question question);

    List<Answer> findAllByQuestion(Question question);
}
