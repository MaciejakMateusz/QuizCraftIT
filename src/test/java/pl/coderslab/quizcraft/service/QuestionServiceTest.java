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
import pl.coderslab.quizcraft.repository.QuestionRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class QuestionServiceTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void shouldSaveQuestionAndAnswers() {
        Question question = new Question();
        question.setQuestion("Will this test question be inserted into the database?");

        Answer answer1 = new Answer();
        answer1.setQuestion(question);
        answer1.setAnswer("Yes");
        answer1.setCorrect(true);

        Answer answer2 = new Answer();
        answer1.setQuestion(question);
        answer1.setAnswer("No");
        answer1.setCorrect(false);

        question.setAnswers(List.of(answer1, answer2));
        questionRepository.saveAndFlush(question);

        Question savedQuestion = questionRepository.findById(205L).orElse(new Question());
        assertEquals("Will this test question be inserted into the database?", savedQuestion.getQuestion());
    }

    @Test
    void shouldFindById() {
        Question question = questionRepository.findById(3L).orElse(new Question());
        assertEquals("How to list all nodes in your Docker swarm?", question.getQuestion());
    }

    @Test
    void findRandom() {
        Question question1 = questionRepository.findRandomQuestion().orElse(new Question());
        Question question2 = questionRepository.findRandomQuestion().orElse(new Question());

        assertNotEquals(question1.getQuestion(), question2.getQuestion());
    }
}