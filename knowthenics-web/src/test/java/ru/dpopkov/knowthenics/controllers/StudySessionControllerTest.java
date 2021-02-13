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
import ru.dpopkov.knowthenics.model.FlashCard;
import ru.dpopkov.knowthenics.model.StudySession;
import ru.dpopkov.knowthenics.services.DeckService;
import ru.dpopkov.knowthenics.services.FlashCardService;
import ru.dpopkov.knowthenics.services.StudySessionService;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.dpopkov.knowthenics.controllers.StudySessionController.FLASHCARD_VIEW;

@ExtendWith(MockitoExtension.class)
class StudySessionControllerTest {

    private static final Long DECK_ID = 10L;
    private static final Long STUDY_ID = 20L;

    @Mock
    DeckService deckService;
    @Mock
    FlashCardService flashCardService;
    @Mock
    StudySessionService studySessionService;
    @Mock
    FlashCard mockedCard;
    @InjectMocks
    StudySessionController controller;
    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    void testStartStudySession() throws Exception {
        Deck deck = new Deck();
        deck.addCard(new FlashCard());
        when(deckService.findById(DECK_ID)).thenReturn(deck);

        mockMvc.perform(get("/decks/" + DECK_ID + "/study"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("studySession"))
                .andExpect(model().attribute("showAnswer", is(equalTo(false))))
                .andExpect(view().name(FLASHCARD_VIEW));
        verify(deckService).findById(DECK_ID);
        verify(studySessionService).save(any(StudySession.class));
    }

    @Test
    void testShowCardAnswer() throws Exception {
        when(studySessionService.findById(STUDY_ID)).thenReturn(new StudySession());
        mockMvc.perform(get("/study/" + STUDY_ID + "/show"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("studySession"))
                .andExpect(model().attribute("showAnswer", is(equalTo(true))))
                .andExpect(view().name(FLASHCARD_VIEW));
        verify(studySessionService).findById(STUDY_ID);
    }

    @Test
    void testKnowCard() throws Exception {
        StudySession study = new StudySession();
        study.addCard(mockedCard);
        when(studySessionService.findById(STUDY_ID)).thenReturn(study);
        mockMvc.perform(get("/study/" + STUDY_ID + "/know"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("studySession"))
                .andExpect(model().attribute("showAnswer", is(equalTo(false))))
                .andExpect(view().name(FLASHCARD_VIEW));
        verify(mockedCard).levelUp();
        verify(studySessionService).findById(STUDY_ID);
        verify(flashCardService).save(any(FlashCard.class));
        verify(studySessionService).save(any(StudySession.class));
    }

    @Test
    void testStudyCardAgain() throws Exception {
        StudySession study = new StudySession();
        study.addCard(mockedCard);
        when(studySessionService.findById(STUDY_ID)).thenReturn(study);
        mockMvc.perform(get("/study/" + STUDY_ID + "/again"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("studySession"))
                .andExpect(model().attribute("showAnswer", is(equalTo(false))))
                .andExpect(view().name(FLASHCARD_VIEW));
        verify(mockedCard).levelReset();
        verify(studySessionService).findById(STUDY_ID);
        verify(flashCardService).save(any(FlashCard.class));
        verify(studySessionService).save(any(StudySession.class));
    }
}
