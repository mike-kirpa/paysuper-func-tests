package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPrimaryOnboardingThirdPage extends AbstractPage {
    public DashboardPrimaryOnboardingThirdPage(WebDriver driver) {
        super(driver);
        waitForElementLoad("DashboardPrimaryOnboardingThirdPage.StepCounterText");
    }
    public void enterCompanyName(String CompanyName){
        By by = Locators.get("DashboardPrimaryOnboardingThridPage.CompanyNameField");
        WebElement webElement = driver.findElement(by);
        moveToElement(webElement);
        waitForClickAbleElement(webElement);
        webElement.sendKeys(CompanyName);
    }

    public void enterWebsiteName(String WebsiteName){
        By by = Locators.get("DashboardPrimaryOnboardingThridPage.WebsiteField");
        WebElement webElement = driver.findElement(by);
        moveToElement(webElement);
        waitForClickAbleElement(webElement);
        webElement.sendKeys(WebsiteName);
    }

    public void selectAnnualIncome(){
        By by = Locators.get("DashboardPrimaryOnboardingThridPage.AnnualIncomeField");
        WebElement webElement = driver.findElement(by);
        moveToElement(webElement);
        waitForClickAbleElement(webElement);
        dropDownSelect(Locators.get("DashboardPrimaryOnboardingThridPage.AnnualIncomeField"), Locators.get("DashboardPrimaryOnboardingPage.SelectOptions"), false, 0);
    }

    public void selectNumberOfEmployees(){
        By by = Locators.get("DashboardPrimaryOnboardingThridPage.NumberOfEmployeesField");
        WebElement webElement = driver.findElement(by);
        moveToElement(webElement);
        waitForClickAbleElement(webElement);
        dropDownSelect(Locators.get("DashboardPrimaryOnboardingThridPage.NumberOfEmployeesField"), Locators.get("DashboardPrimaryOnboardingPage.SelectOptions"), false, 0);
    }

    public void clickOnTypeOfBusiness(){
        By by = Locators.get("DashboardPrimaryOnboardingThridPage.TypeOfBusinessRadiobutton");
        WebElement webElement = driver.findElement(by);
        moveToElement(webElement);
        waitForClickAbleElement(webElement);
        selectCheckbox(Locators.get("DashboardPrimaryOnboardingThridPage.TypeOfBusinessRadiobutton"), false);
    }

    public void clickOnBusinessModel(){
        By by = Locators.get("DashboardPrimaryOnboardingThridPage.BusinessModelCheckboxes");
        WebElement webElement = driver.findElement(by);
        moveToElement(webElement);
        waitForClickAbleElement(webElement);
        selectCheckbox(Locators.get("DashboardPrimaryOnboardingThridPage.BusinessModelCheckboxes"), false);
    }

    public void clickOnSupportedPlatforms(){
        By by = Locators.get("DashboardPrimaryOnboardingThridPage.SupportedPlatformsCheckboxes");
        WebElement webElement = driver.findElement(by);
        moveToElement(webElement);
        waitForClickAbleElement(webElement);
        selectCheckbox(Locators.get("DashboardPrimaryOnboardingThridPage.SupportedPlatformsCheckboxes"), false);
    }

    public void clickOnDoneButton(){
        By by = Locators.get("DashboardPrimaryOnboardingThridPage.DoneButton");
        WebElement webElement = driver.findElement(by);
        moveToElement(webElement);
        waitForClickAbleElement(webElement);
        webElement.click();
    }



    public DashboardVerifyEmailPage successThirdPagePrimaryOnboarding(String CompanyName, String WebsiteName) throws InterruptedException {
        enterCompanyName(CompanyName);
        enterWebsiteName(WebsiteName);
        selectAnnualIncome();
        selectNumberOfEmployees();
        clickOnTypeOfBusiness();
        clickOnBusinessModel();
        clickOnSupportedPlatforms();
        clickOnDoneButton();
        return new DashboardVerifyEmailPage(driver);
    }
}
