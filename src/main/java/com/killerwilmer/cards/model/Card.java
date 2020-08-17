package com.killerwilmer.cards.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "cards")
public class Card {

    @Id
    private String id;
    @Indexed
    private String name;
    private String body;
    private Area area;
    private Channel channel;
    @CreatedDate
    private DateTime creationAt;
    @LastModifiedDate
    private DateTime modificationAt;

    public Card(String name, String body, Area area, Channel channel) {
        this.name = name;
        this.body = body;
        this.area = area;
        this.channel = channel;
    }
}
