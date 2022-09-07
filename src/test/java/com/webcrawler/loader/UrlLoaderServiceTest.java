package com.webcrawler.loader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.webcrawler.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

@ExtendWith(MockitoExtension.class)
class UrlLoaderServiceTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private ChromeDriver driver;

    private UrlLoaderService testInstance;

    @Test
    void getInstance() {
        assertThat(UrlLoaderService.getInstance()).isNotNull();
    }

    @BeforeEach
    void setup() {
        testInstance = new UrlLoaderService(driver);
    }

    @Test
    void loadUrlTextAndLinks() {

        when(driver.findElement(By.tagName("body")).isDisplayed())
                .thenReturn(false).thenReturn(false).thenReturn(true);
        when(driver.executeScript(anyString()))
                .thenReturn(TestData.BODY_TEXT).thenReturn(TestData.LINKS);

        final LinksAndText linksAndText = testInstance.loadUrlTextAndLinks(TestData.TEST_URL);

        assertThat(linksAndText.getText()).isEqualTo(TestData.BODY_TEXT);
        assertThat(linksAndText.getLinks()).isEqualTo(TestData.LINKS);

    }
}
