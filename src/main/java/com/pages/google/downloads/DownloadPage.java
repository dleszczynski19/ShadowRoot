package com.pages.google.downloads;

import com.pages.ShadowPageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class DownloadPage extends ShadowPageBase {
    private Map<Integer, WebElement> shadowRootMap;

    public DownloadPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getShadowRootElement(String selector) {
        highlightElement(driver.findElement(By.cssSelector(selector)));
        clearHighlightBackground(driver.findElement(By.cssSelector(selector)));
        return driver.findElement(By.cssSelector(selector));
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