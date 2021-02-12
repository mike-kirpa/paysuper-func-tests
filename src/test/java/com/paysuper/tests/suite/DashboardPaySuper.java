package com.paysuper.tests.suite;


import com.paysuper.appmanager.pages.dashboard.DashboardLoginPage;
import com.paysuper.appmanager.pages.dashboard.DashboardMainPage;
import org.testng.annotations.Test;
import com.paysuper.tests.TestBase;

public class DashboardPaySuper extends TestBase {

    @Test(description="Dashboard Login-Logout",
            groups = {"tst", "stg", "prod"})
    public void LoginSuccess() {
        app.driver.get(app.getProperties.value("DashboardUrl"));
        DashboardLoginPage dashboardLoginPage = new DashboardLoginPage(app.driver);
        DashboardMainPage dashboardMainPage = dashboardLoginPage.login(app.getProperties.value("ValidEmail"), app.getProperties.value("Password"));
        dashboardMainPage.clickOnProfileIcon();
        dashboardMainPage.clickOnLogoutInProfileMenu();
    }


}
