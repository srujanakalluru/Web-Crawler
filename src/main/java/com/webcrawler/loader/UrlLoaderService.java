package com.webcrawler.loader;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * The class for loading data from URLs.
 */
public class UrlLoaderService implements UrlLoaderServiceInterface {

    private static final String GET_BODY_TEXT_SCRIPT = "return document?.body?.innerText";
    private static final String GET_URLS_SCRIPT = "return Array.from(document.getElementsByTagName('a'), a => a.href)";
    private static final UrlLoaderService INSTANCE = new UrlLoaderService();

    private final transient WebDriver webDriver;

    private UrlLoaderService() {
        WebDriverManager.chromedriver().setup();

        final var options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");  
        options.addArguments("--disable-dev-shm-usage"); 

        this.webDriver = new ChromeDriver(options);
    }

    protected UrlLoaderService(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * Returns singleton instance of UrlLoaderService.
     *
     * @return the single instance of UrlLoaderService
     */
    public static UrlLoaderService getInstance() {
        return INSTANCE;
    }

    /**
     * Loads the page for the given URL, extract all links and body text.
     *
     * @param url the URL to load
     * @return the loaded URL links and text
     */
    @Override
    public LinksAndText loadUrlTextAndLinks(final String url) {
        webDriver.get(url);
        new WebDriverWait(webDriver, Duration.ofSeconds(10L)).until(
                driver -> driver.findElement(By.tagName("body")).isDisplayed());
        final var js = (JavascriptExecutor) webDriver;
        final var text = (String) js.executeScript(GET_BODY_TEXT_SCRIPT);
        final var links = (List<String>) js.executeScript(GET_URLS_SCRIPT);

        return new LinksAndText(links, text);
    }
}

