package com.webcrawler;

import com.webcrawler.loader.LinksAndText;
import com.webcrawler.loader.UrlLoaderService;
import com.webcrawler.loader.UrlLoaderServiceInterface;

import java.util.regex.Pattern;

/**
 * WebCrawler class.
 */
public class WebCrawler {
    private final UrlLoaderServiceInterface urlLoaderService;
    private final String homepage;

    /**
     * Create web crawler for the homepage.
     * @param homepage URL of the page to start the crawler from.
     */
    public WebCrawler(final String homepage) {
        this(UrlLoaderService.getInstance(), homepage);
    }

    /**
     * Create web crawler for the homepage.
     * @param urlLoaderService the service to use to load the pages
     * @param homepage URL of the page to start the crawler from.
     */
    WebCrawler(final UrlLoaderServiceInterface urlLoaderService, final String homepage) {
        this.urlLoaderService = urlLoaderService;
        this.homepage = homepage;
    }

    /**
     * Processes the given URL.
     *
     * @return the number of instances of 'kayako' in the body of the page
     */
    public long crawl() {
        final LinksAndText linksAndText = this.urlLoaderService.loadUrlTextAndLinks(this.homepage);
        final Pattern wordPattern = Pattern.compile("kayako", Pattern.CASE_INSENSITIVE);
        return wordPattern.matcher(linksAndText.getText()).results().count();
    }

}
