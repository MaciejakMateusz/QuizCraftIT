package pl.coderslab.zadanie_rekrutacyjne_cl.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.zadanie_rekrutacyjne_cl.entity.Question;
import pl.coderslab.zadanie_rekrutacyjne_cl.exception.CustomException;
import pl.coderslab.zadanie_rekrutacyjne_cl.service.AnswerService;
import pl.coderslab.zadanie_rekrutacyjne_cl.service.QuestionService;

@RestController
@RequestMapping("/api")
public class MainApiController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    public MainApiController(QuestionService questionService, AnswerService answerService) {
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @GetMapping("/questions")
    public Question randomQuestion() {
        return questionService.findRandom()
                .orElseThrow(() -> new CustomException("Couldn't find a question, try again"));
    }
}
