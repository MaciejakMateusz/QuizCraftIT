package pl.coderslab.quizcraft.service;

import org.springframework.stereotype.Service;
import pl.coderslab.quizcraft.entity.Answer;
import pl.coderslab.quizcraft.entity.Question;
import pl.coderslab.quizcraft.exception.CustomException;
import pl.coderslab.quizcraft.repository.QuestionRepository;
import pl.coderslab.quizcraft.service.interfaces.QuestionServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService implements QuestionServiceInterface {

    private final QuestionRepository questionRepository;
    private final AnswerService answerService;

    public QuestionService(QuestionRepository questionRepository, AnswerService answerService) {
        this.questionRepository = questionRepository;
        this.answerService = answerService;
    }

    @Override
    public void save(Question question) {
        questionRepository.save(question);
    }

    @Override
    public Optional<Question> findById(long id) {
        return questionRepository.findById(id);
    }

    @Override
    public Optional<Question> findRandom() {

        List<Answer> answers;
        Optional<Question> randomQuestion = questionRepository.findRandomQuestion();

        if (randomQuestion.isPresent()) {
            answers = answerService.findAllByQuestion(randomQuestion
                    .orElseThrow(() -> new CustomException("No answers found for this question")));
            randomQuestion.get().setAnswers(answers);
        }

        return randomQuestion;
    }
}
