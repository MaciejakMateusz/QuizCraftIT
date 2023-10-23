package pl.coderslab.quizcraft.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import pl.coderslab.quizcraft.entity.Answer;
import pl.coderslab.quizcraft.entity.Question;
import pl.coderslab.quizcraft.repository.AnswerRepository;
import pl.coderslab.quizcraft.service.interfaces.QuestionServiceInterface;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnswerServiceTest {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionServiceInterface questionService;

    @Test
    void shouldFindAllByQuestion() {
        Question question = questionService.findById(1).orElse(new Question());
        List<Answer> answers = answerRepository.findAllByQuestion(question);

        assertEquals(4, answers.size());
        assertEquals("php artisan maintenance", answers.get(0).getAnswer());
        assertEquals("php artisan down", answers.get(1).getAnswer());
        assertEquals("php artisan stop", answers.get(2).getAnswer());
        assertEquals("php artisan pause", answers.get(3).getAnswer());
    }
}