package pl.coderslab.zadanie_rekrutacyjne_cl.controller.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.zadanie_rekrutacyjne_cl.dto.QuestionAnswerRequestBody;
import pl.coderslab.zadanie_rekrutacyjne_cl.dto.Response;
import pl.coderslab.zadanie_rekrutacyjne_cl.entity.Question;
import pl.coderslab.zadanie_rekrutacyjne_cl.exception.QuestionNotFoundException;
import pl.coderslab.zadanie_rekrutacyjne_cl.service.QuestionService;
import pl.coderslab.zadanie_rekrutacyjne_cl.utility.AnswerValidator;

@RestController
@RequestMapping("/api")
public class MainApiController {

    private final QuestionService questionService;
    private final AnswerValidator answerValidator;

    public MainApiController(QuestionService questionService, AnswerValidator answerValidator) {
        this.questionService = questionService;
        this.answerValidator = answerValidator;
    }

    @GetMapping("/questions")
    public Question randomQuestion() {
        return questionService.findRandom()
                .orElseThrow(() -> new QuestionNotFoundException("No question found, try again."));
    }

    @PostMapping("/questions")
    public Response correct(@RequestBody QuestionAnswerRequestBody requestBody) {
        return new Response(answerValidator.isCorrect(requestBody.getQuestionId(), requestBody.getAnswers()));
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> options() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Allow", "GET, POST, OPTIONS");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
