package com.paysuper.tests.suite;

import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.helpers.MailParser;
import com.paysuper.appmanager.helpers.MockApi;
import com.paysuper.appmanager.models.Email;
import com.paysuper.appmanager.models.Order;
import com.paysuper.appmanager.models.Webhook;
import com.paysuper.appmanager.pages.dashboard.DashboardLoginPage;
import com.paysuper.appmanager.pages.dashboard.DashboardMainPage;
import com.paysuper.appmanager.pages.dashboard.DashboardTransactionsPage;
import com.paysuper.appmanager.pages.dashboard.OrderPage;
import com.paysuper.appmanager.pages.payform.PayFormPage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.paysuper.tests.TestBase;

public class OrderPaymentTest extends TestBase {

    @Test(description="success simple payment",
            groups = {"tst", "stg", "prod", "pay"})
    public void SimpleOrderSuccessPayTest() throws InterruptedException {
        Webhook actualWebhook = new Webhook("null");
        Webhook expectedWebhook = new Webhook("order.processed");
        MockApi mockApi = new MockApi();
        Email email = new Email();
        Order order = new Order();
        String payment_form_url;
        String unixTime;


        order.setOrderCurrency(app.getProperties.value("OrderCurrency"));
        order.setOrderAmount(app.getProperties.value("OrderAmount"));
        order.setProjectId(app.getProperties.value("ProjectWebhooksId"));
        email.setSubject(app.getProperties.value("EmailPurchaseCheckSubject"));

        unixTime = String.valueOf(System.currentTimeMillis() / 1000L);
        email.setEmailRecipient("autotest.protocolone+" + unixTime + "@gmail.com");

        payment_form_url = app.restAPI.createSimpleOrder(
                app.getProperties.value("ApiUrlCheckout"),
                order);
        app.driver.get(payment_form_url);
        PayFormPage payFormPage =new PayFormPage(app.driver,
                app.getProperties.value("DefaultLanguage"),
                app.getProperties.value("DefautCountry"));
        Assert.assertEquals(app.driver.findElement(Locators.get("PayForm.OrderSummaryValue")).getText(), "€" + order.getOrderAmount());
        payFormPage.inputBankCardNumber(app.getProperties.value("ValidNo3DSBankCard"));
        payFormPage.inputBankCardExpired(app.getProperties.value("ValidExpiredDate"));
        payFormPage.inputBankCardCVV(app.getProperties.value("ValidCVV"));
        payFormPage.inputEmail(email.getEmailRecipient());
        order.setTotalAmount(payFormPage.getTotalAmount());
        app.restAPI.getOrderForPayForm(app.getProperties.value("ApiUrlCheckout"),
                order);
        payFormPage.clickPayButton();
        Assert.assertEquals(payFormPage.getFormTitleAfterPay(), app.getProperties.value("EnSuccessPayTitle"));
        Assert.assertEquals(payFormPage.getFormTextAfterPay(), app.getProperties.value("EnSuccessSimplePayText"));
        Assert.assertEquals(payFormPage.getFormEmailAfterPay(), email.getEmailRecipient());
        MailParser mailParser = new MailParser(app.getProperties.value("user_login_for_email"),
                System.getenv("autotest_email_pass"),
                email);
        mailParser.parsePurchaseCheck();
        Assert.assertEquals(email.getTransactionID(), order.getUUID());
        Assert.assertEquals(email.getTotal(), order.getTotalAmount());
        Assert.assertEquals(email.getTransactionDate(), order.getToday());
        Assert.assertEquals(email.getMerchantName(), app.getProperties.value("MerchantName"));
        Assert.assertEquals(email.getPaymentPartner(), app.getProperties.value("OperCompanyNAmeCyprus"));

        //Webhook assert

        expectedWebhook.setObject_id(order.getUUID());
        mockApi.checkAndCleatEvent(actualWebhook);
        Assert.assertEquals(expectedWebhook, actualWebhook, "Actual webhook's data not equal:");
    }

