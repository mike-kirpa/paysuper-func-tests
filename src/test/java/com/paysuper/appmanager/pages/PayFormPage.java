package com.paysuper.appmanager.pages;

import org.openqa.selenium.WebDriver;

public class PayFormPage extends AbstractPage{
    public PayFormPage(WebDriver driver){
        super(driver);
        waitForPageLoad("DemoModalForm.projectNametext");
    }
}
