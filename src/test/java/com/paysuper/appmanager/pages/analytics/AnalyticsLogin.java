package com.paysuper.appmanager.pages.analytics;

import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.WebDriver;

public class AnalyticsLogin extends AbstractPage {

    public AnalyticsLogin(WebDriver driver) {
        super(driver);
        waitForElementLoad("AnalyticsLoginPage.LogInButton");
    }
}
