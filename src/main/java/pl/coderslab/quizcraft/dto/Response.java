package pl.coderslab.quizcraft.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response {
    private boolean correct;

    public Response(boolean correct) {
        this.correct = correct;
    }
}
