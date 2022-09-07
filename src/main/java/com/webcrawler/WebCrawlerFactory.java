package com.webcrawler;

/**
 * Factory class to create WebCrawler objects.
 */
public class WebCrawlerFactory {

    /**
     * Creates a new WebCrawler object.
     * @param url The URL to pass to crawler.
     * @return A new WebCrawler object.
     */
    public WebCrawler getWebCrawler(final String url) {
        return new WebCrawler(url);
    }
}
