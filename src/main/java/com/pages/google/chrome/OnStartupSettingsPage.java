package com.pages.google.chrome;

import com.pages.ShadowPageBase;
import io.github.sukgu.support.FindElementBy;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class OnStartupSettingsPage extends ShadowPageBase {

    @FindElementBy(css = "#searchInput")
    private WebElement searchField;

    @FindElementBy(css = "#onStartupRadioGroup #label")
    private List<WebElement> startupOptions;

    @FindElementBy(css = "#addPage")
    private WebElement addNewPageOption;

    @FindElementBy(css = "#addPage")
    private WebElement useCurrentPagesOption;

    @FindElementBy(css = "#dialog #input")
    private WebElement dialogInputField;

    @FindElementBy(css = "#dialog #actionButton")
    private WebElement confirmDialogButton;

    @FindElementBy(css = "#dialog #cancel-button")
    private WebElement cancelDialogButton;

    @FindElementBy(css = "settings-startup-url-entry .text-elide.secondary")
    private List<WebElement> startupPagesUrls;

    public OnStartupSettingsPage(WebDriver driver) {
        super(driver);
    }

    public OnStartupSettingsPage selectStartupOption(String optionText) {
        startupOptions.stream()
                .filter(o -> Objects.equals(o.getText(), optionText))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Not found startup option with labeled as: " + optionText))
                .click();
        return this;
    }

    public void addStartupPage(String page) {
        addNewPageOption.click();
        dialogInputField.sendKeys(page);
        confirmDialogButton.click();
    }

    public List<String> getStartupPages() {
        return startupPagesUrls.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
