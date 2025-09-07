package com.example.framework.tests;

import com.example.framework.utils.Config;
import com.example.framework.utils.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void openHomeAndCheckTitle() {
        DriverFactory.getDriver().get(Config.baseUrl());
        String title = DriverFactory.getDriver().getTitle();
        Assert.assertTrue(title != null && title.toUpperCase().contains("DEMOQA"),
            "Expected DEMOQA in page title, got: " + title);
    }
}
