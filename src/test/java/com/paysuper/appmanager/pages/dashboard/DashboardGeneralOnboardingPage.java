package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.LocalFileDetector;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class DashboardGeneralOnboardingPage extends AbstractPage {

    private final String FileName = "File.JPG";
    private String currentNameOfTheStep;

    public DashboardGeneralOnboardingPage(WebDriver driver) {
        super(driver);
        waitForElementLoad("DashboardOnboardingPage.CompanyInfoText");
    }

    public void enterTextInLegalnameField(String LegalName){
        waitForClickAbleElement(Locators.get("DashboardOnboardingPage.LegalNameField"));
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


    public void enterTextInVatField(String RegistrationNumber){
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.VatField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.VatField")).sendKeys(RegistrationNumber);
    }



    public void enterTextInCountryField(String Country){
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.CountryField")));
        waitForClickAbleElement(Locators.get("DashboardOnboardingPage.CountryField"));
        driver.findElement(Locators.get("DashboardOnboardingPage.CountryField")).sendKeys(Country);
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.CountryList",Country)));
        driver.findElement(Locators.get("DashboardOnboardingPage.CountryList",Country)).click();
    }

    public void enterTextInCityField(String City){
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.CityField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.CityField")).sendKeys(City);
    }

    public void enterTextInZipCodeField(String ZipCode){
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.ZipCodeField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.ZipCodeField")).sendKeys(ZipCode);
    }

    public void enterTextInAddress1Field(String Address1){
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.Address1Field")));
        driver.findElement(Locators.get("DashboardOnboardingPage.Address1Field")).sendKeys(Address1);
    }

    public void clickOnAccountSubmitButton(){
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.AccountSubmitButton")));
        driver.findElement(Locators.get("DashboardOnboardingPage.AccountSubmitButton")).click();
        waitForElementNotPresence(Locators.get("DashboardOnboardingPage.AccountSubmitButton"));
    }

    public void clickOn3rdStepContactsButton(){
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.ContactsLink")));
        driver.findElement(Locators.get("DashboardOnboardingPage.ContactsLink")).click();
        waitForElementLoad(Locators.get("DashboardOnboardingPage.ContactsTitle"));
    }

    public void enterTextInNameRepresentativeField(String Name)  {
        waitForElementLoad(Locators.get("DashboardOnboardingPage.RepresentativeNameField"));
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.RepresentativeNameField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.RepresentativeNameField")).sendKeys(Name);
    }

    public void enterTextInPositionRepresentativeField(String Position){
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.RepresentativePositionField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.RepresentativePositionField")).sendKeys(Position);
    }

    public void enterTextInEmailRepresentativeField(String Email){
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.RepresentativeEmailField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.RepresentativeEmailField")).sendKeys(Email);
    }

    public void enterTextInPhoneRepresentativeField(String Phone){
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.RepresentativePhoneField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.RepresentativePhoneField")).sendKeys(Phone);
    }

    public void enterTextInNameTechnicalField(String Name){
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.TechnicalNameField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.TechnicalNameField")).sendKeys(Name);
    }

    public void enterTextInEmailTechnicalField(String Email){
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.TechnicalEmailField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.TechnicalEmailField")).sendKeys(Email);
    }

    public void enterTextInPhoneTechnicalField(String Phone){
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.TechnicalPhoneField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.TechnicalPhoneField")).sendKeys(Phone);
    }

    public void clickOnSubmitContactsButton(){
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.ContactsSubmitButton")));
        driver.findElement(Locators.get("DashboardOnboardingPage.ContactsSubmitButton")).click();
        waitForElementNotPresence(Locators.get("DashboardOnboardingPage.ContactsSubmitButton"));
    }

    public void clickOn4rdStepBankingInfoButton(){
        waitForElementLoad(Locators.get("DashboardOnboardingPage.BankingInfoLink"));
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.BankingInfoLink")));
        driver.findElement(Locators.get("DashboardOnboardingPage.BankingInfoLink")).click();
    }

    public void enterTextInSwiftField(String Swift){
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.SWIFTField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.SWIFTField")).sendKeys(Swift);
    }

    public void selectAccountCurrency(){
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.AccountCurrencyField")));
        dropDownSelect(Locators.get("DashboardOnboardingPage.AccountCurrencyField"), Locators.get("DashboardOnboardingPage.SelectOptions"), true, 1);
    }

    public void enterTextInIbanField(String Iban){
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.IBANField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.IBANField")).sendKeys(Iban);
    }


    public void enterTextInBankNameField(String BankName){
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.BankNameField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.BankNameField")).sendKeys(BankName);
    }

    public void enterTextInBankAddressField(String BankAddress){
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.BankAddressField")));
        driver.findElement(Locators.get("DashboardOnboardingPage.BankAddressField")).sendKeys(BankAddress);
    }

    public void clickOnSubmitBankingInfoButton(){
        By by = Locators.get("DashboardOnboardingPage.BankingInfoSubmitButton");
        WebElement element = driver.findElement(by);
        moveToElement(element);
        element.click();
        waitForElementNotPresence(element);
    }

    public void clickOn5rdStepPaymentMethodsButton(){
        waitForElementLoad("DashboardOnboardingPage.PaymentMethodsLink");
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.PaymentMethodsLink")));
        driver.findElement(Locators.get("DashboardOnboardingPage.PaymentMethodsLink")).click();
    }


    public void selectTheMainSalesRegion(){
        waitForElementLoad("DashboardOnboardingPage.TheMainSalesRegionSelect");
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.TheMainSalesRegionSelect")));
        dropDownSelect(Locators.get("DashboardOnboardingPage.TheMainSalesRegionSelect"), Locators.get("DashboardOnboardingPage.SelectOptions"), true, 0);
    }

    public void selectRiskLevel(){
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.RiskLevelRadioButtons")));
        selectCheckbox(Locators.get("DashboardOnboardingPage.RiskLevelRadioButtons"), true);
    }

    public void clickOnSubmitApplicationButton() throws InterruptedException {
        Thread.sleep(500);
        By by = Locators.get("DashboardOnboardingPage.SubmitApplicationButtons");
        WebElement element = driver.findElement(by);
        moveToElement(element);
//        waitForClickAbleElement(element);
        element.click();
        waitForElementNotPresence(element);
    }


    public void clickOn6thStepCompanyDocumentsButton(){
        waitForElementLoad("DashboardOnboardingPage.CompanyDocumentsLink");
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.CompanyDocumentsLink")));
        driver.findElement(Locators.get("DashboardOnboardingPage.CompanyDocumentsLink")).click();
    }
    public void sendFilePath(){
        File f = new File("src/test/resources/UploadFiles/" + FileName);
        String path = f.getAbsolutePath();
        waitForElementLoad("DashboardOnboardingPage.FileInputSilentFiled");
        driver.findElement(Locators.get("DashboardOnboardingPage.FileInputSilentFiled")).sendKeys(path);
    }

    public void typeFileTextInField(String FileFieldText){
        waitForElementLoad(Locators.get(String.format("DashboardOnboardingPage.FileTextFiled"), FileName));
        moveToElement(driver.findElement(Locators.get(String.format("DashboardOnboardingPage.FileTextFiled"), FileName)));
        driver.findElement(Locators.get(String.format("DashboardOnboardingPage.FileTextFiled"), FileName)).sendKeys(FileFieldText);
    }

    public void clickOnSubmitDocumentsButton(){
        waitForClickAbleElement(Locators.get("DashboardOnboardingPage.SubmitDocumentsButton"));
        moveToElement(driver.findElement(Locators.get("DashboardOnboardingPage.SubmitDocumentsButton")));
        driver.findElement(Locators.get("DashboardOnboardingPage.SubmitDocumentsButton")).click();
        waitForElementNotPresence(Locators.get("DashboardOnboardingPage.SubmitDocumentsButton"));
    }

    public  boolean isIncompletetStepNotPresense(){
        return !isElementPresent(Locators.get("DashboardOnboardingPage.IncompleteStepStatusText"));
    }

    public String getCurrentNameOfTheStep(){
        return driver.findElement(Locators.get("DashboardOnboardingPage.CurrentNameOfStepText")).getText();
    }


    public void moveToElement(WebElement element){
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }
}
