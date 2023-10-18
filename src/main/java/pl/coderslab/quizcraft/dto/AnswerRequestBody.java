package pl.coderslab.quizcraft.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnswerRequestBody {
    @JsonProperty("answers")
    private List<Long> answers;

    @JsonProperty("questionId")
    private Long questionId;
}