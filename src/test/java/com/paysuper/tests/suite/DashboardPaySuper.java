package com.paysuper.tests.suite;

import com.paysuper.appmanager.pages.dashboard.DashboardLoginPage;

import com.paysuper.appmanager.pages.dashboard.DashboardMainPage;
import com.paysuper.appmanager.pages.dashboard.DashboardTransactionsPage;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.paysuper.tests.TestBase;

public class DashboardPaySuper extends TestBase {

    @Test(enabled = true, description="Dashboard Login-Logout",
            groups = {"tst", "stg", "prod"})
    public void LoginSuccess() {
        app.driver.get(app.getProperties.value("DashboardUrl"));
        DashboardLoginPage dashboardLoginPage = new DashboardLoginPage(app.driver);
        DashboardMainPage dashboardMainPage = dashboardLoginPage.login(app.getProperties.value("ValidEmail"), app.getProperties.value("Password"));
        dashboardMainPage.clickOnProfileIcon();
        dashboardMainPage.clickOnLogoutInProfileMenu();
    }

    @Test(enabled = true, description="Dashboard Refund From Order List",
            groups = {"tst", "stg", "prod"})
    public void RefundFromOrderListTest() {
        app.driver.get(app.getProperties.value("DashboardUrl"));
        DashboardLoginPage dashboardLoginPage = new DashboardLoginPage(app.driver);
        DashboardMainPage dashboardMainPage = dashboardLoginPage.login(app.getProperties.value("ValidEmail"), app.getProperties.value("Password"));
        DashboardTransactionsPage dashboardTransactionsPage = dashboardMainPage.clickOnTransactionSearchLink();
        dashboardTransactionsPage.clickOnFilterButton();
        dashboardTransactionsPage.clickOnStatusButton();
        dashboardTransactionsPage.clickOnFilterListItem("Processed");
        dashboardTransactionsPage.clickOnFilterButton();
        String lastOrderUrl = dashboardTransactionsPage.clickRefundOnLastTransaction();
        dashboardTransactionsPage.clickOnConfrimRefundButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        app.driver.navigate().refresh();
        dashboardLoginPage.waitForElementLoad("DashboardTransactionsPage.HeaderText");
        Assert.assertFalse(dashboardLoginPage.isElementPresent(By.xpath("//a[@href='" + lastOrderUrl + "']/*/div[contains(@class, 'refund')]")));
    }
}
