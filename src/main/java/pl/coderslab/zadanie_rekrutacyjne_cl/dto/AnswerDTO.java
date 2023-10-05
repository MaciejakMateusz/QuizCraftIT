package pl.coderslab.zadanie_rekrutacyjne_cl.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDTO {
    private long id, question_id;
    private String answer;
    private boolean correct;
}
