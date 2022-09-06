package com.paysuper.appmanager.pages.payform;

import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class PayFormPage extends AbstractPage {
    public PayFormPage(WebDriver driver, String lang, String country){
        super(driver);
        waitForElementLoad("PayForm.formLayout");
        selectLanguage(lang);
        if(isElementPresent("PayForm.TextChangeCountry")){
            changeCountry(country);
        }
    }


    public void selectLanguage(String lang){
        WebElement hoverElement = driver.findElement(Locators.get("PayForm.languageSelectField"));
        if(!(hoverElement.getText().equals(lang))){
            Actions action = new Actions(driver);
            action.moveToElement(hoverElement).perform();
            driver.findElement(By.xpath("//div[contains(text(),'"+lang+"')]")).click();
            hoverElement.click();
        };
    }

    public void changeCountry(String country){
        driver.findElement(Locators.get("PayForm.FieldChangeCountry")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element = driver.findElement(By.xpath("//div[contains(text(), 'Germany')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         driver.findElement(By.xpath("//div[contains(text(), 'Germany')]")).click();
//        System.out.println("//div[contains(text(), '" + country + "')]");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(Locators.get("PayForm.ButtonChangeCountry")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void inputBankCardNumber(String pan){
        sendAndCheck(driver.findElement(Locators.get("PayForm.BankCardNumberField")), pan);
    }

    public void inputBankCardExpired(String date){
            sendAndCheck(driver.findElement(Locators.get("PayForm.BankCardExpiredField")), date);
    }

    public void inputBankCardCVV(String cvv){
            sendAndCheck(driver.findElement(Locators.get("PayForm.BankCardCVVField")), cvv);
    }

    public void inputEmail(String email){
        driver.findElement(Locators.get("PayForm.EmailField")).sendKeys(email);
    }

    public void inputZipCode(String zip) {
        if(isElementPresent(Locators.get("PayForm.ZipField"))) {
            moveToElement(driver.findElement(Locators.get("PayForm.ZipField")));
            driver.findElement(Locators.get("PayForm.ZipField")).sendKeys(zip);
        }
    }

    public void inputCity(String city) {
        if(isElementPresent(Locators.get("PayForm.CityField"))) {
            moveToElement(driver.findElement(Locators.get("PayForm.CityField")));
            driver.findElement(Locators.get("PayForm.CityField")).sendKeys(city);
        }
    }

    public void inputAddress(String address) {
        if(isElementPresent(Locators.get("PayForm.AddressField"))) {
            moveToElement(driver.findElement(Locators.get("PayForm.AddressField")));
            driver.findElement(Locators.get("PayForm.AddressField")).sendKeys(address);
        }
    }

    public void inputPhone(String phone) {
        if(isElementPresent(Locators.get("PayForm.PhoneField"))) {
            moveToElement(driver.findElement(Locators.get("PayForm.PhoneField")));
            driver.findElement(Locators.get("PayForm.PhoneField")).sendKeys(phone);
        }
    }

    public void inputWorkPhone(String phone) {
        if(isElementPresent(Locators.get("PayForm.WorkPhoneField"))) {
            moveToElement(driver.findElement(Locators.get("PayForm.WorkPhoneField")));
            driver.findElement(Locators.get("PayForm.WorkPhoneField")).sendKeys(phone);
        }
    }

    public void inputHomePhone(String phone) {
        if(isElementPresent(Locators.get("PayForm.HomePhoneField"))) {
            moveToElement(driver.findElement(Locators.get("PayForm.HomePhoneField")));
            driver.findElement(Locators.get("PayForm.HomePhoneField")).sendKeys(phone);
        }
    }

    public void clickPayButton(){
        moveToElement(driver.findElement(Locators.get("PayForm.PayButton")));
        driver.findElement(Locators.get("PayForm.PayButton")).click();
        waitForElementLoad("PayForm.formLayout");
    }


    public String getFormTitleAfterPay(){
        waitForElementLoad("PayForm.TitleAfterPay");
        return getTextOfElement("PayForm.TitleAfterPay");
    }

    public String getTotalAmount(){
        return driver.findElement(Locators.get("PayForm.TotalAmountFromPAyForm")).getAttribute("title");
    }

    public String getFormTextAfterPay(){
        return getTextOfElement("PayForm.TextAfterPay");
    }

    public String getFormEmailAfterPay(){
        return getTextOfElement("PayForm.EmailAfterPay");
    }

    public  void clickRememberMeCheckbox(){
        moveToElement(driver.findElement(Locators.get("PayForm.RememberMeCheckbox")));
        driver.findElement(Locators.get("PayForm.RememberMeCheckbox")).click();
    }
    public String getSavedBankCardNumber(){
        waitForElementLoad("PayForm.SavedBankCardField");
        return  getTextOfElement("PayForm.SavedBankCardField");
    }

    public void clickOnDeleteCardButton(){
        driver.findElement(Locators.get("PayForm.DeleteCardButton")).click();
        try {
            Thread.sleep(5500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
