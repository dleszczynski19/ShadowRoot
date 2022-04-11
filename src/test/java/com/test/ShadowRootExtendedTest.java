package com.test;

import com.configuration.TestBase;
import com.pages.google.chrome.DownloadPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShadowRootExtendedTest extends TestBase {
    private static Logger log = LoggerFactory.getLogger(ShadowRootExtendedTest.class);

    @Test
    @DisplayName("Shadow root old approach test")
    public void shouldManageShadowRoot() {
        DownloadPage downloadPage = new DownloadPage(driver);
        driver.get("chrome://downloads/");

        long startTime = System.currentTimeMillis();
        WebElement firstLevelShadow = downloadPage.getShadowRootByJS(driver.findElement(By.cssSelector("downloads-manager")),
                "downloads-toolbar");
        WebElement secondLevelShadow = downloadPage.getShadowRootByJS(firstLevelShadow, "cr-icon-button");
        WebElement thirdLevelShadow = downloadPage.getShadowRootByJS(secondLevelShadow, "#icon iron-icon");
        WebElement fourthLevelShadow = downloadPage.getShadowRootByJS(thirdLevelShadow, "svg");
        log.info("First Shadow Root Element: " + firstLevelShadow.getText());
        log.info("Second Shadow Root Element: " + secondLevelShadow.getAttribute("id"));
        log.info("Third Shadow Root Element: " + fourthLevelShadow.getTagName());
        thirdLevelShadow.click();
        log.info("Fourth Shadow Root Element: " + fourthLevelShadow.getAttribute("style"));

        long estimatedTime = System.currentTimeMillis() - startTime;
        log.info("Time: " + estimatedTime + "ms");
    }
}
