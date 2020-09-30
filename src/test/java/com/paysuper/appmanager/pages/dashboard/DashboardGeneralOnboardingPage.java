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

    public void clickOn4rdStepBankingInfoButton(){
        moToElement(driver.findElement(Locators.get("DashboardOnboardingPage.BankingInfoLink")));
        driver.findElement(Locators.get("DashboardOnboardingPage.BankingInfoLink")).click();
    }

    public void enterTextInSwiftField(String Swift){
        moToElement(driver.findElement(Locators.get("DashboardOnboardingPage.SWIFTField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.SWIFTField")).sendKeys(Swift);
    }

    public void selectAccountCurrency(){
        moToElement(driver.findElement(Locators.get("DashboardOnboardingPage.AccountCurrencyField")));
        dropDownSelect(Locators.get("DashboardOnboardingPage.AccountCurrencyField"), Locators.get("DashboardOnboardingPage.SelectOptions"), true);
    }

    public void enterTextInIbanField(String Iban){
        moToElement(driver.findElement(Locators.get("DashboardOnboardingPage.IBANField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.IBANField")).sendKeys(Iban);
    }


    public void enterTextInBankNameField(String BankName){
        moToElement(driver.findElement(Locators.get("DashboardOnboardingPage.BankNameField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.BankNameField")).sendKeys(BankName);
    }

    public void enterTextInBankAddressField(String BankAddress){
        moToElement(driver.findElement(Locators.get("DashboardOnboardingPage.BankAddressField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.BankAddressField")).sendKeys(BankAddress);
    }

    public void clickOnSubmitBankingInfoButton(){
        moToElement(driver.findElement(Locators.get("DashboardOnboardingPage.BankingInfoSubmitButton")));
        driver.findElement(Locators.get("DashboardOnboardingPage.BankingInfoSubmitButton")).click();
    }

    public void clickOn5rdStepPaymentMethodsButton(){
        waitForElementLoad("DashboardOnboardingPage.PaymentMethodsLink");
        moToElement(driver.findElement(Locators.get("DashboardOnboardingPage.PaymentMethodsLink")));
        driver.findElement(Locators.get("DashboardOnboardingPage.PaymentMethodsLink")).click();
    }


    public void selectTheMainSalesRegion(){
        waitForElementLoad("DashboardOnboardingPage.TheMainSalesRegionSelect");
        moToElement(driver.findElement(Locators.get("DashboardOnboardingPage.TheMainSalesRegionSelect")));
        dropDownSelect(Locators.get("DashboardOnboardingPage.TheMainSalesRegionSelect"), Locators.get("DashboardOnboardingPage.SelectOptions"), true);
    }

    public void selectRiskLevel(){
        moToElement(driver.findElement(Locators.get("DashboardOnboardingPage.RiskLevelRadioButtons")));
        selectCheckbox(Locators.get("DashboardOnboardingPage.RiskLevelRadioButtons"), true);
    }

    public void clickOnSubmitApplicationButton(){
        moToElement(driver.findElement(Locators.get("DashboardOnboardingPage.SubmitApplicationButtons")));
        driver.findElement(Locators.get("DashboardOnboardingPage.SubmitApplicationButtons")).click();
    }


    public void moToElement(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }
}
