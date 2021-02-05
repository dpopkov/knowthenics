package ru.dpopkov.knowthenics.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.dpopkov.knowthenics.model.Deck;
import ru.dpopkov.knowthenics.services.DeckService;
import ru.dpopkov.knowthenics.services.QCollectionService;

import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class DeckControllerTest {
    private static final Long DECK_ID = 10L;

    @Mock
    DeckService deckService;
    @Mock
    QCollectionService qCollectionService;
    @InjectMocks
    DeckController controller;

    private MockMvc mockMvc;
    private final Deck deck = Deck.builder().id(DECK_ID).build();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testList() throws Exception {
        when(deckService.findAll()).thenReturn(Set.of());
        mockMvc.perform(get("/decks/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("decks"))
                .andExpect(view().name("decks/list"));
    }

    @Test
    void testShow() throws Exception {
        when(deckService.findById(DECK_ID)).thenReturn(deck);
        mockMvc.perform(get("/decks/" + DECK_ID))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("deck"))
                .andExpect(view().name("decks/deck-details"));
    }

    @Test
    void testInitNewForm() throws Exception {
        mockMvc.perform(get("/decks/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("deck"))
                .andExpect(view().name("decks/create-or-update-form"));
    }

    @Test
    void testProcessNewDeck() throws Exception {
        when(deckService.save(any(Deck.class))).thenReturn(new Deck());
        mockMvc.perform(post("/decks/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/decks/list"));
        verify(deckService).save(any(Deck.class));
    }

    @Test
    void testInitPopulateForm() throws Exception {
        when(deckService.findById(DECK_ID)).thenReturn(deck);
        when(qCollectionService.findAll()).thenReturn(Set.of());
        mockMvc.perform(get("/decks/" + DECK_ID + "/populate"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("deck"))
                .andExpect(model().attributeExists("qcollections"))
                .andExpect(view().name("decks/populate-form"));
    }

    @Test
    void testProcessPopulateForm() throws Exception {
        when(deckService.findById(DECK_ID)).thenReturn(deck);
        mockMvc.perform(post("/decks/" + DECK_ID + "/populate")
                .param("selectedCollectionsIds", "11", "12")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/decks/list"));
        verify(deckService).findById(DECK_ID);
        verify(deckService).save(deck);
    }
}
