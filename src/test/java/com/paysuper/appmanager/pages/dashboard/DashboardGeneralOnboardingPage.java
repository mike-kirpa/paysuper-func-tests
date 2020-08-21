package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DashboardGeneralOnboardingPage extends AbstractPage {
    public DashboardGeneralOnboardingPage(WebDriver driver) {
        super(driver);
        waitForElementLoad("DashboardOnboardingPage.CompanyInfoText");
    }

    public void enterTextInLegalnameField(String LegalName){
        driver.findElement(Locators.get("DashboardOnboardingPage.LegalNameField")).sendKeys(LegalName);
    }


    public void enterTextInWebsiteFiled(String Website){
        if(driver.findElement(Locators.get("DashboardOnboardingPage.WebsiteField")).getAttribute("value").isEmpty()) {
            driver.findElement(Locators.get("DashboardOnboardingPage.WebsiteField")).sendKeys(Website);
        }
    }

    public void enterTextInOperatingName(String OperatingName){
        if(driver.findElement(Locators.get("DashboardOnboardingPage.OperatingNameField")).getAttribute("value").isEmpty()) {
            driver.findElement(Locators.get("DashboardOnboardingPage.OperatingNameField")).sendKeys(OperatingName);
        }
    }

    public void enterTextInRegistrationNumberField(String RegistrationNumber){
        driver.findElement(Locators.get("DashboardOnboardingPage.RegistrationNumberField")).sendKeys(RegistrationNumber);
    }

    public void enterTextInCountryField(String Country) throws InterruptedException {
        moToElement(driver.findElement(Locators.get("DashboardOnboardingPage.CountryField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.CountryField")).sendKeys(Country);
    }

    public void enterTextInCityField(String City){
        moToElement(driver.findElement(Locators.get("DashboardOnboardingPage.CityField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.CityField")).sendKeys(City);
    }

    public void enterTextInZipCodeField(String ZipCode){
        moToElement(driver.findElement(Locators.get("DashboardOnboardingPage.ZipCodeField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.ZipCodeField")).sendKeys(ZipCode);
    }

    public void enterTextInAddress1Field(String Address1){
        moToElement(driver.findElement(Locators.get("DashboardOnboardingPage.Address1Field")));
        driver.findElement(Locators.get("DashboardOnboardingPage.Address1Field")).sendKeys(Address1);
    }

    public void moToElement(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }
}
