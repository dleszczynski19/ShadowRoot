package com.helpers;

import io.github.sukgu.Shadow;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

public class ShadowWebElementHelper extends WebElementHelper {
    private final Shadow shadow;
    private Map<String, WebElement> shadowRootMap = new HashMap<>();

    public ShadowWebElementHelper(WebDriver driver) {
        super(driver);
        this.shadow = new Shadow(driver);
    }

    public WebElement getShadowRootElement(String selector) {
        WebElement element = shadow.findElement(selector);
        highlightElement(element);
        return element;
    }

    public WebElement getShadowRootBySearchContext(WebElement shadowHost, String selector) {
        return getElement(shadowHost, selector);
    }

    public WebElement getShadowRootBySearchContext(WebElement shadowHost, String selector, String keyValue) {
        WebElement element = getElement(shadowHost, selector);
        shadowRootMap.put(keyValue, element);
        return element;
    }

    public Map<String, WebElement> getShadowRootMap() {
        return shadowRootMap;
    }

    private WebElement getElement(WebElement shadowHost, String selector) {
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        highlightElement(shadowHost);
        WebElement element = shadowRoot.findElement(By.cssSelector(selector));
        highlightElement(element);

        return element;
    }
}
