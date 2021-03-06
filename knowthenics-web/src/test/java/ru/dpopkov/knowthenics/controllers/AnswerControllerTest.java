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
import ru.dpopkov.knowthenics.services.AnswerService;
import ru.dpopkov.knowthenics.services.QuestionService;
import ru.dpopkov.knowthenics.services.SourceService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AnswerControllerTest {

    @Mock
    AnswerService answerService;
    @Mock
    QuestionService questionService;
    @Mock
    SourceService sourceService;
    @InjectMocks
    AnswerController controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    void testInitCreateForm() throws Exception {
        final Long questionId = 10L;
        mockMvc.perform(get("/questions/" + questionId + "/answers/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("answer"))
                .andExpect(model().attributeExists("answerTypes"))
                .andExpect(model().attributeExists("allSources"))
                .andExpect(view().name("answers/create-or-update-form"));
        verify(sourceService).findAll();
        verify(questionService).findById(questionId);
    }

    @Test
    void testInitCreateFormQuestionNotFound() throws Exception {
        final Long questionId = 10L;
        when(questionService.findById(questionId)).thenThrow(NotFoundInRepositoryException.class);

        mockMvc.perform(get("/questions/" + questionId + "/answers/new"))
                .andExpect(status().isNotFound());
        verify(questionService).findById(questionId);
    }

    @Test
    void testProcessCreateForm() throws Exception {
        final Long questionId = 10L;
        final Long answerId = 20L;
        when(answerService.save(any(Answer.class))).thenReturn(Answer.builder().id(answerId).build());
        mockMvc.perform(post("/questions/" + questionId + "/answers/new")
                .param("wordingEn", "wordingEn-value")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(
                        "redirect:/questions/" + questionId + "/answers/" + answerId + "/view"));
        verify(questionService).findById(questionId);
        verify(answerService).save(any(Answer.class));
    }

    @Test
    void testProcessCreateFormValidationFailed() throws Exception {
        mockMvc.perform(post("/questions/10/answers/new")
                .param("wordingEn", "")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("answers/create-or-update-form"));
        verifyNoInteractions(answerService);
    }

    @Test
    void testInitUpdateForm() throws Exception {
        final long questionId = 10L;
        final Long answerId = 20L;
        when(answerService.findById(answerId)).thenReturn(Answer.builder().id(answerId).build());

        mockMvc.perform(get("/questions/" + questionId + "/answers/" + answerId + "/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("answer"))
                .andExpect(model().attributeExists("answerTypes"))
                .andExpect(model().attributeExists("allSources"))
                .andExpect(view().name("answers/create-or-update-form"));
        verify(answerService).findById(answerId);
    }

    @Test
    void testProcessUpdateForm() throws Exception {
        final long questionId = 10L;
        final Long answerId = 20L;
        Answer foundAnswer = Answer.builder().id(answerId).build();
        when(answerService.findById(answerId)).thenReturn(foundAnswer);
        when(answerService.save(foundAnswer)).thenReturn(Answer.builder().id(answerId).build());
        mockMvc.perform(post("/questions/" + questionId + "/answers/" + answerId + "/edit")
                .param("wordingEn", "wordingEn-value")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(
                        "redirect:/questions/" + questionId + "/answers/" + answerId + "/view"));
        verify(answerService).save(foundAnswer);
    }

    @Test
    void testProcessUpdateFormValidationFailed() throws Exception {
        mockMvc.perform(post("/questions/10/answers/20/edit")
                .param("wordingEn", "")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("answers/create-or-update-form"));
        verifyNoInteractions(answerService);
    }
}
