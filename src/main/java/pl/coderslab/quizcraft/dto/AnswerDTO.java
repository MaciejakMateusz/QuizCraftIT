package pl.coderslab.quizcraft.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDTO {
    private long id, question_id;
    private String answer;
    private boolean correct;
}
