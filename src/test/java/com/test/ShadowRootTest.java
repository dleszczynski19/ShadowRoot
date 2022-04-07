package com.test;

import com.configuration.TestBase;
import com.pages.google.downloads.DownloadPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShadowRootTest extends TestBase {
    private static Logger log = LoggerFactory.getLogger(ShadowRootTest.class);

    @Test
    @DisplayName("Shadow root test")
    public void shouldManageShadowRoot() {

        DownloadPage downloadPage = new DownloadPage(driver);

        driver.get("chrome://downloads/");
        log.info("First Shadow Root Element: " + downloadPage.getShadowRootElement("downloads-toolbar").getText());
        log.info("Second Shadow Root Element: " + downloadPage.getShadowRootElement("cr-toolbar").getText());
        log.info("Third Shadow Root Element: " + downloadPage.getShadowRootElement("cr-icon-button").getAttribute("id"));
        log.info("Fourth Shadow Root Element: " + downloadPage.getShadowRootElement("#moreActions #icon iron-icon").getTagName());
        downloadPage.getShadowRootElement("#moreActions #icon iron-icon").click();
        log.info("Fifth Shadow Root Element: " + downloadPage
                .getShadowRootElement("#moreActions #icon iron-icon svg").getAttribute("style"));
    }
}