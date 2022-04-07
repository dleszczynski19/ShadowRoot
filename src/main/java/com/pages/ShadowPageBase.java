package com.pages;

import io.github.sukgu.Shadow;
import org.openqa.selenium.WebDriver;

public abstract class ShadowPageBase extends PageBase{
    protected Shadow shadow;

    public ShadowPageBase(WebDriver driver) {
        super(driver);
        shadow = new Shadow(driver);
    }
}
