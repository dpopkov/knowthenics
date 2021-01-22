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

import java.util.Set;

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
        verifyNoInteractions(sourceService);
    }

    @Test
    void testProcessCreateForm() throws Exception {
        final Long questionId = 10L;
        final Long answerId = 20L;
        when(answerService.save(any(Answer.class))).thenReturn(Answer.builder().id(answerId).build());
        mockMvc.perform(post("/questions/" + questionId + "/answers/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(
                        "redirect:/questions/" + questionId + "/answers/" + answerId + "/view"));
        verify(questionService).findById(questionId);
        verify(answerService).save(any(Answer.class));
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
        mockMvc.perform(post("/questions/" + questionId + "/answers/" + answerId + "/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(
                        "redirect:/questions/" + questionId + "/answers/" + answerId + "/view"));
        verify(answerService).save(foundAnswer);
    }
}
