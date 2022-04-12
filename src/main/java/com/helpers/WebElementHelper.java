package com.helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebElementHelper {
    protected static void setHighlightElement(WebElement element, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("const hue = Math.floor(Math.random() * 360);" +
                "arguments[0].style.border = '2px solid hsl(' + hue + ', 100%, 80%)';" +
                "arguments[0].style.background = 'hsl(' + hue + ', 100%, 50%)';", element);
    }

    protected static void clearHighlightBackground(WebElement element, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.background = 'transparent'", element);
    }

    protected static void clearHighlightBorder(WebElement element, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border = 'none'", element);
    }

    public static void highlightElement(WebElement element, WebDriver driver) {
        setHighlightElement(element, driver);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clearHighlightBackground(element, driver);
    }
}
