package com.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class PageBase {
    protected static final int TIMEOUT_S = 5;
    private static final Logger logger = LoggerFactory.getLogger(PageBase.class.getName());
    private static final int SLEEP_MS = 100;

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor jse;

    public PageBase(WebDriver driver) {
        initDrivers(driver);
        PageFactory.initElements(driver, this);
    }

    public void initDrivers(WebDriver driver) {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_S), Duration.ofMillis(SLEEP_MS));
        logger.debug("Created WebDriverWait with timeout: " + TIMEOUT_S + "s and sleep: " + SLEEP_MS + "ms");
    }
}
