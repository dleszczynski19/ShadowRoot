package com.helpers;

import io.github.sukgu.Shadow;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

import static com.helpers.WebElementHelper.highlightElement;

public class ShadowWebElementHelper {
    private final WebDriver driver;
    private final Shadow shadow;
    private Map<String, WebElement> shadowRootMap = new HashMap<>();

    public ShadowWebElementHelper(WebDriver driver) {
        this.driver = driver;
        this.shadow = new Shadow(driver);
    }

    public WebElement getShadowRootElement(String selector) {
        WebElement element = shadow.findElement(selector);
        highlightElement(element, driver);
        return element;
    }

    public WebElement getShadowRootByJS(WebElement shadowHost, String selector) {
        WebElement element = getElement(shadowHost, selector);
        shadowRootMap.put(String.valueOf(shadowRootMap.size() + 1), element);
        return element;
    }

    public WebElement getShadowRootByJS(WebElement shadowHost, String selector, String keyValue) {
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
