package com.pages;

import com.shadow.driver.ShadowDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static com.helpers.WebElementHelper.highlightElement;

public abstract class ShadowPageBase extends PageBase {
    private static final Logger logger = LoggerFactory.getLogger(ShadowPageBase.class.getName());
    private Map<String, WebElement> shadowRootMap = new HashMap<>();

    public ShadowPageBase(WebDriver driver) {
        super(new ShadowDriver(driver));
        logger.info("Created ShadowPage");
    }

    public WebElement getShadowRootElement(String selector) {   // using shadow driver wrapper
        WebElement element = driver.findElement(By.cssSelector(selector));
        highlightElement(element, driver);
        return element;
    }

    public WebElement getShadowRootByJS(WebElement shadowHost, String selector) { // using js, old approach
        WebElement element = getElement(shadowHost, selector);
        shadowRootMap.put(String.valueOf(shadowRootMap.size() + 1), element);
        return element;
    }

    public WebElement getShadowRootByJS(WebElement shadowHost, String selector, String keyValue) { // using js, old approach
        WebElement element = getElement(shadowHost, selector);
        shadowRootMap.put(keyValue, element);
        return element;
    }

    public Map<String, WebElement> getShadowRootMap() {
        return shadowRootMap;
    }

    private WebElement getElement(WebElement shadowHost, String selector) {
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        highlightElement(shadowHost, driver);

        WebElement element = shadowRoot.findElement(By.cssSelector(selector));
        highlightElement(element, driver);
        return element;
    }
}
