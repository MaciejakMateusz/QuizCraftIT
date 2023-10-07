package pl.coderslab.zadanie_rekrutacyjne_cl.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class QuestionDTO {
    private int id;
    private String question;
    private String description;
    private Map<String, String> answers;
    private boolean multipleCorrectAnswers;
    private Map<String, Boolean> correct_answers;
    private String explanation;
    private String tip;
    private String category;
    private String difficulty;
}

