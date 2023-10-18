package pl.coderslab.quizcraft.service.interfaces;

import pl.coderslab.quizcraft.dto.QuestionDTO;

import java.util.List;

public interface DataTransferServiceInterface {
    List<QuestionDTO> fetchQuestions();

    void transferToDatabase();
}
