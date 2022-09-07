plugins {
    id("java")
    id("checkstyle")
    id("com.github.spotbugs") version "4.6.0"
    id("pmd")
    id("jacoco")
    application
}

group = "com"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

val lombokVersion = "1.18.22"
val lombok = "org.projectlombok:lombok:$lombokVersion"

dependencies {
    spotbugs("com.github.spotbugs:spotbugs:4.6.0")
    spotbugsPlugins("com.mebigfatguy.sb-contrib:sb-contrib:7.4.7")
    spotbugsPlugins("com.h3xstream.findsecbugs:findsecbugs-plugin:1.11.0")
    spotbugsPlugins("jp.skypencil.findbugs.slf4j:bug-pattern:1.5.0@jar")

    compileOnly(lombok)
    annotationProcessor(lombok)

    implementation("commons-cli:commons-cli:1.5.0")
    implementation("org.seleniumhq.selenium:selenium-java:4.1.3")
    implementation("io.github.bonigarcia:webdrivermanager:5.1.0")

    testCompileOnly(lombok)
    testAnnotationProcessor(lombok)
    testImplementation(TestDependencies.junitJupiterApi)
    testImplementation(TestDependencies.junitJupiterEngine)
    testImplementation(TestDependencies.junitJupiterParams)
    testImplementation(TestDependencies.mockitoInline)
    testImplementation(TestDependencies.mockitoJunit)
    testImplementation(TestDependencies.assertJCore)
}


object TestVersions {
    const val mockitoVersion = "4.4.0"
    const val junitVersion = "5.7.2"
    const val assertJVersion = "3.22.0"
}

object TestDependencies {
    const val junitJupiterApi = "org.junit.jupiter:junit-jupiter-api:${TestVersions.junitVersion}"
    const val junitJupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${TestVersions.junitVersion}"
    const val junitJupiterParams = "org.junit.jupiter:junit-jupiter-params:${TestVersions.junitVersion}"
    const val mockitoJunit = "org.mockito:mockito-junit-jupiter:${TestVersions.mockitoVersion}"
    const val mockitoInline = "org.mockito:mockito-inline:${TestVersions.mockitoVersion}"
    const val assertJCore = "org.assertj:assertj-core:${TestVersions.assertJVersion}"
}

checkstyle {
    toolVersion = "8.37"
    isIgnoreFailures = false
    maxWarnings = 0
    maxErrors = 0
    configFile = file("$rootDir/config/checkstyle/checkstyle.xml")
}

spotbugs {
    ignoreFailures.set(false)
    effort.set(com.github.spotbugs.snom.Effort.MAX)
    reportLevel.set(com.github.spotbugs.snom.Confidence.HIGH)
    excludeFilter.fileValue(File("$rootDir/config/spotbugs/exclude-filter.xml"))

    tasks.spotbugsMain {
        reports.create("html") { isEnabled = true; setStylesheet("fancy-hist.xsl") }
    }

    tasks.spotbugsTest {
        reports.create("xml") { enabled = true }
        reports.create("html") { isEnabled = true; setStylesheet("fancy-hist.xsl") }
    }
}

pmd {
    toolVersion = "6.43.0"
    isIgnoreFailures = false
    targetJdk = TargetJdk.VERSION_1_7
    ruleSets = emptyList()
    rulesMinimumPriority.set(5)
    reportsDir = file("${project.buildDir}/reports/pmd")
    ruleSetFiles = files("$rootDir/config/pmd/ruleset.xml")
}


tasks.named<Test>("test") {
    useJUnitPlatform()
    finalizedBy(tasks.named("jacocoTestReport"))
    extensions.configure(JacocoTaskExtension::class) {
        setDestinationFile(file("$buildDir/jacoco/jacocoTest.exec"))
        classDumpDir = file("$buildDir/jacoco/classpathdumps")
    }

}

tasks {
    test {
        finalizedBy(named<JacocoCoverageVerification>("jacocoTestCoverageVerification"))
    }

    withType<JacocoCoverageVerification> {
        violationRules {
            rule {
                element = "CLASS"
                excludes = "".split(",")
                limit { counter = "INSTRUCTION"; minimum = BigDecimal.ONE }
                limit { counter = "BRANCH"; minimum = BigDecimal.ONE }
                limit { counter = "CLASS"; minimum = BigDecimal.ONE }
                limit { counter = "METHOD"; minimum = BigDecimal.ONE }
            }
        }
    }
}

application {
    mainClass.set("com.webcrawler.App")
}
