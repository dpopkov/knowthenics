package ru.dpopkov.knowthenics.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.dpopkov.knowthenics.model.Deck;
import ru.dpopkov.knowthenics.services.DeckService;
import ru.dpopkov.knowthenics.services.QCollectionService;

import java.util.List;

@SuppressWarnings("SameReturnValue")
@Slf4j
@Controller
@RequestMapping("/decks")
public class DeckController {

    private final DeckService deckService;
    private final QCollectionService qCollectionService;

    public DeckController(DeckService deckService, QCollectionService qCollectionService) {
        this.deckService = deckService;
        this.qCollectionService = qCollectionService;
    }

    @InitBinder
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping({"", "/", "/list"})
    public String list(Model model) {
        model.addAttribute("decks", deckService.findAll());
        return "decks/list";
    }

    @GetMapping("/{deckId}")
    public String show(@PathVariable String deckId, Model model) {
        Long id = Long.valueOf(deckId);
        Deck deck = deckService.findById(id);
        model.addAttribute("deck", deck);
        return "decks/deck-details";
    }

    @GetMapping("/new")
    public String initNewForm(Model model) {
        model.addAttribute("deck", new Deck());
        return "decks/create-or-update-form";
    }

    @PostMapping("/new")
    public String processNewDeck(Deck deck) {
        Deck saved = deckService.save(deck);
        log.debug("Saved DEck ID {}", saved.getId());
        return "redirect:/decks/list";
    }

    @GetMapping("/{deckId}/populate")
    public String initPopulateForm(@PathVariable String deckId, Model model) {
        Long id = Long.valueOf(deckId);
        Deck deck = deckService.findById(id);
        model.addAttribute("deck", deck);
        model.addAttribute("qcollections", qCollectionService.findAll());
        return "decks/populate-form";
    }

    @PostMapping("/{deckId}/populate")
    public String processPopulateForm(@PathVariable String deckId, @RequestParam List<Long> selectedCollectionsIds) {
        Long id = Long.valueOf(deckId);
        Deck deck = deckService.findById(id);
        selectedCollectionsIds.forEach(collectionId -> deckService.populateFrom(deck, collectionId));
        deckService.save(deck);
        log.debug("Populated Deck ID {}", id);
        return "redirect:/decks/list";
    }
}
