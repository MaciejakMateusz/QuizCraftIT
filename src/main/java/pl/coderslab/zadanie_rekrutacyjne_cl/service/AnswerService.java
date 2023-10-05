package pl.coderslab.zadanie_rekrutacyjne_cl.service;

import pl.coderslab.zadanie_rekrutacyjne_cl.entity.Answer;
import pl.coderslab.zadanie_rekrutacyjne_cl.repository.AnswerRepository;
import pl.coderslab.zadanie_rekrutacyjne_cl.service.interfaces.AnswerServiceInterface;

import java.util.List;
import java.util.Optional;

public class AnswerService implements AnswerServiceInterface {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public void save(Answer answer) {
        answerRepository.save(answer);
    }

    @Override
    public Optional<Answer> findById(Long id) {
        return answerRepository.findById(id);
    }

    @Override
    public List<Answer> findAll() {
        return answerRepository.findAll();
    }
}
