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
    @Test(enabled = true, description="Dashboard Refund From Order List",
            groups = {"tst", "stg", "prod"})
    public void RefundFromOrderListTest() throws InterruptedException {
        Email email = new Email();
        Order order = new Order();
        app.driver.get(app.getProperties.value("DashboardUrl"));
        DashboardLoginPage dashboardLoginPage = new DashboardLoginPage(app.driver);
        DashboardMainPage dashboardMainPage = dashboardLoginPage.login(app.getProperties.value("ValidEmail"), app.getProperties.value("Password"));
        DashboardTransactionsPage dashboardTransactionsPage = dashboardMainPage.clickOnTransactionSearchLink();
        dashboardTransactionsPage.clickOnFilterButton();
        dashboardTransactionsPage.clickOnStatusButton();
        dashboardTransactionsPage.clickOnFilterListItem("Processed");
        dashboardTransactionsPage.clickOnFilterButton();
        String lastOrderUrl = dashboardTransactionsPage.clickRefundOnLastTransaction();
        System.out.println(lastOrderUrl);
        dashboardTransactionsPage.clickOnConfrimRefundButton();
        Thread.sleep(2000);
//        app.driver.navigate().refresh();
//        dashboardLoginPage.waitForElementLoad("DashboardTransactionsPage.HeaderText");
        Assert.assertFalse(dashboardLoginPage.isElementPresent(By.xpath("//a[@href='" + lastOrderUrl + "']/*/div[contains(@class, 'refund')]")));

        OrderPage OrderPage = dashboardTransactionsPage.openOrderPageByLink(app.getProperties.value("DashboardUrl") + lastOrderUrl);
        email.setEmailRecipient(OrderPage.getEmail());
        order.setGrossRevenue(OrderPage.getTotalChargeSum());
        order.setCountry(OrderPage.getBillingAddress());
        order.setUUID(OrderPage.getOrderUid());

        dashboardTransactionsPage = dashboardMainPage.clickOnTransactionSearchLink();
        dashboardTransactionsPage.clickOnFilterButton();
        dashboardTransactionsPage.clickOnStatusButton();
        dashboardTransactionsPage.clickOnFilterListItem("Refunded");
        dashboardTransactionsPage.clickOnFilterButton();
        OrderPage refundOrderPage = dashboardTransactionsPage.clickOnFirstOrderIntable();
        refundOrderPage.getRefundOrderUid(order);

        MailParser mailParser = new MailParser(app.getProperties.value("user_login_for_email"),
                System.getenv("autotest_email_pass"),
                email);
        mailParser.parseRefundedCheck();
        org.testng.Assert.assertEquals(email.getTransactionID(), order.getUUID());
        org.testng.Assert.assertEquals(email.getTotal(), order.getGrossRevenue());
        org.testng.Assert.assertEquals(email.getTransactionDate(), order.getToday());
        org.testng.Assert.assertEquals(email.getMerchantName(), app.getProperties.value("MerchantName"));
        if(order.getCountry().contains("Ukraine") | order.getCountry().contains("Russia ")){
            org.testng.Assert.assertEquals(email.getPaymentPartner(), app.getProperties.value("OperCompanyNameMalta"));
        }
        else
            org.testng.Assert.assertEquals(email.getPaymentPartner(), app.getProperties.value("OperCompanyNAmeCyprus"));

    }
    }

