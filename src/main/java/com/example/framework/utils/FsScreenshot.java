package com.example.framework.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FsScreenshot {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

    public static Path save(WebDriver driver, String testName) {
        try {
            Files.createDirectories(Path.of("target","screenshots"));
            File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            Path dst = Path.of("target","screenshots", testName + "_" + LocalDateTime.now().format(FMT) + ".png");
            Files.copy(src.toPath(), dst);
            return dst;
        } catch (Exception e) { return null; }
    }
}
