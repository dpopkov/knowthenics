package ru.dpopkov.knowthenics.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import ru.dpopkov.knowthenics.model.Question;
import ru.dpopkov.knowthenics.services.QuestionService;

import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class QuestionControllerTest {
    @Mock
    QuestionService questionService;
    @Mock
    Model model;
    @InjectMocks
    QuestionController controller;

    private Set<Question> questions;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        questions = Set.of(
                Question.builder().id(1L).build(),
                Question.builder().id(2L).build()
        );
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @SuppressWarnings("unchecked")
    @Test
    void testList() {
        when(questionService.findAll()).thenReturn(questions);
        ArgumentCaptor<Set<Question>> captor = ArgumentCaptor.forClass(Set.class);

        String viewName = controller.list(model);
        assertEquals("questions/list", viewName);
        verify(questionService).findAll();
        verify(model).addAttribute(eq("questions"), captor.capture());
        Set<Question> set = captor.getValue();
        assertEquals(2, set.size());
    }

    @ParameterizedTest
    @ValueSource(strings = {"/questions", "/questions/", "/questions/list", "/questions/list.html"})
    void testGetQuestions(String urlTemplate) throws Exception {
        when(questionService.findAll()).thenReturn(questions);

        mockMvc.perform(get(urlTemplate))
                .andExpect(status().isOk())
                .andExpect(view().name("questions/list"))
                .andExpect(model().attribute("questions", hasSize(2)));
    }

    @Test
    void testShowQuestion() throws Exception {
        when(questionService.findById(anyLong())).thenReturn(new Question());

        mockMvc.perform(get("/questions/10"))
                .andExpect(status().isOk())
                .andExpect(view().name("questions/question-details"))
                .andExpect(model().attributeExists("question"));
    }

    @Test
    void testFind() throws Exception {
        mockMvc.perform(get("/questions/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("notimplemented"));
        verifyNoInteractions(questionService);
    }
}
