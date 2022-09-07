package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.helpers.GetProperties;
import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPrimaryOnboardingThirdPage extends AbstractPage {
    public DashboardPrimaryOnboardingThirdPage(WebDriver driver) {
        super(driver);
        waitForElementLoad("DashboardPrimaryOnboardingFourthPage.StepCounterText");
    }
    public void enterCompanyName(String CompanyName){
        By by = Locators.get("DashboardPrimaryOnboardingFourthPage.CompanyNameField");
        WebElement webElement = driver.findElement(by);
        moveToElement(webElement);
        waitForClickAbleElement(webElement);
        webElement.sendKeys(CompanyName);
    }

    public void enterBrandName(String CompanyName){
        By by = Locators.get("DashboardPrimaryOnboardingFourthPage.BrandNameField");
        WebElement webElement = driver.findElement(by);
        moveToElement(webElement);
        waitForClickAbleElement(webElement);
        webElement.sendKeys(CompanyName);
    }

    public void enterWebsiteName(String WebsiteName){
        By by = Locators.get("DashboardPrimaryOnboardingFourthPage.WebsiteField");
        WebElement webElement = driver.findElement(by);
        moveToElement(webElement);
        waitForClickAbleElement(webElement);
        webElement.sendKeys(WebsiteName);
    }

    public void selectAnnualIncome(){
        By by = Locators.get("DashboardPrimaryOnboardingFourthPage.AnnualIncomeField");
        WebElement webElement = driver.findElement(by);
        moveToElement(webElement);
        waitForClickAbleElement(webElement);
        dropDownSelect(Locators.get("DashboardPrimaryOnboardingFourthPage.AnnualIncomeField"), Locators.get("DashboardPrimaryOnboardingPage.SelectOptions"), false, 0);
    }

    public void selectNumberOfEmployees(){
        By by = Locators.get("DashboardPrimaryOnboardingFourthPage.NumberOfEmployeesField");
        WebElement webElement = driver.findElement(by);
        moveToElement(webElement);
        waitForClickAbleElement(webElement);
        dropDownSelect(Locators.get("DashboardPrimaryOnboardingFourthPage.NumberOfEmployeesField"), Locators.get("DashboardPrimaryOnboardingPage.SelectOptions"), false, 0);
    }

    public void clickOnTypeOfBusiness(){
        By by = Locators.get("DashboardPrimaryOnboardingFourthPage.TypeOfBusinessRadiobutton");
        WebElement webElement = driver.findElement(by);
        moveToElement(webElement);
        waitForClickAbleElement(webElement);
        selectCheckbox(Locators.get("DashboardPrimaryOnboardingFourthPage.TypeOfBusinessRadiobutton"), false);
    }

    public void clickOnBusinessModel(){
        By by = Locators.get("DashboardPrimaryOnboardingFourthPage.BusinessModelCheckboxes");
        WebElement webElement = driver.findElement(by);
        moveToElement(webElement);
        waitForClickAbleElement(webElement);
        selectCheckbox(Locators.get("DashboardPrimaryOnboardingFourthPage.BusinessModelCheckboxes"), false);
    }

    public void clickOnSupportedPlatforms(){
        By by = Locators.get("DashboardPrimaryOnboardingFourthPage.SupportedPlatformsCheckboxes");
        WebElement webElement = driver.findElement(by);
        moveToElement(webElement);
        waitForClickAbleElement(webElement);
        selectCheckbox(Locators.get("DashboardPrimaryOnboardingFourthPage.SupportedPlatformsCheckboxes"), false);
    }

    public void selectCryptoNetwork(){
        By by = Locators.get("DashboardPrimaryOnboardingFourthPage.CryptoNetworkField");
        WebElement webElement = driver.findElement(by);
        moveToElement(webElement);
        waitForClickAbleElement(webElement);
        dropDownSelect(by, Locators.get("DashboardPrimaryOnboardingFourthPage.CryptoNetworkOption"), false, 0);
    }

    public void selectCountry(){
        By by = Locators.get("DashboardPrimaryOnboardingFourthPage.CountryField");
        WebElement webElement = driver.findElement(by);
        moveToElement(webElement);
        waitForClickAbleElement(webElement);
        dropDownSelect(Locators.get("DashboardPrimaryOnboardingFourthPage.CountryField"), By.className("option"), false, 0);
    }

    public void enterSmartContractAddress(String SmartContractAddress){
        By by = Locators.get("DashboardPrimaryOnboardingFourthPage.SmartContractAddressField");
        WebElement webElement = driver.findElement(by);
        moveToElement(webElement);
        waitForClickAbleElement(webElement);
        webElement.sendKeys(SmartContractAddress);
    }

    public DashboardVerifyEmailPage successThirdPagePrimaryOnboarding(String CompanyName, String WebsiteName) throws InterruptedException {
        enterCompanyName(CompanyName);
        enterWebsiteName(WebsiteName);
        selectAnnualIncome();
        selectNumberOfEmployees();
        clickOnTypeOfBusiness();
        clickOnBusinessModel();
        clickOnSupportedPlatforms();
        clickOnNextButton();
        return new DashboardVerifyEmailPage(driver);
    }

    public DashboardVerifyEmailPage successDAOThirdPagePrimaryOnboarding(String CompanyName, String WebsiteName) throws InterruptedException {
        enterBrandName(CompanyName);
        enterWebsiteName(WebsiteName);
        selectCountry();
        selectCryptoNetwork();
        enterSmartContractAddress(GetProperties.value("ETHSmartContractAddress"));
        clickOnNextButton();
        return new DashboardVerifyEmailPage(driver);
    }
}
