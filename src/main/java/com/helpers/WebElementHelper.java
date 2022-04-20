package com.helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebElementHelper {
    private final WebDriver driver;

    public WebElementHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void clearHighlightBackground(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.background = 'transparent'", element);
    }

    public void setHighlightElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("const hue = Math.floor(Math.random() * 360);" +
                "arguments[0].style.border = '2px solid hsl(' + hue + ', 100%, 80%)';" +
                "arguments[0].style.background = 'hsl(' + hue + ', 100%, 50%)';", element);
    }

    public void clearHighlightBorder(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border = 'none'", element);
    }

    public void highlightElement(WebElement element) {
//        setHighlightElement(element);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        clearHighlightBackground(element);
    }
}
