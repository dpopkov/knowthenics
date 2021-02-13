package ru.dpopkov.knowthenics.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.dpopkov.knowthenics.model.Deck;
import ru.dpopkov.knowthenics.model.FlashCard;
import ru.dpopkov.knowthenics.model.StudySession;
import ru.dpopkov.knowthenics.services.DeckService;
import ru.dpopkov.knowthenics.services.FlashCardService;
import ru.dpopkov.knowthenics.services.StudySessionService;

import java.time.LocalDateTime;

@SuppressWarnings("SameReturnValue")
@Controller
public class StudySessionController {

    public static final String FLASHCARD_VIEW = "decks/flashcard";

    private final DeckService deckService;
    private final FlashCardService flashCardService;
    private final StudySessionService studySessionService;

    public StudySessionController(DeckService deckService, FlashCardService flashCardService, StudySessionService studySessionService) {
        this.deckService = deckService;
        this.flashCardService = flashCardService;
        this.studySessionService = studySessionService;
    }

    @GetMapping("/decks/{deckId}/study")
    public String startStudySession(@PathVariable String deckId, Model model) {
        Long deckIdLong = Long.valueOf(deckId);
        Deck deck = deckService.findById(deckIdLong);
        StudySession studySession = new StudySession();
        studySession.setStarted(LocalDateTime.now());
        studySession.addAllCards(deck.getFlashCards());
        studySessionService.save(studySession);
        model.addAttribute("studySession", studySession);
        model.addAttribute("showAnswer", false);
        return FLASHCARD_VIEW;
    }

    @GetMapping("/study/{studySessionId}/show")
    public String showCardAnswer(@PathVariable String studySessionId, Model model) {
        Long studyId = Long.valueOf(studySessionId);
        StudySession studySession = studySessionService.findById(studyId);
        model.addAttribute("studySession", studySession);
        model.addAttribute("showAnswer", true);
        return FLASHCARD_VIEW;
    }

    @GetMapping("/study/{studySessionId}/know")
    public String knowCard(@PathVariable String studySessionId, Model model) {
        Long studyId = Long.valueOf(studySessionId);
        StudySession studySession = studySessionService.findById(studyId);

        FlashCard currentCard = studySession.getCurrentCard();
        currentCard.levelUp();
        flashCardService.save(currentCard);

        checkAndSave(studySession);
        model.addAttribute("studySession", studySession);
        model.addAttribute("showAnswer", false);
        return FLASHCARD_VIEW;
    }

    @GetMapping("/study/{studySessionId}/again")
    public String studyCardAgain(@PathVariable String studySessionId, Model model) {
        Long studyId = Long.valueOf(studySessionId);
        StudySession studySession = studySessionService.findById(studyId);

        FlashCard currentCard = studySession.getCurrentCard();
        currentCard.levelReset();
        flashCardService.save(currentCard);

        checkAndSave(studySession);
        model.addAttribute("studySession", studySession);
        model.addAttribute("showAnswer", false);
        return FLASHCARD_VIEW;
    }

    private void checkAndSave(StudySession studySession) {
        if (studySession.isAtEnd()) {
            studySession.setFinished(LocalDateTime.now());
        } else {
            studySession.moveToNext();
        }
        studySessionService.save(studySession);
    }
}
