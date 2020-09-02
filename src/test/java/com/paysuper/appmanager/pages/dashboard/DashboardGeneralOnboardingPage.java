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
        moToElement(driver.findElement(Locators.get("DashboardOnboardingPage.CountryList",Country)));
        driver.findElement(Locators.get("DashboardOnboardingPage.CountryList",Country)).click();
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

    public void clickOnAccountSubmitButton(){
        moToElement(driver.findElement(Locators.get("DashboardOnboardingPage.AccountSubmitButton")));
        driver.findElement(Locators.get("DashboardOnboardingPage.AccountSubmitButton")).click();
    }

    public void clickOn3rdStepContactsButton(){
        moToElement(driver.findElement(Locators.get("DashboardOnboardingPage.ContactsLink")));
        driver.findElement(Locators.get("DashboardOnboardingPage.ContactsLink")).click();
    }


    public void enterTextInNameRepresentativeField(String Name){
        moToElement(driver.findElement(Locators.get("DashboardOnboardingPage.RepresentativeNameField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.RepresentativeNameField")).sendKeys(Name);
    }

    public void enterTextInPositionRepresentativeField(String Position){
        moToElement(driver.findElement(Locators.get("DashboardOnboardingPage.RepresentativePositionField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.RepresentativePositionField")).sendKeys(Position);
    }

    public void enterTextInEmailRepresentativeField(String Email){
        moToElement(driver.findElement(Locators.get("DashboardOnboardingPage.RepresentativeEmailField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.RepresentativeEmailField")).sendKeys(Email);
    }

    public void enterTextInPhoneRepresentativeField(String Phone){
        moToElement(driver.findElement(Locators.get("DashboardOnboardingPage.RepresentativePhoneField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.RepresentativePhoneField")).sendKeys(Phone);
    }

    public void enterTextInNameTechnicalField(String Name){
        moToElement(driver.findElement(Locators.get("DashboardOnboardingPage.TechnicalNameField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.TechnicalNameField")).sendKeys(Name);
    }

    public void enterTextInEmailTechnicalField(String Email){
        moToElement(driver.findElement(Locators.get("DashboardOnboardingPage.TechnicalEmailField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.TechnicalEmailField")).sendKeys(Email);
    }

    public void enterTextInPhoneTechnicalField(String Phone){
        moToElement(driver.findElement(Locators.get("DashboardOnboardingPage.TechnicalPhoneField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.TechnicalPhoneField")).sendKeys(Phone);
    }

    public void clickOnSubmitContactsButton(){
        moToElement(driver.findElement(Locators.get("DashboardOnboardingPage.ContactsSubmitButton")));
        driver.findElement(Locators.get("DashboardOnboardingPage.ContactsSubmitButton")).click();
    }


    public void moToElement(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }
}
