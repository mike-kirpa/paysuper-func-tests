package com.paysuper.appmanager.pages;

import org.openqa.selenium.WebDriver;

public class DemoModalForm extends AbstractPage{
    public DemoModalForm(WebDriver driver){
        super(driver);
        waitForPageLoad("DemoModalForm.projectNametext");
    }
}
