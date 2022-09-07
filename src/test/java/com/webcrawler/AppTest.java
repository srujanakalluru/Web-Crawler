package com.webcrawler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.assertj.core.internal.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AppTest {

    @Mock
    private WebCrawlerFactory webCrawlerFactory;
    @Mock
    private WebCrawler webCrawler;

    @Test
    void shouldUseUrlFromCli() throws ParseException {
        when(webCrawlerFactory.getWebCrawler(TestData.TEST_URL)).thenReturn(webCrawler);
        when(webCrawler.crawl()).thenReturn(TestData.EXPECTED_WORD_COUNT);

        final var app = new App(webCrawlerFactory);
        final long count = app.run("-u", TestData.TEST_URL);

        assertThat(count).isEqualTo(TestData.EXPECTED_WORD_COUNT);
        verify(webCrawlerFactory).getWebCrawler(TestData.TEST_URL);
        verify(webCrawler).crawl();
        verifyNoMoreInteractions(webCrawlerFactory, webCrawler);
    }

    @Test
    void shouldUseDefaultUrlWhenNoArg() throws ParseException {
        final String defaultUrl = "https://kayako.com";
        when(webCrawlerFactory.getWebCrawler(defaultUrl)).thenReturn(webCrawler);
        when(webCrawler.crawl()).thenReturn(TestData.EXPECTED_WORD_COUNT);

        final var app = new App(webCrawlerFactory);
        final long count = app.run();

        assertThat(count).isEqualTo(TestData.EXPECTED_WORD_COUNT);
        verify(webCrawlerFactory).getWebCrawler(defaultUrl);
        verify(webCrawler).crawl();
        verifyNoMoreInteractions(webCrawlerFactory, webCrawler);
    }

    @Test
    @Advice.AssignReturned.ToThrown
    void shouldThrowWhenInvalidArg() throws ParseException {
        final var app = new App(webCrawlerFactory);

        final var exception = assertThrows(UnrecognizedOptionException.class, () -> app.run("-i"));
        assertThat(exception.getMessage()).isEqualTo("Unrecognized option: -i");
        verifyNoMoreInteractions(webCrawlerFactory, webCrawler);
    }
}
