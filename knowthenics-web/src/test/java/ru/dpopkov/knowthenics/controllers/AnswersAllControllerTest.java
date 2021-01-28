package ru.dpopkov.knowthenics.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.dpopkov.knowthenics.exceptions.data.NotFoundInRepositoryException;
import ru.dpopkov.knowthenics.model.Answer;
import ru.dpopkov.knowthenics.model.KeyTerm;
import ru.dpopkov.knowthenics.services.AnswerService;
import ru.dpopkov.knowthenics.services.KeyTermService;

import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AnswersAllControllerTest {
    @Mock
    AnswerService answerService;
    @Mock
    KeyTermService keyTermService;
    @InjectMocks
    AnswersAllController controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    void testList() throws Exception {
        when(answerService.findAll()).thenReturn(Set.of());
        mockMvc.perform(get("/answers"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("answers"))
                .andExpect(view().name("answers/list"));
        verify(answerService).findAll();
    }

    @Test
    void testShow() throws Exception {
        final Long answerId = 20L;
        when(answerService.findById(answerId)).thenReturn(Answer.builder().id(answerId).build());

        mockMvc.perform(get("/questions/10/answers/" + answerId + "/view"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("answer"))
                .andExpect(view().name("answers/answer-details"));
        verify(answerService).findById(answerId);
    }

    @Test
    void testShowNotFound() throws Exception {
        final Long answerId = 20L;
        when(answerService.findById(answerId)).thenThrow(NotFoundInRepositoryException.class);

        mockMvc.perform(get("/questions/10/answers/" + answerId + "/view"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("errors/404error"));
        verify(answerService).findById(answerId);
    }

    @Test
    void testShowBadId() throws Exception {
        final String badId = "not_a_number";
        mockMvc.perform(get("/questions/10/answers/" + badId + "/view"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("errors/400error"));
        verifyNoInteractions(answerService);
    }

    @Test
    void testInitFindAnswerForm() throws Exception {
        mockMvc.perform(get("/answers/find"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("answer"))
                .andExpect(view().name("answers/find-answers"));
    }

    @Test
    void testProcessFindAnswerForm() throws Exception {
        String search = "test";
        String searchPattern = "%" + search + "%";
        Set<Answer> answers = Set.of(
                Answer.builder().id(1L).build(),
                Answer.builder().id(2L).build()
        );
        when(answerService.findAllByWordingEnLike(searchPattern)).thenReturn(answers);
        mockMvc.perform(post("/answers/find")
                .param("wordingEn", search)
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("answers"))
                .andExpect(model().attribute("answers", hasSize(2)))
                .andExpect(view().name("answers/list"));
        verify(answerService).findAllByWordingEnLike(searchPattern);
    }

    @Test
    void testFindByKeyTerm() throws Exception {
        final Long keyTermId = 20L;
        KeyTerm keyTerm = KeyTerm.builder().id(keyTermId).build();
        when(keyTermService.findById(keyTermId)).thenReturn(keyTerm);
        mockMvc.perform(get("/answers/findByKeyTerm/" + keyTermId))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("answers"))
                .andExpect(view().name("answers/list"));
        verify(keyTermService).findById(keyTermId);
        verify(answerService).findByKeyTerm(keyTerm);
    }
}
