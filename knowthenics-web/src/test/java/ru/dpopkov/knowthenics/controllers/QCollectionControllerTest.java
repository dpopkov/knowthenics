package ru.dpopkov.knowthenics.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.dpopkov.knowthenics.model.QCollection;
import ru.dpopkov.knowthenics.services.QCollectionService;
import ru.dpopkov.knowthenics.services.QuestionService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class QCollectionControllerTest {

    @Mock
    QCollectionService qCollectionService;
    @Mock
    QuestionService questionService;
    @InjectMocks
    QCollectionController controller;

    private final Long collectionId = 10L;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    void testListAll() throws Exception {
        mockMvc.perform(get("/qcollections/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("allCollections"))
                .andExpect(view().name("qcollections/list"));
    }

    @Test
    void testShow() throws Exception {
        when(qCollectionService.findById(collectionId)).thenReturn(QCollection.builder().build());

        mockMvc.perform(get("/qcollections/" + collectionId))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("qCollection"))
                .andExpect(view().name("qcollections/qcollection-details"));
        verify(qCollectionService).findById(collectionId);
    }

    @Test
    void testInitCreateForm() throws Exception {
        mockMvc.perform(get("/qcollections/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("qCollection"))
                .andExpect(model().attributeExists("allQuestions"))
                .andExpect(view().name("qcollections/create-or-update-form"));
    }

    @Test
    void testProcessNewForm() throws Exception {
        when(qCollectionService.save(any(QCollection.class)))
                .thenReturn(QCollection.builder().id(collectionId).build());

        mockMvc.perform(post("/qcollections/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/qcollections/" + collectionId));
        verify(qCollectionService).save(any(QCollection.class));
    }

    @Test
    void testInitUpdateForm() throws Exception {
        when(qCollectionService.findById(collectionId)).thenReturn(QCollection.builder().build());

        mockMvc.perform(get("/qcollections/" + collectionId + "/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("qCollection"))
                .andExpect(model().attributeExists("allQuestions"))
                .andExpect(view().name("qcollections/create-or-update-form"));
        verify(qCollectionService).findById(collectionId);
        verify(questionService).findAll();
    }

    @Test
    void testProcessNewForm1234() throws Exception {
        when(qCollectionService.findById(collectionId)).thenReturn(QCollection.builder().build());
        when(qCollectionService.save(any(QCollection.class)))
                .thenReturn(QCollection.builder().id(collectionId).build());

        mockMvc.perform(post("/qcollections/" + collectionId + "/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/qcollections/" + collectionId));
        verify(qCollectionService).findById(collectionId);
    }
}
