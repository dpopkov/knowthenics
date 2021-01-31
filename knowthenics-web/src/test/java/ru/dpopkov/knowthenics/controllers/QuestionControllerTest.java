package ru.dpopkov.knowthenics.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import ru.dpopkov.knowthenics.exceptions.data.NotFoundInRepositoryException;
import ru.dpopkov.knowthenics.model.KeyTerm;
import ru.dpopkov.knowthenics.model.Question;
import ru.dpopkov.knowthenics.services.CategoryService;
import ru.dpopkov.knowthenics.services.KeyTermService;
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
    CategoryService categoryService;
    @Mock
    KeyTermService keyTermService;
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
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
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
    @ValueSource(strings = {"/questions/list", "/questions/list.html"})
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
    void testShowQuestionNotFound() throws Exception {
        when(questionService.findById(anyLong())).thenThrow(NotFoundInRepositoryException.class);

        mockMvc.perform(get("/questions/10"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("errors/404error"));
    }

    @Test
    void testInitFindQuestionForm() throws Exception {
        mockMvc.perform(get("/questions/find"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("question"))
                .andExpect(view().name("questions/find-questions"));
        verifyNoInteractions(questionService);
    }

    @Test
    void testProcessFindQuestionForm() throws Exception {
        when(questionService.findAllByWordingEnLike(anyString())).thenReturn(questions);

        mockMvc.perform(post("/questions"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("questions"))
                .andExpect(view().name("questions/list"));
        verify(questionService).findAllByWordingEnLike(anyString());
    }

    @Test
    void testFindByKeyTerm() throws Exception {
        final Long keyTermId = 20L;
        KeyTerm keyTerm = KeyTerm.builder().id(keyTermId).build();
        when(keyTermService.findById(keyTermId)).thenReturn(keyTerm);
        mockMvc.perform(get("/questions/findByKeyTerm/" + keyTermId))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("questions"))
                .andExpect(view().name("questions/list"));
        verify(keyTermService).findById(keyTermId);
        verify(questionService).findByKeyTerm(keyTerm);
    }

    @Test
    void testInitCreationForm() throws Exception {
        mockMvc.perform(get("/questions/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("questions/create-or-update-form"))
                .andExpect(model().attributeExists("question"))
                .andExpect(model().attributeExists("categories"));
        verify(categoryService).findAll();
        verifyNoInteractions(questionService);
    }

    @Test
    void testProcessCreateForm() throws Exception {
        final Long questionId = 10L;
        when(questionService.save(ArgumentMatchers.any(Question.class))).thenReturn(
                Question.builder().id(questionId).build()
        );
        mockMvc.perform(post("/questions/new")
                .param("wordingEn", "wordingEn-value")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/questions/" + questionId));
        verify(questionService).save(ArgumentMatchers.any(Question.class));
    }

    @Test
    void testProcessCreateFormValidationFails() throws Exception {
        mockMvc.perform(post("/questions/new")
                .param("wordingEn", "")
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("question"))
                .andExpect(view().name("questions/create-or-update-form"));
        verifyNoInteractions(questionService);
    }

    @Test
    void testInitUpdateForm() throws Exception {
        final Long questionId = 10L;
        when(questionService.findById(questionId)).thenReturn(Question.builder().id(questionId).build());

        mockMvc.perform(get("/questions/" + questionId + "/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("questions/create-or-update-form"))
                .andExpect(model().attributeExists("question"))
                .andExpect(model().attributeExists("categories"));
        verify(categoryService).findAll();
        verify(questionService).findById(questionId);
    }

    @Test
    void testProcessUpdateForm() throws Exception {
        final Long questionId = 10L;
        Question found = Question.builder().id(questionId).build();
        Question saved = Question.builder().id(questionId).build();
        when(questionService.findById(questionId)).thenReturn(found);
        when(questionService.save(found)).thenReturn(saved);

        mockMvc.perform(post("/questions/" + questionId + "/edit")
                .param("wordingEn", "wordingEn-value")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/questions/" + questionId));
        verify(questionService).findById(questionId);
        verify(questionService).save(found);
    }

    @Test
    void testProcessUpdateFormValidationFails() throws Exception {
        mockMvc.perform(post("/questions/10/edit")
                .param("wordingEn", "")
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("question"))
                .andExpect(view().name("questions/create-or-update-form"));
        verifyNoInteractions(questionService);
    }
}
