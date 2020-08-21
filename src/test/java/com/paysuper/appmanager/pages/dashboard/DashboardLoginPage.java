package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.WebDriver;

public class DashboardLoginPage extends AbstractPage {

    public DashboardLoginPage(WebDriver driver) {
        super(driver);
        waitForElementLoad("DashboardLoginPage.SignInButton");
    }

    public void clickOnSignInButton(){
        driver.findElement(Locators.get("DashboardLoginPage.SignInButton")).click();
        waitForElementLoad("DashboardLoginPage.Iframe");
        driver.switchTo().frame(driver.findElement(Locators.get("DashboardLoginPage.Iframe")));
        waitForElementLoad("DashboardLoginPage.LogInButton");
    }

    public DashboardRegistrationPage clickOnSignUpButton(){
        driver.findElement(Locators.get("DashboardLoginPage.SingUpButton")).click();
        return new DashboardRegistrationPage(driver);
    }

    public void clickOnLogInButton(){
        driver.findElement(Locators.get("DashboardLoginPage.LogInButton")).click();
    }

    public void enterEmail(String email){
        driver.findElement(Locators.get("DashboardLoginPage.LogInEmailField")).sendKeys(email);
    }
    public void enterPassword(String password){
        driver.findElement(Locators.get("DashboardLoginPage.LogInPasswordField")).sendKeys(password);
    }

    public DashboardMainPage login(String email, String password){
        clickOnSignInButton();
        enterEmail(email);
        enterPassword(password);
        clickOnLogInButton();
        driver.switchTo().defaultContent();
        return new DashboardMainPage(driver);
    }


}
