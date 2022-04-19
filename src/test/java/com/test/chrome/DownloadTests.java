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
    private static Logger logger = LoggerFactory.getLogger(DownloadTests.class);

    @Test
    @DisplayName("Shadow root test using sukgu library")
    public void shouldManageShadowRoot() {
        // Arrange
        ShadowWebElementHelper shadowHelper = new ShadowWebElementHelper(driver);
        driver.get(URL);

        // Act
        Timer watch = Timer.start();

        WebElement firstShadowLevel = shadowHelper.getShadowRootElement("downloads-toolbar");
        logger.info("First Shadow Root Element: " + firstShadowLevel.getText());

        WebElement secondShadowLevel = shadowHelper.getShadowRootElement("cr-icon-button");
        logger.info("Second Shadow Root Element: " + secondShadowLevel.getAttribute("id"));

        WebElement thirdShadowLevel = shadowHelper.getShadowRootElement("#moreActions #icon iron-icon");
        logger.info("Third Shadow Root Element: " + thirdShadowLevel.getTagName());

        WebElement fourthShadowLevel = shadowHelper.getShadowRootElement("#moreActions #icon iron-icon svg");
        logger.info("Fourth Shadow Root Element: " + fourthShadowLevel.getAttribute("style"));

        logger.info("Time elapsed: " + watch.time() + "ms");

        // Assert
        MatcherAssert.assertThat(fourthShadowLevel.getTagName(), equalTo("svg"));
        logger.info(passed, passedMessage);
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
        logger.info("First Shadow Root Element: " + firstShadowLevel.getText());

        WebElement secondShadowLevel = shadowHelper.getShadowRootByJS(firstShadowLevel, "cr-icon-button");
        logger.info("Second Shadow Root Element: " + secondShadowLevel.getAttribute("id"));

        WebElement thirdShadowLevel = shadowHelper.getShadowRootByJS(secondShadowLevel, "#icon iron-icon");
        logger.info("Third Shadow Root Element: " + thirdShadowLevel.getTagName());

        WebElement fourthShadowLevel = shadowHelper.getShadowRootByJS(thirdShadowLevel, "svg");
        logger.info("Fourth Shadow Root Element: " + fourthShadowLevel.getAttribute("style"));

        logger.info("Time elapsed: " + watch.time() + "ms");

        // Assert
        MatcherAssert.assertThat(fourthShadowLevel.getTagName(), equalTo("svg"));
        logger.info(passed, passedMessage);
    }
}