package ru.dpopkov.knowthenics.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.dpopkov.knowthenics.model.KeyTerm;
import ru.dpopkov.knowthenics.services.KeyTermService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class KeyTermControllerTest {

    @Mock
    KeyTermService keyTermService;
    @InjectMocks
    KeyTermController controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    void testShow() throws Exception {
        final Long keyTermId = 10L;
        when(keyTermService.findById(keyTermId)).thenReturn(KeyTerm.builder().id(keyTermId).build());
        mockMvc.perform(get("/keyterms/" + keyTermId))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("keyTerm"))
                .andExpect(view().name("keyterms/keyterm-details"));
        verify(keyTermService).findById(keyTermId);
    }
}
