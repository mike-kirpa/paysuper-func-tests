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
        WebElement element = driver.findElement(Locators.get("PayForm.BankCardNumberField"));
        sendAndCheck(element, pan);
    }

    public void inputBankCardExpired(String date){
        WebElement element = driver.findElement(Locators.get("PayForm.BankCardExpiredField"));
        sendAndCheck(element, date);
    }

    public void inputBankCardCVV(String cvv){
        WebElement element = driver.findElement(Locators.get("PayForm.BankCardCVVField"));
        sendAndCheck(element, cvv);
    }

    public void inputEmail(String email){
        driver.findElement(Locators.get("PayForm.EmailField")).sendKeys(email);
    }

    public void clickPayButton(){
        driver.findElement(Locators.get("PayForm.PayButton")).click();
        waitForElementLoad("PayForm.formLayout");
    }


    public String getFormTitleAfterPay(){
        waitForElementLoad("PayForm.TitleAfterPay");
        return getTextOfElement("PayForm.TitleAfterPay");
    }

    public String getTotalAmount(){
        return driver.findElement(Locators.get("PayForm.TotalAmountFromPAyForm")).getText();
    }

    public String getFormTextAfterPay(){
        return getTextOfElement("PayForm.TextAfterPay");
    }

    public String getFormEmailAfterPay(){
        return getTextOfElement("PayForm.EmailAfterPay");
    }

}
