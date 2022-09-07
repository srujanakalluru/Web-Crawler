package com.webcrawler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.webcrawler.loader.LinksAndText;
import com.webcrawler.loader.UrlLoaderServiceInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WebCrawlerTest {

    @Mock
    private UrlLoaderServiceInterface loaderService;

    @Test
    void shouldUseSingletonUrlLoader() {
        final WebCrawler webCrawler = new WebCrawler(TestData.TEST_URL);
        assertThat(webCrawler).isNotNull();
    }

    @Test
    void shouldReturnWordCount() {
        when(loaderService.loadUrlTextAndLinks(TestData.TEST_URL))
                .thenReturn(new LinksAndText(TestData.LINKS, TestData.BODY_TEXT));
        final long crawl = new WebCrawler(loaderService, TestData.TEST_URL).crawl();
        assertThat(crawl).isEqualTo(TestData.EXPECTED_WORD_COUNT);
    }
}
