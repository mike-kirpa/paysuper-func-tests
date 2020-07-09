package com.paysuper.tests.suite;

import com.paysuper.appmanager.pages.analytics.AnalyticsLogin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.paysuper.tests.TestBase;

public class AnalyticsLoginRegistration extends TestBase {

    @Test(enabled = true, description="Analytics Login")
    public void TokenOrderSuccessPayTest() {
        app.driver.get(app.getProperties.value("AnalyticsURL"));
        AnalyticsLogin analyticsLogin = new AnalyticsLogin(app.driver);
        //analyticsLogin.login(app.getProperties.value("ValidEmail"), app.getProperties.value("Password"));
    }
}
