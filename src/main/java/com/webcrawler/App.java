package com.webcrawler;

import java.util.logging.Level;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * The main application class.
 */
@Log
@RequiredArgsConstructor
public final class App {
    private static final String DEFAULT_URL = "https://kayako.com";

    private final WebCrawlerFactory crawlerFactory;

    /**
     * The main method.
     *
     * @param args the command line arguments
     */
    @SuppressWarnings("PMD.SystemPrintln")
    @ExcludeFromJacocoGeneratedReport
    public static void main(final String... args) {
        final WebCrawlerFactory crawlerFactory = new WebCrawlerFactory();
        try {
            final long count = new App(crawlerFactory).run(args);
            System.out.println("Found " + count + " instances of 'kayako' in the body of the page");
        } catch (ParseException e) {
            log.log(Level.SEVERE, "Failed to parse command line arguments", e);
        }

    }

    /**
     * Run the application.
     * @param args the command line arguments
     * @return  the number of instances of 'kayako' in the body of the page
     * @throws ParseException if the command line arguments are invalid
     */
    public long run(final String... args) throws ParseException {
        final var options = new Options();
        final var urlOption = Option.builder().option("u").longOpt("url").desc("URL to crawl")
                .hasArg().argName("url").build();
        options.addOption(urlOption);

        final var parser = new DefaultParser();
        final var cmd = parser.parse(options, args);
        final String url = cmd.getOptionValue(urlOption, DEFAULT_URL);
        final WebCrawler webCrawler = crawlerFactory.getWebCrawler(url);
        return webCrawler.crawl();
    }

}
