package pl.coderslab.quizcraft.utility;

import org.springframework.stereotype.Service;
import pl.coderslab.quizcraft.entity.Answer;
import pl.coderslab.quizcraft.entity.Question;
import pl.coderslab.quizcraft.exception.QuestionNotFoundException;
import pl.coderslab.quizcraft.service.AnswerService;
import pl.coderslab.quizcraft.service.QuestionService;

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
