package com.google.downloads;

import io.github.sukgu.Shadow;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DownloadPage {
    private WebDriver driver;
    private JavascriptExecutor jsExecutor;


    public DownloadPage(WebDriver driver) {
        this.driver = driver;
        jsExecutor = (JavascriptExecutor) driver;
    }

    public WebElement getShadowRootElement(String cssSelector) {
        Shadow shadow = new Shadow(driver);
        highlightElement(shadow.findElement(cssSelector));
        clearHighlight(shadow.findElement(cssSelector));
        return shadow.findElement(cssSelector);
    }

    public void highlightElement(WebElement element){
        jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')", element);
    }

    public void clearHighlight(WebElement element){
        jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:0px solid transparent; background:transparent')", element);
    }
}