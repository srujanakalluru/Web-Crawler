package com.webcrawler;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class WebCrawlerFactoryTest {

    @Test
    void shouldCreateWebCrawler() {
        final var factory = new WebCrawlerFactory();
        final var crawler = factory.getWebCrawler(TestData.TEST_URL);
        assertNotNull(crawler);
    }
}
