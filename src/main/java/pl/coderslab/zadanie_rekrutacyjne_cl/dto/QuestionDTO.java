package pl.coderslab.zadanie_rekrutacyjne_cl.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDTO {
    private long id, api_id;
    private String question;
}