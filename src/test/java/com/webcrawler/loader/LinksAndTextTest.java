package com.webcrawler.loader;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;

class LinksAndTextTest {

    @Test
    void getLinks() {
        final var linksAndText = new LinksAndText(List.of(), "text");
        assertThrows(UnsupportedOperationException.class, () -> linksAndText.getLinks().add("link"));
    }
}
