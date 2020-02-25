package com.paysuper.appmanager.pages;

import com.paysuper.appmanager.helpers.Locators;
import org.openqa.selenium.WebDriver;

public class PayFormPage extends AbstractPage{
    public PayFormPage(WebDriver driver){
        super(driver);
        waitForElementLoad("DemoFormPage.formLayout");
    }

    public void selectCountry(){
        if(isElementPresent("DemoFormPage.formWarningArea") & isElementPresent("DemoFormPage.countrySelectField")){
            driver.findElement(Locators.get("DemoFormPage.countrySelectField")).click();
        }
    }

    public void selectLanguage(){
        String lang = driver.findElement(Locators.get("DemoFormPage.languageSelectField")).getText();
        System.out.println(lang);

    }

    public void inputBankCardNumber(String pan){
        driver.findElement(Locators.get("DemoFormPage.BankCardNumberField")).sendKeys(pan);
    }

    public void inputBankCardExpired(String date){
        driver.findElement(Locators.get("DemoFormPage.BankCardExpiredField")).sendKeys(date);
    }

    public void inputBankCardCVV(String cvv){
        driver.findElement(Locators.get("DemoFormPage.BankCardCVVField")).sendKeys(cvv);
    }

    public void inputEmail(String email){
        driver.findElement(Locators.get("DemoFormPage.EmailField")).sendKeys(email);
    }

    public void clickPayButton(){
        driver.findElement(Locators.get("DemoFormPage.PayButton")).click();
    }


    public String getFormTitleAfterPay(){
        waitForElementLoad("DemoFormPage.TitleAfterPay");
        return getTextOfElement("DemoFormPage.TitleAfterPay");
    }

    public String getFormTextAfterPay(){
        return getTextOfElement("DemoFormPage.TextAfterPay");
    }

    public String getFormEmailAfterPay(){
        return getTextOfElement("DemoFormPage.EmailAfterPay");
    }

}
