package com.pages;

import com.shadow.driver.ShadowDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class PageBase {
    protected static final int TIMEOUT_S = 5;
    private static final Logger logger = LoggerFactory.getLogger(PageBase.class.getName());
    private static final int SLEEP_MS = 100;

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final JavascriptExecutor jse;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.jse = initJSE(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_S), Duration.ofMillis(SLEEP_MS));
        logger.debug("Created WebDriverWait with timeout: " + TIMEOUT_S + "s and sleep: " + SLEEP_MS + "ms");
        PageFactory.initElements(driver, this);
    }

    public void highlightElement(WebElement element) {
        jse.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')", element);
    }

    public void clearHighlight(WebElement element) {
        jse.executeScript("arguments[0].setAttribute('style', 'border:0px solid transparent; background:transparent')", element);
    }

    private JavascriptExecutor initJSE(WebDriver driver) {
        if (driver instanceof ShadowDriver) {
            return (JavascriptExecutor) ((ShadowDriver) driver).getDriver();
        }
        return (JavascriptExecutor) driver;
    }
}
