package com.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class TestBase {
    public static Marker passed = MarkerFactory.getMarker("PASSED");
    public static String passedMessage = "Test passed!";
    private static Logger log = LoggerFactory.getLogger(TestBase.class);
    public WebDriver driver;

    @BeforeAll
    static void setup() {
        WebDriverManager.chromedriver().setup();
        log.info("Driver setup properly");
    }

    @BeforeEach
    void initializeDriver() {
        driver = new ChromeDriver(new ChromeOptions().addArguments("start-maximized"));
        log.info("Driver initialized properly");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        log.info("Driver closed properly");
    }
}