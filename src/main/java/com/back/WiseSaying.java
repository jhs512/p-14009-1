package com.back;

public class WiseSaying {
    Integer id;
    String content;
    String author;

    public WiseSaying(Integer id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    @Override
    public String toString() {
        return id + " / " + author + " / " + content;
    }
}
