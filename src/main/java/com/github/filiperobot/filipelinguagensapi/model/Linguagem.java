package com.github.filiperobot.filipelinguagensapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "principaisLinguagens")
public class Linguagem {
    @Id
    private String id;
    private String title;
    private String url;
    private int ranking;

    public Linguagem() {
    }

    public Linguagem(String title, String url, int ranking) {
        this.title = title;
        this.url = url;
        this.ranking = ranking;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public int getRanking() {
        return ranking;
    }
}
