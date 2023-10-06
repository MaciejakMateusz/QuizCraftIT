package pl.coderslab.zadanie_rekrutacyjne_cl.service.interfaces;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.zadanie_rekrutacyjne_cl.dto.QuestionDTO;
import pl.coderslab.zadanie_rekrutacyjne_cl.exception.ApiCommunicationException;
import pl.coderslab.zadanie_rekrutacyjne_cl.service.QuestionService;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class DataTransferService implements DataTransferServiceInterface {

    @Value("${api.url.questions}")
    private String apiUrl;

    @Value("${api.key}")
    private String apiKey;

    private final QuestionService questionService;

    public DataTransferService(QuestionService questionService) {
        this.questionService = questionService;
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

    }
}
