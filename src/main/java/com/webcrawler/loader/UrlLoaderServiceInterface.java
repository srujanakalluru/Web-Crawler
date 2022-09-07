package com.webcrawler.loader;

/**
 * Interface for service that loads urls from a source.
 */
public interface UrlLoaderServiceInterface {

    /**
     * Loads the page for the given URL, extract all links and body text.
     *
     * @param url the URL to load
     * @return the loaded URL links and text
     */
    LinksAndText loadUrlTextAndLinks(String url);
}
