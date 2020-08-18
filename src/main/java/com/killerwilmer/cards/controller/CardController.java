package com.killerwilmer.cards.controller;

import com.killerwilmer.cards.exception.CardNotFoundException;
import com.killerwilmer.cards.model.Card;
import com.killerwilmer.cards.repository.CardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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

    @GetMapping("/cards/{id}")
    Card getOneCard(@PathVariable String id) {

        return cardRepository.findById(id)
                .orElseThrow(() -> new CardNotFoundException(id));
    }

    @DeleteMapping("/cards/{id}")
    void deleteCard(@PathVariable String id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new CardNotFoundException(id));

        cardRepository.deleteById(card.getId());
    }

    @PutMapping("/cards/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable("id") String id, @RequestBody Card p_Card) {

        Optional<Card> cardData = cardRepository.findById(id);

        if (cardData.isPresent()) {
            Card _card = cardData.get();
            _card.setName(p_Card.getName());
            _card.setBody(p_Card.getBody());
            _card.setArea(p_Card.getArea());
            _card.setChannel(p_Card.getChannel());
            return new ResponseEntity<>(cardRepository.save(_card), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
