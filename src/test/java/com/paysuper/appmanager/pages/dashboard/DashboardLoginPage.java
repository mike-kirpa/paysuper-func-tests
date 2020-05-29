package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
