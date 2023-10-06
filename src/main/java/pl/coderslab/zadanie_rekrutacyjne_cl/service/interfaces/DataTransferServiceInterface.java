package pl.coderslab.zadanie_rekrutacyjne_cl.service.interfaces;

import pl.coderslab.zadanie_rekrutacyjne_cl.dto.QuestionDTO;

import java.util.List;

public interface DataTransferServiceInterface {
    List<QuestionDTO> fetchQuestions();

    void transferToDatabase();
}
