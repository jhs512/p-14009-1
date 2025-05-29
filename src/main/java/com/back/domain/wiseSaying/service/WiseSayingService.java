package com.back.domain.wiseSaying.service;

import com.back.WiseSaying;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingService {
    private int lastId = 0;
    private final List<WiseSaying> wiseSayings = new ArrayList<>();

    public List<WiseSaying> findForList() {
        return wiseSayings.reversed();
    }

    public WiseSaying write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(++lastId, content, author);

        wiseSayings.add(wiseSaying);

        return wiseSaying;
    }

    public WiseSaying findById(int id) {
        return wiseSayings
                .stream()
                .filter(wiseSaying -> wiseSaying.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);
    }

    public boolean delete(int id) {
        return wiseSayings
                .removeIf(
                        wiseSaying -> wiseSaying.getId() == id
                );
    }
}
