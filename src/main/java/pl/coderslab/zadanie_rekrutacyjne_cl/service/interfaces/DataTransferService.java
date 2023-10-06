package pl.coderslab.zadanie_rekrutacyjne_cl.service.interfaces;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.zadanie_rekrutacyjne_cl.dto.QuestionDTO;
import pl.coderslab.zadanie_rekrutacyjne_cl.entity.Answer;
import pl.coderslab.zadanie_rekrutacyjne_cl.entity.Question;
import pl.coderslab.zadanie_rekrutacyjne_cl.exception.ApiCommunicationException;
import pl.coderslab.zadanie_rekrutacyjne_cl.repository.AnswerRepository;
import pl.coderslab.zadanie_rekrutacyjne_cl.service.QuestionService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class DataTransferService implements DataTransferServiceInterface {

    @Value("${api.url.questions}")
    private String apiUrl;

    @Value("${api.key}")
    private String apiKey;

    private final QuestionService questionService;
    private final AnswerRepository answerRepository;

    public DataTransferService(QuestionService questionService, AnswerRepository answerRepository) {
        this.questionService = questionService;
        this.answerRepository = answerRepository;
    }

    @Override
    public List<QuestionDTO> fetchQuestions() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", apiKey);

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<QuestionDTO[]> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, QuestionDTO[].class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        } else {
            throw new ApiCommunicationException("Błąd komunikacji z " + apiUrl);
        }
    }

    @Override
    public void transferToDatabase() {
        for (QuestionDTO questionDTO : fetchQuestions()) {

            Question question = new Question();
            question.setApi_id(questionDTO.getId());
            question.setQuestion(questionDTO.getQuestion());

            questionService.save(question);

            Map<String, String> answers = questionDTO.getAnswers();
            Map<String, Boolean> correctAnswers = questionDTO.getCorrectAnswers();

            for (Map.Entry<String, String> answerEntry : answers.entrySet()) {
                String answerKey = answerEntry.getKey();
                String answerValue = answerEntry.getValue();

                if (answerValue == null) {
                    continue;
                }

                Answer answer = new Answer();
                answer.setQuestion(question);
                answer.setAnswer(answerValue);

                // Sprawdzam poprawność odpowiedzi na podstawie mapy correctAnswers
                String correctKey = answerKey + "_correct";
                Boolean isCorrect = correctAnswers.get(correctKey);

                if (isCorrect != null && isCorrect) {
                    answer.setCorrect(true);
                }

                answerRepository.save(answer);
            }
        }
    }
}
