package com.pages;

import com.shadow.driver.ShadowDriver;
import io.github.sukgu.Shadow;
import org.openqa.selenium.WebDriver;

public abstract class ShadowPageBase extends PageBase{
    protected ShadowDriver shadow;

    public ShadowPageBase(WebDriver driver) {
        super(driver);
        shadow = new ShadowDriver(driver);
    }
}
