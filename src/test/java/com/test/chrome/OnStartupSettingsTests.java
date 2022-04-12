package com.test.chrome;

import com.pages.chrome.chrome.OnStartupSettingsPage;
import com.test.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsIterableContaining.hasItems;

public class OnStartupSettingsTests extends TestBase {
    private static Logger logger = LoggerFactory.getLogger(OnStartupSettingsTests.class);

    @Test
    @DisplayName("Shadow root tests with using Page Object Pattern")
    public void shouldManageShadowRoot() {
        // Arrange
        OnStartupSettingsPage onStartupSettingsPage = new OnStartupSettingsPage(driver);
        driver.get("chrome://settings/onStartup");
        String pageUrlToAdd = "https://sii.pl/";

        // Act
        onStartupSettingsPage.selectStartupOption("Otwórz konkretną stronę lub zestaw stron")
                .addStartupPage(pageUrlToAdd);
        List<String> sut = onStartupSettingsPage.getStartupPages();

        // Assert
        logger.info("Startup page urls: " + sut);
        assertThat(sut, hasItems(pageUrlToAdd));
        logger.info(passed, passedMessage);
    }
}
