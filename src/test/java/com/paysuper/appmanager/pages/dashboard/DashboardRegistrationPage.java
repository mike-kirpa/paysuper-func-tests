package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.WebDriver;

public class DashboardRegistrationPage extends AbstractPage {
    public DashboardRegistrationPage(WebDriver driver) {
        super(driver);
        waitForElementLoad("DashboardLoginPage.SingUpButton");
    }

    public void enterEmail(String email){
        driver.findElement(Locators.get("DashboardLoginPage.LogInEmailField")).sendKeys(email);
    }

    public void enterPassword(String password){
        driver.findElement(Locators.get("DashboardLoginPage.LogInPasswordField")).sendKeys(password);
    }

    public void clickOnCheckBoxUserAgreement(){
        driver.findElement(Locators.get("DashboardLoginPage.CheckBoxRegistration")).click();

    }

    public void clickOnSignUpButton(){
        driver.findElement(Locators.get("DashboardLoginPage.SingUpButton")).click();

    }

    public DashboardPrimaryOnboardingFirstPage successRegistration(String email, String password){
        enterEmail(email);
        enterPassword(password);
        clickOnCheckBoxUserAgreement();
        clickOnSignUpButton();
        return new DashboardPrimaryOnboardingFirstPage(driver);
    }
}
