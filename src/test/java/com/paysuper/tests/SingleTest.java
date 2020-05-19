package com.paysuper.tests;

import com.paysuper.appmanager.pages.analytics.AnalyticsLogin;
import com.paysuper.appmanager.pages.dashboard.DashboardLoginPage;
import org.testng.annotations.Test;


public class SingleTest extends TestBase {

    @Test(enabled = true, description="Dashboard Login")
    public void LoginSuccess() {
        app.driver.get(app.getProperties.value("DashboardUrl"));
        DashboardLoginPage dashboardLoginPage = new DashboardLoginPage(app.driver);
        dashboardLoginPage.login(app.getProperties.value("ValidEmail"), app.getProperties.value("Password"));
    }
}
