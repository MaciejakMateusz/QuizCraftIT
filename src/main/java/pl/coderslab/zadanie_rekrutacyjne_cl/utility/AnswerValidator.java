package pl.coderslab.zadanie_rekrutacyjne_cl.utility;

import org.springframework.stereotype.Service;
import pl.coderslab.zadanie_rekrutacyjne_cl.entity.Answer;
import pl.coderslab.zadanie_rekrutacyjne_cl.entity.Question;
import pl.coderslab.zadanie_rekrutacyjne_cl.exception.QuestionNotFoundException;
import pl.coderslab.zadanie_rekrutacyjne_cl.service.AnswerService;
import pl.coderslab.zadanie_rekrutacyjne_cl.service.QuestionService;

import java.util.List;

@Service
public class AnswerValidator {

    private final QuestionService questionService;
    private final AnswerService answerService;

    public AnswerValidator(QuestionService questionService, AnswerService answerService) {
        this.questionService = questionService;
        this.answerService = answerService;
    }

    public boolean isCorrect(long questionId, List<Long> answers) {
        Question question = questionService.findById(questionId)
                .orElseThrow(() -> new QuestionNotFoundException("No question found, try again."));
        List<Answer> answersForQuestion = answerService.findAllByQuestionAndCorrect(question, true);

        for (Answer answerForQuestion : answersForQuestion) {
            long answerId = answerForQuestion.getId();
            boolean found = false;
            for (long id : answers) {
                if (answerId == id) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }

        return true;
    }

}
