package com.killerwilmer.cards.repository;

import com.killerwilmer.cards.model.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CardRepository extends MongoRepository<Card, String> {
}
