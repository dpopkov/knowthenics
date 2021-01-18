package ru.dpopkov.knowthenics.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.dpopkov.knowthenics.model.Category;
import ru.dpopkov.knowthenics.services.CategoryService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    CategoryService categoryService;
    @InjectMocks
    CategoryController controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testInitCreateForm() throws Exception {
        mockMvc.perform(get("/categories/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("category"))
                .andExpect(view().name("categories/create-or-update-form"));
        verifyNoInteractions(categoryService);
    }

    @Test
    void testProcessCreateForm() throws Exception {
        final Long categoryId = 20L;
        Category category = Category.builder().id(categoryId).build();
        when(categoryService.save(any(Category.class))).thenReturn(category);
        mockMvc.perform(post("/categories/new")
                .param("name", "name-value")
                .param("description", "description-value")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/categories"));
        verify(categoryService).save(any(Category.class));
    }

    @Test
    void testInitUpdateForm() throws Exception {
        final Long categoryId = 20L;
        when(categoryService.findById(categoryId)).thenReturn(Category.builder().id(categoryId).build());

        mockMvc.perform(get("/categories/" + categoryId + "/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("category"))
                .andExpect(view().name("categories/create-or-update-form"));
        verify(categoryService).findById(categoryId);
    }

    @Test
    void testProcessUpdateForm() throws Exception {
        final Long categoryId = 20L;
        Category category = Category.builder().id(categoryId).build();
        when(categoryService.save(any(Category.class))).thenReturn(category);
        mockMvc.perform(post("/categories/" + categoryId + "/edit")
                .param("name", "name-value")
                .param("description", "description-value")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/categories"));
        verify(categoryService).save(any(Category.class));
    }
}
