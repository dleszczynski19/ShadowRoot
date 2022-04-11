package com.test.chrome;

import com.test.TestBase;
import com.helpers.Timer;
import com.pages.chrome.chrome.DownloadPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownloadTests extends TestBase {
    private static final String URL = "chrome://downloads/";
    private static Logger log = LoggerFactory.getLogger(DownloadTests.class);

    @Test
    @DisplayName("Shadow root test using sukgu library")
    public void shouldManageShadowRoot() {
        // Arrange
        DownloadPage downloadPage = new DownloadPage(driver);
        driver.get(URL);

        // Act
        Timer watch = Timer.start();

        log.info("First Shadow Root Element: " + downloadPage.getShadowRootElement("downloads-toolbar").getText());
        log.info("Second Shadow Root Element: " + downloadPage.getShadowRootElement("cr-icon-button").getAttribute("id"));
        log.info("Third Shadow Root Element: " + downloadPage.getShadowRootElement("#moreActions #icon iron-icon").getTagName());
        downloadPage.getShadowRootElement("#moreActions #icon iron-icon").click();
        log.info("Fourth Shadow Root Element: " + downloadPage
                .getShadowRootElement("#moreActions #icon iron-icon svg").getAttribute("style"));

        log.info("Time elapsed: " + watch.time());
    }

    @Test
    @DisplayName("Shadow root old approach test")
    public void shouldManageOldApproachShadowRoot() {
        // Arrange
        DownloadPage downloadPage = new DownloadPage(driver);
        driver.get(URL);

        // Act
        Timer watch = Timer.start();

        log.info("First Shadow Root Element: " + downloadPage.getShadowRootByJS(driver.findElement(By.cssSelector("downloads-manager")),
                "downloads-toolbar").getText());
        log.info("Second Shadow Root Element: " + downloadPage.getShadowRootByJS(downloadPage.getShadowRootMap().get("1"),
                "cr-icon-button", "second").getAttribute("id"));
        log.info("Third Shadow Root Element: " + downloadPage.getShadowRootByJS(downloadPage.getShadowRootMap().get("second"),
                "#icon iron-icon").getTagName());
        downloadPage.getShadowRootByJS(downloadPage.getShadowRootMap().get("second"), "#icon iron-icon").click();
        log.info("Fourth Shadow Root Element: " + downloadPage.getShadowRootByJS(downloadPage.getShadowRootMap().get("3"),
                "svg").getAttribute("style"));

        log.info("Time elapsed: " + watch.time());
    }
}