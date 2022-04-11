package com.pages;

import com.shadow.driver.ShadowDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ShadowPageBase extends PageBase {
    private static final Logger logger = LoggerFactory.getLogger(ShadowPageBase.class.getName());

    public ShadowPageBase(WebDriver driver) {
        super(new ShadowDriver(driver));
        logger.info("Created ShadowPage");
    }

    public WebElement getShadowRootElement(String selector) {
        WebElement element = driver.findElement(By.cssSelector(selector));
        highlightElement(element);
        clearHighlightBackground(element);
        return element;
    }

    public WebElement getShadowRootByJS(WebElement shadowHost, String selector) {
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        highlightElement(shadowHost);
        clearHighlightBackground(shadowHost);
        highlightElement(shadowRoot.findElement(By.cssSelector(selector)));
        clearHighlightBackground(shadowRoot.findElement(By.cssSelector(selector)));
        return shadowRoot.findElement(By.cssSelector(selector));
    }
}
