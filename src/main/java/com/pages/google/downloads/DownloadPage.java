package com.pages.google.downloads;

import com.pages.ShadowPageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DownloadPage extends ShadowPageBase {

    public DownloadPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getShadowRootElement(String selector) {
        highlightElement(driver.findElement(By.cssSelector(selector)));
        clearHighlight(driver.findElement(By.cssSelector(selector)));
        return driver.findElement(By.cssSelector(selector));
    }
}