package ru.dpopkov.knowthenics.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class FindControllerTest {

    FindController controller = new FindController();

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    void testInitFindForm() throws Exception {
        mockMvc.perform(get("/find"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("question"))
                .andExpect(model().attributeExists("answer"))
                .andExpect(view().name("find-form"));
    }
}
