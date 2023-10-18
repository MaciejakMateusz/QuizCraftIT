package pl.coderslab.quizcraft.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.quizcraft.dto.AnswerRequestBody;
import pl.coderslab.quizcraft.dto.Response;
import pl.coderslab.quizcraft.entity.Question;
import pl.coderslab.quizcraft.exception.QuestionNotFoundException;
import pl.coderslab.quizcraft.service.QuestionService;
import pl.coderslab.quizcraft.utility.AnswerValidator;

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

    @PostMapping("/answers")
    public Response correct(@RequestBody AnswerRequestBody requestBody) {
        return new Response(answerValidator.isCorrect(requestBody.getQuestionId(), requestBody.getAnswers()));
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> options() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Allow", "GET, POST, OPTIONS");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}