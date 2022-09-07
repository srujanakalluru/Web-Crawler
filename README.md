# Web Crawler
This repository contains a web crawler that fetches the number of times the word ‘Kayako’ appears on Kayako's product homepage (https://kayako.com/).
1. Crawl the subpages of Kayako’s website, in addition to the homepage upto a configurable depth. For detailed information about what "level" means, look into the Additional information section.
2. Make the URL, word, and scan depth level command line parameters of the application, with default word 'kayako' and depth 2.
3. The repository contains local copy of the website to be used for verification. Use command `bash verification.sh` to run your app against the local server. Expected response for word 'kayako' and depth 2 is 1084.


### Requirements:
* Find the target word “Kayako” *1084* times in the subpages against the local version of the website and exits the page once the crawling is done
* Code must be free from defects and regressions
* 100% test coverage and pass both automatic checks (Analyze and Test)
* Extend CLI parsing to include the parameter 
* Make the appropriate method-level decisions that make the code simple, modular, readable, and easily changeable


### Additional information:
* You can change and move the code, except for main.ts
* `./gradlew run`  will be used to run the crawler and collect the result
* If two URLs only differ in the "fragment" part they are considered identical
* ‘Two levels’ means that it takes at most two clicks on hyperlinks to get to the subpage
![First-four-levels-of-a-web-site](https://user-images.githubusercontent.com/88856224/159994289-a973f85f-3003-42fd-bc81-015d1bd59465.png)

### Useful commands
* `./gradlew clean check -x test` - runs the code analysis tool
* `./gradlew clean test -x check` - runs the unit tests
* `./gradlew run` - runs the application
* `./gradlew run --args "-u www.google.com"` - runs the application with parameters
* `bash verification.sh` - runs the verification process

