package pl.coderslab.quizcraft.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.coderslab.quizcraft.dto.AnswerRequestBody;
import pl.coderslab.quizcraft.entity.Question;
import pl.coderslab.quizcraft.service.interfaces.QuestionServiceInterface;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MainApiControllerTest {

    @Autowired
    private QuestionServiceInterface questionService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetRandomQuestionFromDB() {
        Question question = questionService.findRandom().orElse(new Question());

        assertTrue(question.getId() != 0);
        assertTrue(question.getApiID() != 0);
        assertNotNull(question.getQuestion());
        assertNotNull(question.getAnswers());
    }

    @Test
    void shouldGetRandomQuestionFromEndpoint() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/questions")).andReturn();
        String actualQuestionJson1 = result.getResponse().getContentAsString();
        assertTrue(actualQuestionJson1.contains("\"id\":"));
        assertTrue(actualQuestionJson1.contains("\"question\":"));
        assertTrue(actualQuestionJson1.contains("\"answers\":[{"));

        //fetching random question second time to ensure it is different from the first one
        result = mockMvc.perform(get("/api/questions")).andReturn();
        String actualQuestionJson2 = result.getResponse().getContentAsString();
        assertNotEquals(actualQuestionJson1, actualQuestionJson2);
    }

    @Test
    void shouldCheckIfCorrectSingleAnswerCase() throws Exception {
        AnswerRequestBody answerRequestBody = new AnswerRequestBody();
        answerRequestBody.setQuestionId(2L);
        answerRequestBody.setAnswers(List.of(7L));

        ObjectMapper objectMapper = new ObjectMapper();
        String objectAsString = objectMapper.writeValueAsString(answerRequestBody);

        MvcResult result = mockMvc.perform(post("/api/answers")
                .content(objectAsString)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        String actualAnswerJson = result.getResponse().getContentAsString();

        assertTrue(actualAnswerJson.contains("\"correct\":true"));
    }

    @Test
    void shouldCheckIfCorrectMultipleAnswersCase() throws Exception {
        AnswerRequestBody answerRequestBody = new AnswerRequestBody();
        answerRequestBody.setQuestionId(4L);
        answerRequestBody.setAnswers(List.of(13L, 16L));

        ObjectMapper objectMapper = new ObjectMapper();
        String objectAsString = objectMapper.writeValueAsString(answerRequestBody);

        MvcResult result = mockMvc.perform(post("/api/answers")
                .content(objectAsString)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        String actualAnswerJson = result.getResponse().getContentAsString();

        assertTrue(actualAnswerJson.contains("\"correct\":true"));
    }

    @Test
    void shouldCheckIfIncorrectSingleAnswerCase() throws Exception {
        AnswerRequestBody answerRequestBody = new AnswerRequestBody();
        answerRequestBody.setQuestionId(1L);
        answerRequestBody.setAnswers(List.of(3L));

        ObjectMapper objectMapper = new ObjectMapper();
        String objectAsString = objectMapper.writeValueAsString(answerRequestBody);

        MvcResult result = mockMvc.perform(post("/api/answers")
                .content(objectAsString)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        String actualAnswerJson = result.getResponse().getContentAsString();

        assertTrue(actualAnswerJson.contains("\"correct\":false"));
    }
}