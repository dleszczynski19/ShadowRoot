package com.shadow.driver;

import com.exceptions.UnsupportedShadowSelector;
import io.github.sukgu.Shadow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

public class ShadowDriver implements WebDriver {
    private final WebDriver driver;
    private final Shadow shadow;

    public ShadowDriver(WebDriver driver) {
        this.driver = driver;
        this.shadow = new Shadow(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    @Override
    public void get(String url) {
        driver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        String selector = getSelector(by);

        try {
            printFields(by);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (by instanceof By.ByCssSelector) {
            return shadow.findElements(selector);
        } else if (by instanceof By.ByName) {
            return shadow.findElements("[name=" + selector + "\"]");
        } else if (by instanceof By.ByXPath) {
            return shadow.findElementsByXPath(selector);
        } else if (by instanceof By.ById) {
            return shadow.findElements("#" + selector);
        } else if (by instanceof By.ByClassName) {
            return shadow.findElements("[class=" + selector + "]");
        }
        throw new UnsupportedShadowSelector("Selector: " + selector + " is not supported yet.");
    }

    @Override
    public WebElement findElement(By by) {
        String selector = getSelector(by);
        try {
            printFields(by);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (by instanceof By.ByCssSelector) {
            return shadow.findElement(selector);
        } else if (by instanceof By.ByName) {
            return shadow.findElement("[name=" + selector + "\"]");
        } else if (by instanceof By.ByXPath) {
            return shadow.findElementByXPath(selector);
        } else if (by instanceof By.ById) {
            return shadow.findElement("#" + selector);
        } else if (by instanceof By.ByClassName) {
            return shadow.findElement("[class=" + selector + "]");
        }
        throw new UnsupportedShadowSelector("Selector: " + selector + " is not supported yet.");
    }

    private void printFields(By by) throws IllegalAccessException {

        if (by instanceof By.ByCssSelector) {
            for (Field field : ((By.ByCssSelector) by).getClass().getDeclaredFields()) {
                field.setAccessible(true);
                String name = field.getName();
                Object value = field.get(by);
                System.out.printf("%s: %s%n", name, value);
            }
        } else if (by instanceof By.ByName) {
            for (Field field : ((By.ByName) by).getClass().getDeclaredFields()) {
                field.setAccessible(true);
                String name = field.getName();
                Object value = field.get(by);
                System.out.printf("%s: %s%n", name, value);
            }
        } else if (by instanceof By.ByXPath) {
            for (Field field : ((By.ByXPath) by).getClass().getDeclaredFields()) {
                field.setAccessible(true);
                String name = field.getName();
                Object value = field.get(by);
                System.out.printf("%s: %s%n", name, value);
            }
        } else if (by instanceof By.ById) {
            for (Field field : ((By.ById) by).getClass().getDeclaredFields()) {
                field.setAccessible(true);
                String name = field.getName();
                Object value = field.get(by);
                System.out.printf("%s: %s%n", name, value);
            }
        } else if (by instanceof By.ByClassName) {
            for (Field field : ((By.ByClassName) by).getClass().getDeclaredFields()) {
                field.setAccessible(true);
                String name = field.getName();
                Object value = field.get(by);
                System.out.printf("%s: %s%n", name, value);
            }
        }
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    public void close() {
        driver.close();
    }

    @Override
    public void quit() {
        driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return driver.navigate();
    }

    @Override
    public Options manage() {
        return driver.manage();
    }

    public String getSelector(By by) {
        return by.toString().replaceAll("By.*: ", "");
    }
}