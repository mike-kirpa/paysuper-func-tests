package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.WebDriver;

public class WebCheck extends AbstractPage {
    public WebCheck(WebDriver driver) {
        super(driver);
        waitForElementLoad("DashboardVerifyEmailPage.Text");
    }
}
