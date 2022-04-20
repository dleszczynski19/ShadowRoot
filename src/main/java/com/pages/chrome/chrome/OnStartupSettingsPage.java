package com.pages.chrome.chrome;

import com.pages.ShadowPageBase;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class OnStartupSettingsPage extends ShadowPageBase {
    
    @FindBy(css = "#onStartupRadioGroup #label")
    private List<WebElement> startupOptions;

    @FindBy(css = "#addPage a")
    private WebElement addNewPageOption;

    @FindBy(css = "#dialog #input")
    private WebElement dialogInputField;

    @FindBy(css = "#dialog #actionButton")
    private WebElement confirmDialogButton;

    @FindBy(css = "settings-startup-url-entry .text-elide.secondary")
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
