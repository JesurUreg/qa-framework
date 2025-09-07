package com.example.framework.utils;

import org.openqa.selenium.*;

public final class ScreenshotUtil {
    private ScreenshotUtil(){}

    public static String takeBase64(WebDriver driver) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        } catch (Throwable t) {
            return "";
        }
    }
}