    @Test(description="success product payment",
            groups = {"tst", "stg", "prod", "pay"})
    public void ProductOrderSuccessPayTest() {
        Email email = new Email();
        Order order = new Order();
        String payment_form_url;
        String unixTime;

        order.setProjectId(app.getProperties.value("ProjectId"));
        order.setProduct(app.getProperties.value("Product"));
        email.setSubject(app.getProperties.value("EmailPurchaseCheckSubject"));


        unixTime = String.valueOf(System.currentTimeMillis() / 1000L);
        email.setEmailRecipient("autotest.protocolone+" + unixTime + "@gmail.com");

        payment_form_url = app.restAPI.createProductOrder(
                app.getProperties.value("ApiUrlCheckout"),
                "product",
                order);
        app.driver.get(payment_form_url);
        PayFormPage payFormPage =new PayFormPage(app.driver,
                app.getProperties.value("DefaultLanguage"),
                app.getProperties.value("DefautCountry"));
        payFormPage.inputBankCardNumber(app.getProperties.value("ValidNo3DSBankCard"));
        payFormPage.inputBankCardExpired(app.getProperties.value("ValidExpiredDate"));
        payFormPage.inputBankCardCVV(app.getProperties.value("ValidCVV"));
        payFormPage.inputEmail(email.getEmailRecipient());
        payFormPage.clickPayButton();
        Assert.assertEquals(payFormPage.getFormTitleAfterPay(), app.getProperties.value("EnSuccessPayTitle"));
        Assert.assertEquals(payFormPage.getFormTextAfterPay(), app.getProperties.value("EnSuccessProductPayText"));
        Assert.assertEquals(payFormPage.getFormEmailAfterPay(), email.getEmailRecipient());
    }

    @Test(description="success token payment",
            groups = {"tst", "stg", "prod", "pay"})
    public void TokenOrderSuccessPayTest() {
        Email email = new Email();
        Order order = new Order();
        String payment_form_url;
        String unixTime;

        order.setProjectId(app.getProperties.value("ProjectId"));
        order.setSecret(app.getProperties.value("Secret"));
        email.setSubject(app.getProperties.value("EmailPurchaseCheckSubject"));

        unixTime = String.valueOf(System.currentTimeMillis() / 1000L);
        email.setEmailRecipient("autotest.protocolone+" + unixTime + "@gmail.com");

        payment_form_url = app.restAPI.createTokenOrder(
                app.getProperties.value("ApiUrl"),
                order,
                email);
        app.driver.get(payment_form_url);
        PayFormPage payFormPage =new PayFormPage(app.driver,
                app.getProperties.value("DefaultLanguage"),
                app.getProperties.value("DefautCountry"));
        payFormPage.inputBankCardNumber(app.getProperties.value("ValidNo3DSBankCard"));
        payFormPage.inputBankCardExpired(app.getProperties.value("ValidExpiredDate"));
        payFormPage.inputBankCardCVV(app.getProperties.value("ValidCVV"));
        payFormPage.clickPayButton();
        Assert.assertEquals(payFormPage.getFormTitleAfterPay(), app.getProperties.value("EnSuccessPayTitle"));
        Assert.assertEquals(payFormPage.getFormTextAfterPay(), app.getProperties.value("EnSuccessSimplePayText"));
        Assert.assertEquals(payFormPage.getFormEmailAfterPay(), email.getEmailRecipient());
    }

