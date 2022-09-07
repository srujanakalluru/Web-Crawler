package com.webcrawler;

import java.util.List;

/**
 * Test data for the web crawler.
 */
public class TestData {
    public static final String TEST_URL = "http://kayako.com";
    public static final List<String> LINKS = List.of("link1", "link2");
    public static final String BODY_TEXT = "kayako Kayako is a customer support software company";
    public static final Long EXPECTED_WORD_COUNT = 2L;
}
