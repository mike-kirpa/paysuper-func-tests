package com.paysuper.tests;



import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.helpers.MailParser;
import com.paysuper.appmanager.models.Email;
import com.paysuper.appmanager.models.Order;
import com.paysuper.appmanager.pages.dashboard.*;
import com.paysuper.appmanager.pages.payform.PayFormPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SingleTest extends TestBase {
    @Test(enabled = true, description="Dashboard Login-Logout",
            groups = {"tst", "stg", "prod"})
    public void LoginSuccess() {
        app.driver.get(app.getProperties.value("DashboardUrl"));
        DashboardLoginPage dashboardLoginPage = new DashboardLoginPage(app.driver);
        DashboardMainPage dashboardMainPage = dashboardLoginPage.login(app.getProperties.value("ValidEmail"), app.getProperties.value("Password"));
        dashboardMainPage.clickOnProfileIcon();
        dashboardMainPage.clickOnLogoutInProfileMenu();
    }

    }

