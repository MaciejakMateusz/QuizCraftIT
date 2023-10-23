package pl.coderslab.quizcraft.service;

import org.springframework.stereotype.Service;
import pl.coderslab.quizcraft.entity.Answer;
import pl.coderslab.quizcraft.entity.Question;
import pl.coderslab.quizcraft.repository.AnswerRepository;
import pl.coderslab.quizcraft.service.interfaces.AnswerServiceInterface;

import java.util.List;

@Service
public class AnswerService implements AnswerServiceInterface {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public List<Answer> findAllByQuestionAndCorrectTrue(Question question) {
        return answerRepository.findAllByQuestionAndCorrectTrue(question);
    }

    @Override
    public List<Answer> findAllByQuestion(Question question) {
        return answerRepository.findAllByQuestion(question);
    }

    @Override
    public void save(Answer answer) {
        answerRepository.save(answer);
    }
}
