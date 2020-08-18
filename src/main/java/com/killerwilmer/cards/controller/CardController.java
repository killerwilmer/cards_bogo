package com.killerwilmer.cards.controller;

import com.killerwilmer.cards.model.Card;
import com.killerwilmer.cards.repository.CardRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class CardController {

    final CardRepository cardRepository;

    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping("/cards")
    List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @PostMapping("/cards")
    public Card createCard(@RequestBody Card pCard) {

        Card card = cardRepository.save(new Card(pCard.getName(), pCard.getBody(), pCard.getArea(), pCard.getChannel()));
        return card;
    }
}
