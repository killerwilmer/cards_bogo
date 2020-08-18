package com.killerwilmer.cards.exception;

public class CardNotFoundException extends RuntimeException {

    public CardNotFoundException(String id) {
        super("Could not find card " + id);
    }
}
