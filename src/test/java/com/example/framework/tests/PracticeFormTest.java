package com.example.framework.tests;

import com.example.framework.pages.PracticeFormPage;
import com.example.framework.utils.Config;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PracticeFormTest extends BaseTest {
    @Test
    public void fillPracticeForm_Positive(){
        boolean ok = new PracticeFormPage()
                .open(Config.baseUrl())
                .setFirstName("Nari")
                .setLastName("Man")
                .setEmail("nari@example.com")
                .setGenderMale()
                .setMobile("9991234567")
                .addSubject("Maths")
                .toggleSports()
                .setAddress("Philadelphia, PA")
                .selectStateCity("NCR","Delhi")
                .submit()
                .isResultVisible();

        Assert.assertTrue(ok, "Result modal should be visible");
    }
}
