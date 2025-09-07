package com.example.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class PracticeFormPage extends BasePage {
    private final By firstName = By.id("firstName");
    private final By lastName = By.id("lastName");
    private final By email = By.id("userEmail");
    private final By genderMale = By.cssSelector("label[for=\"gender-radio-1\"]");
    private final By userNumber = By.id("userNumber");
    private final By subjectsInput = By.id("subjectsInput");
    private final By hobbiesSports = By.cssSelector("label[for=\"hobbies-checkbox-1\"]");
    private final By address = By.id("currentAddress");
    private final By state = By.id("state");
    private final By stateInput = By.id("react-select-3-input");
    private final By city = By.id("city");
    private final By cityInput = By.id("react-select-4-input");
    private final By submit = By.id("submit");
    private final By resultModal = By.cssSelector(".modal-content");

    public PracticeFormPage open(String baseUrl){
        driver.get(baseUrl + "/automation-practice-form");
        removeObstructions();
        return this;
    }
    public PracticeFormPage setFirstName(String v){ type(firstName, v); return this; }
    public PracticeFormPage setLastName(String v){ type(lastName, v); return this; }
    public PracticeFormPage setEmail(String v){ type(email, v); return this; }
    public PracticeFormPage setGenderMale(){ clickSafe(genderMale); return this; }
    public PracticeFormPage setMobile(String v){ type(userNumber, v); return this; }
    public PracticeFormPage addSubject(String s){
        type(subjectsInput, s);
        driver.findElement(subjectsInput).sendKeys(Keys.ENTER);
        return this;
    }
    public PracticeFormPage toggleSports(){ clickSafe(hobbiesSports); return this; }
    public PracticeFormPage setAddress(String v){ type(address, v); return this; }
    public PracticeFormPage selectStateCity(String st, String ct){
        clickSafe(state); driver.findElement(stateInput).sendKeys(st + Keys.ENTER);
        clickSafe(city);  driver.findElement(cityInput).sendKeys(ct + Keys.ENTER);
        return this;
    }
    public PracticeFormPage submit(){ clickSafe(submit); return this; }
    public boolean isResultVisible(){ return driver.findElement(resultModal).isDisplayed(); }
}
