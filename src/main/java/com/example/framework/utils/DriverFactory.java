package com.example.framework.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    public static void initDriver(String browser, boolean headless) {
        if (DRIVER.get() != null) return;
        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions fopts = new FirefoxOptions();
                if (headless) fopts.addArguments("-headless");
                DRIVER.set(new FirefoxDriver(fopts));
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions copts = new ChromeOptions();
                if (headless) copts.addArguments("--headless=new");
                copts.addArguments("--window-size=1366,768");
                DRIVER.set(new ChromeDriver(copts));
        }
    }

    public static WebDriver getDriver() { return DRIVER.get(); }

    public static void quitDriver() {
        WebDriver d = DRIVER.get();
        if (d != null) { d.quit(); DRIVER.remove(); }
    }
}
