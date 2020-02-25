package com.paysuper.appmanager.pages;

import org.openqa.selenium.WebDriver;

public class DashboardLoginPage extends AbstractPage {

    public DashboardLoginPage(WebDriver driver) {
        super(driver);
        waitForElementLoad("DashboardLoginPage.SignInButton");
    }
}
