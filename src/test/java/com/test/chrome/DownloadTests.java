package com.test.chrome;

import com.helpers.ShadowWebElementHelper;
import com.helpers.Timer;
import com.test.TestBase;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.Matchers.equalTo;

public class DownloadTests extends TestBase {
    private static final String URL = "chrome://downloads/";
    private static Logger log = LoggerFactory.getLogger(DownloadTests.class);

    @Test
    @DisplayName("Shadow root test using sukgu library")
    public void shouldManageShadowRoot() {
        // Arrange
        ShadowWebElementHelper shadowHelper = new ShadowWebElementHelper(driver);
        driver.get(URL);

        // Act
        Timer watch = Timer.start();
        
        WebElement firstShadowLevel = shadowHelper.getShadowRootElement("downloads-toolbar");
        log.info("First Shadow Root Element: " + firstShadowLevel.getText());

        WebElement secondShadowLevel = shadowHelper.getShadowRootElement("cr-icon-button");
        log.info("Second Shadow Root Element: " + secondShadowLevel.getAttribute("id"));

        WebElement thirdShadowLevel = shadowHelper.getShadowRootElement("#moreActions #icon iron-icon");
        log.info("Third Shadow Root Element: " + thirdShadowLevel.getTagName());

        WebElement fourthShadowLevel = shadowHelper.getShadowRootElement("#moreActions #icon iron-icon svg");
        log.info("Fourth Shadow Root Element: " + fourthShadowLevel.getAttribute("style"));

        log.info("Time elapsed: " + watch.time());

        // Assert
        MatcherAssert.assertThat(fourthShadowLevel.getTagName(), equalTo("svg"));
        log.info(passed, passedMessage);
    }

    @Test
    @DisplayName("Shadow root old approach test using JS")
    public void shouldManageOldApproachShadowRoot() {
        // Arrange
        ShadowWebElementHelper shadowHelper = new ShadowWebElementHelper(driver);
        driver.get(URL);

        // Act
        Timer watch = Timer.start();

        WebElement firstShadowRootElement = driver.findElement(By.cssSelector("downloads-manager"));
        WebElement firstShadowLevel = shadowHelper.getShadowRootByJS(firstShadowRootElement, "downloads-toolbar");
        log.info("First Shadow Root Element: " + firstShadowLevel.getText());

        WebElement secondShadowRootElement = shadowHelper.getShadowRootMap().get("1");
        WebElement secondShadowLevel = shadowHelper.getShadowRootByJS(secondShadowRootElement, "cr-icon-button", "second");
        log.info("Second Shadow Root Element: " + secondShadowLevel.getAttribute("id"));

        WebElement thirdShadowRootElement = shadowHelper.getShadowRootMap().get("second");
        WebElement thirdShadowLevel = shadowHelper.getShadowRootByJS(thirdShadowRootElement, "#icon iron-icon");
        log.info("Third Shadow Root Element: " + thirdShadowLevel.getTagName());

        WebElement fourthShadowRootElement = shadowHelper.getShadowRootMap().get("3");
        WebElement fourthShadowLevel = shadowHelper.getShadowRootByJS(fourthShadowRootElement, "svg");
        log.info("Fourth Shadow Root Element: " + fourthShadowLevel.getAttribute("style"));

        log.info("Time elapsed: " + watch.time());

        // Assert
        MatcherAssert.assertThat(fourthShadowLevel.getAttribute("style"), equalTo("svg"));
        log.info(passed, passedMessage);
    }
}