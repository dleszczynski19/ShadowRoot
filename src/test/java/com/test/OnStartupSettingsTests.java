package com.test;

import com.configuration.TestBase;
import com.pages.google.chrome.OnStartupSettingsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsIterableContaining.hasItems;

public class OnStartupSettingsTests extends TestBase {
    private static Logger log = LoggerFactory.getLogger(ShadowRootExtendedTest.class);

    @Test
    @DisplayName("Shadow root tests with using Page Object Pattern")
    public void shouldManageShadowRoot() {
        // Arrange
        OnStartupSettingsPage onStartupSettingsPage = new OnStartupSettingsPage(driver);
        driver.get("chrome://settings/onStartup");

        // Act
        onStartupSettingsPage.selectStartupOption("Otwórz konkretną stronę lub zestaw stron")
                .addStartupPage("https://sii.pl/");
        List<String> sut = onStartupSettingsPage.getStartupPages();

        // Assert
        log.info("Startup page urls: " + sut);
        assertThat(sut, hasItems("https://sii.pl/"));
    }
}
