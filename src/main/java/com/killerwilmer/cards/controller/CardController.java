package com.killerwilmer.cards.controller;

import com.killerwilmer.cards.model.Card;
import com.killerwilmer.cards.repository.CardRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class CardController {

    final CardRepository cardRepository;

    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @PostMapping("/card/create")
    public Card createCard(@RequestBody Card pCard) {

        Card card = cardRepository.save(new Card(pCard.getName(), pCard.getBody(), pCard.getArea(), pCard.getChannel()));
        return card;
    }
}
