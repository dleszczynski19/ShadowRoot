package com.pages;

import com.shadow.driver.ShadowDriver;
import io.github.sukgu.support.ElementFieldDecorator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
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
        if (driver instanceof ShadowDriver) {
            WebDriver pageFactoryDriver = ((ShadowDriver) driver).getDriver();
            ElementFieldDecorator decorator = new ElementFieldDecorator(new DefaultElementLocatorFactory(pageFactoryDriver));
            // need to use decorator if you want to use @FindElementBy in your PageFactory model.
            PageFactory.initElements(decorator, this);
            this.jse = (JavascriptExecutor) pageFactoryDriver;
        } else {
            PageFactory.initElements(driver, this);
            this.jse = (JavascriptExecutor) driver;
        }
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(TIMEOUT_S), Duration.ofMillis(SLEEP_MS));
        logger.debug("Created WebDriverWait with timeout: " + TIMEOUT_S + "s and sleep: " + SLEEP_MS + "ms");
    }

    public void highlightElement(WebElement element) {
        jse.executeScript("const hue = Math.floor(Math.random() * 360);" +
                "arguments[0].style.border = '2px solid hsl(' + hue + ', 100%, 80%)';" +
                "arguments[0].style.background = 'hsl(' + hue + ', 100%, 50%)';", element);
    }

    public void clearHighlightBackground(WebElement element) {
        jse.executeScript("arguments[0].style.background = 'transparent'", element);
    }

    public void clearHighlightBorder(WebElement element) {
        jse.executeScript("arguments[0].style.border = 'none'", element);
    }
}
