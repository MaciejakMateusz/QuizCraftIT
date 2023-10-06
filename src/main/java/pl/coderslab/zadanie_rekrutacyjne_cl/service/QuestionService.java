package pl.coderslab.zadanie_rekrutacyjne_cl.service;

import org.springframework.stereotype.Service;
import pl.coderslab.zadanie_rekrutacyjne_cl.entity.Question;
import pl.coderslab.zadanie_rekrutacyjne_cl.repository.QuestionRepository;
import pl.coderslab.zadanie_rekrutacyjne_cl.service.interfaces.QuestionServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService implements QuestionServiceInterface {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void save(Question question) {
        questionRepository.save(question);
    }

    @Override
    public Optional<Question> findById(Long id) {
        return questionRepository.findById(id);
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }
}
