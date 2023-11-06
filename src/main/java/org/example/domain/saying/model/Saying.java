package org.example.domain.saying.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Saying {
    private int id;
    private String content;
    private String author;

    public Saying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }
}