package com.pages;

import com.shadow.driver.ShadowDriver;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ShadowPageBase extends PageBase {
    private static final Logger logger = LoggerFactory.getLogger(ShadowPageBase.class.getName());

    public ShadowPageBase(WebDriver driver) {
        super(new ShadowDriver(driver));
        logger.info("Created ShadowPage");
    }
}
