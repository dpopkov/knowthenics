package ru.dpopkov.knowthenics.services.springdatajpa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.dpopkov.knowthenics.exceptions.data.NotFoundInRepositoryException;
import ru.dpopkov.knowthenics.model.Category;
import ru.dpopkov.knowthenics.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategorySDJpaServiceTest {
    private static final Long ID = 12L;

    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategorySDJpaService categoryService;

    private final Category core = Category.builder().id(ID).name("Core").build();

    @Test
    void testFindAll() {
        var data = List.of(
                Category.builder().name("Core").build(),
                Category.builder().name("SQL").build()
        );
        when(categoryRepository.findAll()).thenReturn(data);

        var all = categoryService.findAll();
        assertEquals(2, all.size());
        verify(categoryRepository).findAll();
    }

    @Test
    void testFindById() {
        when(categoryRepository.findById(ID)).thenReturn(Optional.of(core));

        Category found = categoryService.findById(ID);
        assertNotNull(found);
        assertEquals(ID, found.getId());
        verify(categoryRepository).findById(ID);
    }

    @Test
    void testFindByIdNotExisting() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        final Long theId = 123L;
        assertThrows(NotFoundInRepositoryException.class, () -> categoryService.findById(theId));
        verify(categoryRepository).findById(theId);
    }

    @Test
    void testSave() {
        when(categoryRepository.save(core)).thenReturn(core);

        Category saved = categoryService.save(core);
        assertNotNull(saved);
        verify(categoryRepository).save(core);
    }

    @Test
    void testDelete() {
        categoryService.delete(core);
        verify(categoryRepository).delete(core);
    }

    @Test
    void testDeleteById() {
        categoryService.deleteById(ID);
        verify(categoryRepository).deleteById(ID);
    }
}
