package pl.coderslab.zadanie_rekrutacyjne_cl.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.zadanie_rekrutacyjne_cl.entity.Question;

@RestController
@RequestMapping("/api")
public class MainApiController {

    @GetMapping("/questions")
    public Question randomQuestion() {
        return new Question();
    }
}
