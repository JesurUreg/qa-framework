package com.example.framework.pages;

import com.example.framework.utils.Config;
import com.example.framework.utils.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Config.timeoutSec()));
    }

    protected WebElement $(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected JavascriptExecutor js() {
        return (JavascriptExecutor) driver;
    }

    /** Remove common fixed overlays on DemoQA (ads, consent, fixed banners). */
    protected void removeObstructions() {
        try {
            js().executeScript(
                "for (const sel of ['#fixedban','#adplus-anchor','.fc-consent-root','iframe[title=\"Advertisement\"]','footer']) {" +
                "  const el = document.querySelector(sel); if (el) { try { el.remove(); } catch(e){} } }"
            );
        } catch (Throwable ignored) {}
    }

    protected void scrollIntoView(By locator) {
        try {
            js().executeScript("arguments[0].scrollIntoView({block:'center', inline:'center'});",
                    driver.findElement(locator));
        } catch (Throwable ignored) {}
    }

    protected void click(By locator) {
        $(locator).click();
    }

    /** Robust click: remove overlays, scroll, wait clickable, normal click, then JS fallback. */
    protected void clickSafe(By locator) {
        removeObstructions();
        scrollIntoView(locator);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (ElementClickInterceptedException | TimeoutException e) {
            try {
                js().executeScript("arguments[0].click();", driver.findElement(locator));
            } catch (JavascriptException je) {
                throw e;
            }
        }
    }

    protected void type(By locator, String text) {
        WebElement el = $(locator);
        el.clear();
        el.sendKeys(text);
    }
}
