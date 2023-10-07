package pl.coderslab.zadanie_rekrutacyjne_cl.controller.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.zadanie_rekrutacyjne_cl.entity.Question;
import pl.coderslab.zadanie_rekrutacyjne_cl.exception.QuestionNotFoundException;
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
                .orElseThrow(() -> new QuestionNotFoundException("No question found, try again."));
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> options() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Allow", "GET, POST, OPTIONS");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
