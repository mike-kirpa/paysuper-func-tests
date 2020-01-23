package com.paysuper.appmanager.pages;

import com.paysuper.appmanager.helpers.Locators;
import org.openqa.selenium.WebDriver;

import java.sql.SQLOutput;

public class PayFormPage extends AbstractPage{
    public PayFormPage(WebDriver driver){
        super(driver);
        waitForPageLoad("DemoFormPage.formLayout");
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
}
