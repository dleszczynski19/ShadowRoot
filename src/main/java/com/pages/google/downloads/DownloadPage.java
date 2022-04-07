package com.pages.google.downloads;

import com.pages.ShadowPageBase;
import io.github.sukgu.Shadow;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DownloadPage extends ShadowPageBase {

    public DownloadPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getShadowRootElement(String cssSelector) {
        highlightElement(shadow.findElement(By.cssSelector(cssSelector)));
        clearHighlight(shadow.findElement(By.cssSelector(cssSelector)));
        return shadow.findElement(By.cssSelector(cssSelector));
    }
}