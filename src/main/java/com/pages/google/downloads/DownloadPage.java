package com.pages.google.downloads;

import com.pages.ShadowPageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

public class DownloadPage extends ShadowPageBase {
    private Map<String, WebElement> shadowRootMap = new HashMap<>();

    public DownloadPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getShadowRootElement(String selector) {
        WebElement element = driver.findElement(By.cssSelector(selector));
        highlightElement(element);
        clearHighlightBackground(element);
        return element;
    }

    public WebElement getShadowRootByJS(WebElement shadowHost, String selector) {
        WebElement element = getElement(shadowHost, selector);
        shadowRootMap.put(String.valueOf(getMapSize() + 1), element);
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
        highlightElement(shadowHost);
        clearHighlightBackground(shadowHost);
        WebElement element = shadowRoot.findElement(By.cssSelector(selector));
        highlightElement(element);
        clearHighlightBackground(element);
        return element;
    }

    private int getMapSize() {
        return shadowRootMap.size();
    }
}