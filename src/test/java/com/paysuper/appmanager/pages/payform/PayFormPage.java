package com.paysuper.appmanager.pages.payform;

import com.paysuper.appmanager.helpers.GetProperties;
import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PayFormPage extends AbstractPage {
    public PayFormPage(WebDriver driver, String lang){
        super(driver);
        waitForElementLoad("DemoFormPage.formLayout");
        selectLanguage(lang);
    }

    public void selectCountry(){
        if(isElementPresent("DemoFormPage.formWarningArea") & isElementPresent("DemoFormPage.countrySelectField")){
            driver.findElement(Locators.get("DemoFormPage.countrySelectField")).click();
        }
    }

    public void selectLanguage(String lang){
        WebElement hoverElement = driver.findElement(Locators.get("DemoFormPage.languageSelectField"));
        if(hoverElement.getText().equals(lang)){
            Actions action = new Actions(driver);
            action.moveToElement(hoverElement).perform();
            driver.findElement(By.xpath("//div[contains(text(),'"+lang+"')]")).click();
            hoverElement.click();
        };
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