    @Test(groups = {"tst", "stg", "prod", "pay"})
    public void ProductKeyOrderSuccessPayTest()  {
        Email email = new Email();
        Order order = new Order();
        String payment_form_url;
        String unixTime;

        order.setProjectId(app.getProperties.value("ProjectId"));
        order.setProduct(app.getProperties.value("ProductKey"));
        email.setSubject(app.getProperties.value("EmailPurchaseCheckSubject"));


        unixTime = String.valueOf(System.currentTimeMillis() / 1000L);
        email.setEmailRecipient("autotest.protocolone+" + unixTime + "@gmail.com");

        payment_form_url = app.restAPI.createProductOrder(
                app.getProperties.value("ApiUrlCheckout"),
                "key",
                order);
        app.driver.get(payment_form_url);
        PayFormPage payFormPage =new PayFormPage(app.driver,
                app.getProperties.value("DefaultLanguage"),
                app.getProperties.value("DefautCountry"));
        payFormPage.inputBankCardNumber(app.getProperties.value("ValidNo3DSBankCard"));
        payFormPage.inputBankCardExpired(app.getProperties.value("ValidExpiredDate"));
        payFormPage.inputBankCardCVV(app.getProperties.value("ValidCVV"));
        payFormPage.inputEmail(email.getEmailRecipient());
        payFormPage.clickPayButton();
        Assert.assertEquals(payFormPage.getFormTitleAfterPay(), app.getProperties.value("EnSuccessPayTitle"));
        Assert.assertEquals(payFormPage.getFormTextAfterPay(), app.getProperties.value("EnSuccessProductKeyPayText"));
        Assert.assertEquals(payFormPage.getFormEmailAfterPay(), email.getEmailRecipient());
    }

    @Test(description="Dashboard Refund From Order List",
            groups = {"tst", "stg", "prod"})
    public void RefundFromOrderListTest() throws InterruptedException {
        Email email = new Email();
        Order order = new Order();

        email.setSubject(app.getProperties.value("EmailRefundSubject"));

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
        app.driver.navigate().refresh();
        dashboardLoginPage.waitForElementLoad("DashboardTransactionsPage.HeaderText");
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
        org.testng.Assert.assertEquals(email.getPaymentPartner(), app.getProperties.value("OperCompanyNAmeCyprus"));

    }

    @Test(description="Decline 3DS simple payment",
            groups = {"tst", "stg", "prod", "pay"})
    public void SimpleOrder3DSDeclineTest() throws InterruptedException {
        Webhook actualWebhook = new Webhook("null");
        Webhook expectedWebhook = new Webhook("payment.cancel");
        MockApi mockApi = new MockApi();
        Email email = new Email();
        Order order = new Order();
        String payment_form_url;
        String unixTime;


        order.setOrderCurrency(app.getProperties.value("OrderCurrency"));
        order.setOrderAmount(app.getProperties.value("OrderAmount"));
        order.setProjectId(app.getProperties.value("ProjectWebhooksId"));
        email.setSubject(app.getProperties.value("EmailPurchaseCheckSubject"));

        unixTime = String.valueOf(System.currentTimeMillis() / 1000L);
        email.setEmailRecipient("autotest.protocolone+" + unixTime + "@gmail.com");

        payment_form_url = app.restAPI.createSimpleOrder(
                app.getProperties.value("ApiUrlCheckout"),
                order);
        app.driver.get(payment_form_url);
        PayFormPage payFormPage =new PayFormPage(app.driver,
                app.getProperties.value("DefaultLanguage"),
                app.getProperties.value("DefautCountry"));
        Assert.assertEquals(app.driver.findElement(Locators.get("PayForm.OrderSummaryValue")).getText(), "€" + order.getOrderAmount());
        payFormPage.inputBankCardNumber(app.getProperties.value("No3DSSPaymentDECLINED"));
        payFormPage.inputBankCardExpired(app.getProperties.value("ValidExpiredDate"));
        payFormPage.inputBankCardCVV(app.getProperties.value("ValidCVV"));
        payFormPage.inputEmail(email.getEmailRecipient());
        order.setTotalAmount(payFormPage.getTotalAmount());
        app.restAPI.getOrderForPayForm(app.getProperties.value("ApiUrlCheckout"),
                order);
        payFormPage.clickPayButton();
        Assert.assertEquals(payFormPage.getFormTitleAfterPay(), app.getProperties.value("UnSuccessPayTitle"));
        Assert.assertEquals(payFormPage.getFormTextAfterPay(), app.getProperties.value("UnSuccessPayText"));

        //Webhook assert
        expectedWebhook.setObject_id(order.getUUID());
        mockApi.checkAndCleatEvent(actualWebhook);
        Assert.assertEquals(expectedWebhook, actualWebhook, "Actual webhook's data not equal:");
    }


}
