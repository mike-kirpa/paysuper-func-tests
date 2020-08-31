package com.paysuper.tests;


import com.paysuper.appmanager.pages.dashboard.*;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;


public class SingleTest extends TestBase {

    @Test(enabled = true, description="Dashboard Login")
    public void LoginSuccess() {
        app.driver.get(app.getProperties.value("DashboardUrl"));
        DashboardLoginPage dashboardLoginPage = new DashboardLoginPage(app.driver);
        DashboardMainPage dashboardMainPage = dashboardLoginPage.login(app.getProperties.value("ValidEmail"), app.getProperties.value("Password"));
        dashboardMainPage.clickOnProfileIcon();
        dashboardMainPage.clickOnLogoutInProfileMenu();
    }
    }

