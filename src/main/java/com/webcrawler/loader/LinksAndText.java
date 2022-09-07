package com.webcrawler.loader;

import java.util.Collections;
import java.util.List;
import lombok.Getter;

/**
 * The class for holding the page's links and text.
 */
@Getter
public class LinksAndText {

    private final List<String> links;
    private final String text;

    /**
     * The LinksAndText constructor.
     * @param links the links, saved as unmodifiable list
     * @param text the text
     */
    public LinksAndText(final List<String> links, final String text) {
        this.links = Collections.unmodifiableList(links);
        this.text = text;
    }
}
